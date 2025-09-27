import java.util.*;
import java.io.*;

/**
 * SistemaAtencion
 * Gestiona clientes y delega operaciones sobre sus tickets.
 */
public class SistemaAtencion {
    private Map<String, Cliente> clientes = new TreeMap<>();
    private Map<String, Cliente> clientesEliminados = new TreeMap<>();
    private int contadorClientes = 1;
    private int contadorTickets = 1;
    
    public SistemaAtencion() {
        // Al crear el sistema, cargar clientes desde el CSV
        cargarClientesDesdeCSV("clientes.csv");
    }
    
    public Map<String, Cliente> getClientes(){ return clientes; }
    
    private String generarIdCliente() {
        return "C" + String.format("%02d", contadorClientes++);
    }
    
    private String generarIdTicket() {
        return "T" + String.format("%02d", contadorTickets++);
    }
    
    public void agregarCliente(String nombreCliente, String correoCliente) {
        // Validar si ya existe cliente con mismo email
        for (Cliente c : clientes.values()) {
            if (c.getEmail().equalsIgnoreCase(correoCliente)) {
                System.out.println("Cliente ya registrado: " + correoCliente);
                return;
            }
        }
        
        String idCliente = generarIdCliente();
        Cliente nuevoCliente = new Cliente(idCliente, nombreCliente, correoCliente);
        clientes.put(idCliente, nuevoCliente);
        System.out.println("Cliente agregado con ID: " + idCliente);
    }
    
    public void agregarTicket(String idCliente, String descripcionTicket) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            // Validar que la descripción no esté vacía
            if (descripcionTicket == null || descripcionTicket.trim().isEmpty()) {
                System.out.println("No se puede agregar un ticket vacío.");
                return;
            }
            
            // Verificar si ya existe un ticket con la misma descripción
            boolean ticketExiste = false;
            for (Ticket t : cliente.getTickets()) {
                if (t.getDescripcion().equalsIgnoreCase(descripcionTicket.trim())) {
                    ticketExiste = true;
                    break;
                }
            }
            
            if (ticketExiste) {
                System.out.println("Ticket ya existe para este cliente.");
                return;
            }
            
