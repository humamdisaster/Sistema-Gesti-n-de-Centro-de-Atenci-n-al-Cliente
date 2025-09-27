import java.util.*;
import java.io.*;

/**
 * SistemaAtencion
 * Gestiona clientes y delega operaciones sobre sus tickets.
 */
public class SistemaAtencion {

    private Map<String, Cliente> clientes = new TreeMap<>();          // Clientes activos
    private Map<String, Cliente> clientesEliminados = new TreeMap<>(); // Clientes eliminados
    private int contadorClientes = 1;  // contador para generar IDs de clientes
    private int contadorTickets = 1;   // contador para generar IDs de tickets

    /**
     * Constructor. Carga los clientes desde CSV al iniciar.
     */
    public SistemaAtencion() {
        cargarClientesDesdeCSV("clientes.csv");
    }

    /** Devuelve todos los clientes activos.
     * @return Mapa con ID de cliente como clave y objeto Cliente como valor.
     */
    public Map<String, Cliente> getClientes() { 
        return clientes; 
    }

    /** Genera un ID único para un nuevo cliente. */
    private String generarIdCliente() {
        return "C" + String.format("%02d", contadorClientes++);
    }

    /** Genera un ID único para un nuevo ticket. */
    private String generarIdTicket() {
        return "T" + String.format("%02d", contadorTickets++);
    }

    // ------------------ AGREGAR CLIENTES ------------------

    /**
     * Agrega un nuevo cliente al sistema.
     * @param nombreCliente nombre del cliente
     * @param correoCliente email del cliente
     */
    public void agregarCliente(String nombreCliente, String correoCliente) {
        // Validar duplicados por email
        for (Cliente cliente : clientes.values()) {
            if (cliente.getEmail().equalsIgnoreCase(correoCliente)) {
                System.out.println("Cliente ya registrado: " + correoCliente);
                return;
            }
        }
        String idCliente = generarIdCliente();
        Cliente nuevoCliente = new Cliente(idCliente, nombreCliente, correoCliente);
        clientes.put(idCliente, nuevoCliente);
        System.out.println("Cliente agregado con ID: " + idCliente);
    }

    /**
     * Sobrecarga de agregarCliente, retorna el ID del cliente agregado.
     * 
     * @param nombreCliente Nombre del cliente.
     * @param correoCliente Correo electrónico del cliente.
     * @param retornarId Boolean para indicar si se retorna el ID.
     * @return ID del cliente agregado.
     */
    public String agregarCliente(String nombreCliente, String correoCliente, boolean retornarId) {
        String idCliente = generarIdCliente();
        Cliente nuevoCliente = new Cliente(idCliente, nombreCliente, correoCliente);
        clientes.put(idCliente, nuevoCliente);
        System.out.println("Cliente agregado con ID: " + idCliente);
        return idCliente;
    }

    // ------------------ AGREGAR TICKETS ------------------

    /**
     * Agrega un ticket a un cliente existente.
     * @param idCliente ID del cliente
     * @param descripcionTicket descripción del ticket
     */
    public void agregarTicket(String idCliente, String descripcionTicket) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
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

    // ------------------ MOSTRAR CLIENTES Y TICKETS ------------------

    public void mostrarTickets() {
        for (Cliente cliente : clientes.values()) {
            System.out.println("\nTickets de " + cliente.info());
            for (Ticket ticket : cliente.getTickets()) {
                System.out.println(" - " + ticket.resumen(true));
            }
        }
    }