            String idTicket = generarIdTicket();
            cliente.agregarTicket(new Ticket(idTicket, descripcionTicket.trim()));
            System.out.println("Ticket agregado con ID: " + idTicket + " al cliente " + idCliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    // --- Mostrar clientes y tickets ---
    public void mostrarTickets() {
        for (Cliente c : clientes.values()) {
            System.out.println("\nTickets de " + c.info());
            for (Ticket t : c.getTickets()) {
                System.out.println(" - " + t.resumen(true));
            }
        }
    }
    
    public void mostrarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println(cliente.info(true));
        }
    }
    
    // --- Editar y eliminar ---
    public void editarTicket(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado, int nuevoTiempo, int nuevaSatisfaccion) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            Ticket ticket = cliente.buscarTicket(idTicket);
            if (ticket != null) {
                ticket.setDescripcion(nuevaDescripcion);
                ticket.setEstado(nuevoEstado);
                ticket.setTiempoRespuesta(nuevoTiempo);
                ticket.setSatisfaccion(nuevaSatisfaccion);
                System.out.println("Ticket editado.");
            } else System.out.println("Ticket no encontrado.");
        } else System.out.println("Cliente no encontrado.");
    }
    
    public void eliminarTicket(String idCliente, String idTicket) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            if (cliente.eliminarTicket(idTicket)) {
                System.out.println("Ticket eliminado.");
            } else System.out.println("Ticket no encontrado.");
        } else System.out.println("Cliente no encontrado.");
    }
    
    public void filtrarTicketsPorEstado(String estado) {
        System.out.println("\nTickets con estado: " + estado);
        for (Cliente cliente : clientes.values()) {
            for (Ticket ticket : cliente.getTickets()) {
                if (ticket.getEstado().equalsIgnoreCase(estado)) {
                    System.out.println(cliente.getNombre() + " -> " + ticket.resumen(true));
                }
            }
        }
    }
    
    public void filtrarTicketsPorTiempo(int limiteHoras) {
        System.out.println("\nTickets con tiempo de respuesta " + limiteHoras + "h");
        for (Cliente cliente : clientes.values()) {
            for (Ticket ticket : cliente.getTickets()) {
                if (ticket.getTiempoRespuesta() == limiteHoras) {
                    System.out.println(cliente.getNombre() + " -> " + ticket.resumen(true));
                }
            }
        }
    }
    
    public void eliminarCliente(String idCliente) {
        Cliente clienteEliminado = clientes.get(idCliente);
        if (clienteEliminado != null) {
            clienteEliminado.getHistorial().add("Cliente eliminado del sistema");
            clientesEliminados.put(idCliente, clienteEliminado);
            clientes.remove(idCliente);
            System.out.println("Cliente eliminado: " + clienteEliminado.getNombre());
        } else System.out.println("No se encontró un cliente con el ID: " + idCliente);
    }
    
    public Cliente buscarCliente(String criterioBusqueda) {
        if (clientes.containsKey(criterioBusqueda)) return clientes.get(criterioBusqueda);
        for (Cliente cliente : clientes.values()) {
            if (cliente.getNombre().equalsIgnoreCase(criterioBusqueda)) return cliente;
        }
        return null;
    }
    
    public Ticket buscarTicket(String idTicket) {
        for (Cliente cliente : clientes.values()) {
            Ticket ticketEncontrado = cliente.buscarTicket(idTicket);
            if (ticketEncontrado != null) return ticketEncontrado;
        }
        return null;
    }
    
    // --- Guardado y reporte ---
    public void guardarCambios(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("ID_Cliente,Nombre,Correo,ID_Ticket,Descripcion,Estado,TiempoRespuesta,Satisfaccion");
            
            for (Cliente cliente : clientes.values()) {
                for (Ticket ticket : cliente.getTickets()) {
                    writer.printf("%s,%s,%s,%s,%s,%s,%d,%d%n",
                            cliente.getId(),
                            cliente.getNombre(),
                            cliente.getEmail(),
                            ticket.getId(),
                            ticket.getDescripcion().replace(",", ";"),  // reemplaza comas para CSV
                            ticket.getEstado(),
                            ticket.getTiempoRespuesta(),
                            ticket.getSatisfaccion());
                }
            }
            
            System.out.println("Cambios guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar cambios: " + e.getMessage());
        }
    }
    
    public void generarReporte(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.append("ID Cliente;Nombre;Correo;Acción\n");
            for (Cliente cliente : clientes.values()) escribirClienteEnReporte(writer, cliente);
            for (Cliente cliente : clientesEliminados.values()) escribirClienteEnReporte(writer, cliente);
            System.out.println("Reporte generado en " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar reporte: " + e.getMessage());
        }
    }
    
    private void escribirClienteEnReporte(FileWriter writer, Cliente cliente) throws IOException {
        writer.append(cliente.getId()).append(";")
              .append(cliente.getNombre()).append(";")
              .append(cliente.getEmail()).append(";");
        if (cliente.getHistorial().isEmpty()) writer.append("Sin acciones registradas\n");
        else {
            writer.append("\n");
            for (String accion : cliente.getHistorial()) {
                writer.append(";;;").append(accion).append("\n");
            }
        }
        writer.append("\n");
    }
    
    // --- Cargar CSV ---
    public void cargarClientesDesdeCSV(String nombreArchivo) {
        File f = new File(nombreArchivo);
        if (!f.exists()) {
            System.out.println("Archivo CSV no existe, comenzando con datos vacíos.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = br.readLine(); // saltar encabezado
            int maxCliente = 0;
            int maxTicket = 0;
            
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] partes = linea.split(",", -1);
                if (partes.length < 8) {
                    continue;
                }
                
                String idCliente = partes[0].trim();
                String nombre = partes[1].trim();
                String email = partes[2].trim();
                String idTicket = partes[3].trim();
                String descripcion = partes[4].trim();
                String estado = partes[5].trim();
                int tiempo = partes[6].isEmpty() ? 0 : Integer.parseInt(partes[6]);
                int satisfaccion = partes[7].isEmpty() ? 0 : Integer.parseInt(partes[7]);
                
                // Actualizar contadores
                try {
                    if (idCliente.startsWith("C")) {
                        int numCliente = Integer.parseInt(idCliente.substring(1));
                        if (numCliente > maxCliente) maxCliente = numCliente;
                    }
                    
                    if (idTicket.startsWith("T")) {
                        int numTicket = Integer.parseInt(idTicket.substring(1));
                        if (numTicket > maxTicket) maxTicket = numTicket;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
                
                // Buscar o crear cliente
                Cliente clienteObj = clientes.get(idCliente);
                if (clienteObj == null) {
                    clienteObj = new Cliente(idCliente, nombre, email);
                    clientes.put(idCliente, clienteObj);
                }
                
                // Verificar si el ticket ya existe
                Ticket ticketExistente = clienteObj.buscarTicket(idTicket);
                if (ticketExistente == null) {
                    // Ticket no existe, crearlo
                    Ticket ticket = new Ticket(idTicket, descripcion);
                    ticket.setEstado(estado);
                    ticket.setTiempoRespuesta(tiempo);
                    ticket.setSatisfaccion(satisfaccion);
                    clienteObj.agregarTicket(ticket);
                }
            }
            
            // Actualizar contadores globales
            contadorClientes = maxCliente + 1;
            contadorTickets = maxTicket + 1;
            
            System.out.println("Clientes cargados desde CSV correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error al cargar CSV: " + e.getMessage());
        }
    }
}