    public void mostrarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println(cliente.info(true));
        }
    }

    public boolean correoYaExiste(String correo) {
        if (correo == null || correo.isEmpty()) return false;
        for (Cliente cliente : clientes.values()) {
            if (correo.equalsIgnoreCase(cliente.getEmail())) {
                return true;
            }
        }
        return false;
    }

    // ------------------ EDITAR Y ELIMINAR ------------------
    
    /**
     * Edita un ticket existente de un cliente.
     * 
     * @param idCliente ID del cliente.
     * @param idTicket ID del ticket.
     * @param nuevaDescripcion Nueva descripción del ticket.
     * @param nuevoEstado Nuevo estado del ticket.
     * @param nuevoTiempo Nuevo tiempo de respuesta en horas.
     * @param nuevaSatisfaccion Nueva satisfacción del cliente.
     */
    public void editarTicket(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado,
                             double nuevoTiempo, int nuevaSatisfaccion) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            Ticket ticket = cliente.buscarTicket(idTicket);
            if (ticket != null) {
                ticket.setDescripcion(nuevaDescripcion);
                ticket.setEstado(nuevoEstado);
                ticket.setTiempoRespuesta(nuevoTiempo);
                ticket.setSatisfaccion(nuevaSatisfaccion);
                System.out.println("Ticket editado.");
            } else {
                System.out.println("Ticket no encontrado.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    /**
     * Elimina un ticket de un cliente.
     * 
     * @param idCliente ID del cliente.
     * @param idTicket ID del ticket a eliminar.
     */
    public void eliminarTicket(String idCliente, String idTicket) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            if (cliente.eliminarTicket(idTicket)) {
                System.out.println("Ticket eliminado.");
            } else {
                System.out.println("Ticket no encontrado.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    /**
     * Filtra tickets por estado y los muestra por consola.
     * 
     * @param estado Estado de los tickets a filtrar (ej. "Pendiente" o "Resuelto").
     */
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
    
    /**
     * Filtra tickets por tiempo de respuesta exacto y los muestra por consola.
     * 
     * @param limiteHoras Tiempo de respuesta en horas.
     */
    public void filtrarTicketsPorTiempo(double limiteHoras) {
        System.out.println("\nTickets con tiempo de respuesta " + limiteHoras + "h");
        for (Cliente cliente : clientes.values()) {
            for (Ticket ticket : cliente.getTickets()) {
                if (Math.abs(ticket.getTiempoRespuesta() - limiteHoras) < 0.001) {
                    System.out.println(cliente.getNombre() + " -> " + ticket.resumen(true));
                }
            }
        }
    }
    
    /**
     * Elimina un cliente del sistema, moviéndolo a clientes eliminados.
     * 
     * @param idCliente ID del cliente a eliminar.
     */
    public void eliminarCliente(String idCliente) {
        Cliente clienteEliminado = clientes.get(idCliente);
        if (clienteEliminado != null) {
            clienteEliminado.getHistorial().add("Cliente eliminado del sistema");
            clientesEliminados.put(idCliente, clienteEliminado);
            clientes.remove(idCliente);
            System.out.println("Cliente eliminado: " + clienteEliminado.getNombre());
        } else {
            System.out.println("No se encontró un cliente con el ID: " + idCliente);
        }
    }

    // ------------------ BÚSQUEDAS ------------------
    
    /**
     * Busca un cliente por ID o nombre.
     * 
     * @param criterioBusqueda ID o nombre del cliente.
     * @return Cliente encontrado, o null si no existe.
     */
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

    // ------------------ GUARDADO Y REPORTE ------------------
    
    /**
     * Guarda los cambios en un archivo CSV.
     * 
     * @param nombreArchivo Ruta del archivo donde se guardarán los datos.
     */
    public void guardarCambios(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("ID_Cliente,Nombre,Correo,ID_Ticket,Descripcion,Estado,TiempoRespuesta,Satisfaccion");

            for (Cliente cliente : clientes.values()) {
                for (Ticket ticket : cliente.getTickets()) {
                    writer.printf("%s,%s,%s,%s,%s,%s,%.2f,%d%n",
                            cliente.getId(),
                            cliente.getNombre(),
                            cliente.getEmail(),
                            ticket.getId(),
                            ticket.getDescripcion().replace(",", ";"),
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
    
    /**
     * Genera un reporte en un archivo de texto con la información de clientes y tickets.
     * 
     * @param nombreArchivo Ruta del archivo de reporte.
     */
    public void generarReporte(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.append("ID Cliente;Nombre;Correo;Tiempo Resolución (h);Acción\n");
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

        if (cliente.getHistorial().isEmpty()) {
            writer.append("Sin acciones registradas\n");
        } else {
            writer.append("\n");
            for (String accion : cliente.getHistorial()) {
                String accionConTiempo = accion;
                for (Ticket ticket : cliente.getTickets()) {
                    if (accion.contains(ticket.getId())) {
                        accionConTiempo = accion + ", Tiempo de respuesta: " + ticket.getTiempoRespuesta() + " (h)";
                        break;
                    }
                }
                writer.append(";;;0;").append(accionConTiempo).append("\n");
            }
        }
        writer.append("\n");
    }

    // ------------------ CARGAR CSV ------------------

    public void cargarClientesDesdeCSV(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
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
                if (partes.length < 8) continue;

                String idCliente = partes[0].trim();
                String nombre = partes[1].trim();
                String email = partes[2].trim();
                String idTicket = partes[3].trim();
                String descripcion = partes[4].trim();
                String estado = partes[5].trim();
                double tiempo = partes[6].isEmpty() ? 0 : Double.parseDouble(partes[6]);
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
                    Ticket ticket = new Ticket(idTicket, descripcion);
                    ticket.setEstado(estado);
                    ticket.setTiempoRespuesta(tiempo);
                    ticket.setSatisfaccion(satisfaccion);
                    clienteObj.agregarTicket(ticket);
                }
            }

            contadorClientes = maxCliente + 1;
            contadorTickets = maxTicket + 1;
            System.out.println("Clientes cargados desde CSV correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar CSV: " + e.getMessage());
        }
    }
}