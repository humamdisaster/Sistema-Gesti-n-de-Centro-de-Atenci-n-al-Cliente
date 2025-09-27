import java.util.*;
import java.io.*;

/**
 * SistemaAtencion
 * Gestiona clientes y delega operaciones sobre sus tickets.
 */
public class SistemaAtencion {
    private Map<String, Cliente> clientes = new TreeMap<>();
    private int contadorClientes = 1;
    private int contadorTickets = 1;
    
    public SistemaAtencion() {
        // Datos iniciales
    	/*
        Cliente c1 = new Cliente("C01", "Ana Pérez", "ana@mail.com");
        Cliente c2 = new Cliente("C02", "Luis Gómez", "luis@mail.com");

        clientes.put(c1.getId(), c1);
        clientes.put(c2.getId(), c2);

        c1.agregarTicket(new Ticket("T01", "Problema con facturación"));
        c2.agregarTicket(new Ticket("T02", "No puedo iniciar sesión"));
        */
    }

    public Map<String, Cliente> getClientes(){ return clientes; }
    
    /**
     * Genera un nuevo ID de cliente según el orden de llegada.
     * @return ID generado en formato CXX
     */
    private String generarIdCliente() {
        return "C" + String.format("%02d", contadorClientes++);
    }
    
    /**
     * Genera un nuevo ID de ticket según el orden de llegada.
     * @return ID generado en formato TXX
     */
    private String generarIdTicket() {
        return "T" + String.format("%02d", contadorTickets++);
    }
    
    /**
     * Agrega un nuevo cliente al sistema con ID generado automáticamente.
     * @param nombreCliente nombre del cliente
     * @param correoCliente correo electrónico
     */
    public void agregarCliente(String nombreCliente, String correoCliente) {
        String idCliente = generarIdCliente();
        Cliente nuevoCliente = new Cliente(idCliente, nombreCliente, correoCliente);
        clientes.put(idCliente, nuevoCliente);
        System.out.println("Cliente agregado con ID: " + idCliente);
    }
    
 // Sobrecarga: retorna el ID
    public String agregarCliente(String nombreCliente, String correoCliente, boolean retornarId) {
        String idCliente = generarIdCliente();
        Cliente nuevoCliente = new Cliente(idCliente, nombreCliente, correoCliente);
        clientes.put(idCliente, nuevoCliente);
        System.out.println("Cliente agregado con ID: " + idCliente);
        return idCliente;
    }
    
    /**
     * Agrega un nuevo ticket a un cliente existente, con ID generado automáticamente.
     * @param idCliente ID del cliente
     * @param descripcionTicket descripción del ticket
     */
    public void agregarTicket(String idCliente, String descripcionTicket) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            String idTicket = generarIdTicket();
            cliente.agregarTicket(new Ticket(idTicket, descripcionTicket));
            System.out.println("Ticket agregado con ID: " + idTicket + " al cliente " + idCliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    /**
     * Muestra todos los clientes y sus tickets.
     */
    public void mostrarTickets() {
        for (Cliente c : clientes.values()) {
            System.out.println("\nTickets de " + c.info());
            for (Ticket t : c.getTickets()) {
                System.out.println(" - " + t.resumen(true));
            }
        }
    }
    
    /**
     * Muestra la lista de clientes.
     */
    public void mostrarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println(cliente.info(true));
        }
    }
    
    /**
     * Edita un ticket de un cliente.
     */
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
            } else {
                System.out.println("Ticket no encontrado.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    /**
     * Elimina un ticket de un cliente.
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
     * Filtra tickets por estado.
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
     * Filtra tickets por tiempo de respuesta exacto.
     */
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
    
    /**
     * Elimina un cliente por su ID.
     * @param idCliente identificador único del cliente
     */
    public void eliminarCliente(String idCliente) {
        Cliente clienteEliminado = clientes.remove(idCliente);
        if (clienteEliminado != null) {
            System.out.println("Cliente eliminado: " + clienteEliminado.getNombre());
        } else {
            System.out.println("No se encontró un cliente con el ID: " + idCliente);
        }
    }
    
    /**
     * Busca un cliente por su ID o por nombre.
     * @param criterioBusqueda ID o nombre del cliente
     * @return el cliente si existe, null en caso contrario
     */
    public Cliente buscarCliente(String criterioBusqueda) {
        // Buscar por ID directamente en el mapa
        if (clientes.containsKey(criterioBusqueda)) {
            return clientes.get(criterioBusqueda);
        }
        
        // Buscar por nombre en la colección de clientes
        for (Cliente cliente : clientes.values()) {
            if (cliente.getNombre().equalsIgnoreCase(criterioBusqueda)) {
                return cliente;
            }
        }
        return null;
    }
    
    /**
     * Busca un ticket por su ID.
     * @param idTicket identificador único del ticket
     * @return el ticket si existe, null en caso contrario
     */
    public Ticket buscarTicket(String idTicket) {
        for (Cliente cliente : clientes.values()) {
            Ticket ticketEncontrado = cliente.buscarTicket(idTicket);
            if (ticketEncontrado != null) {
                return ticketEncontrado;
            }
        }
        return null;
    }
    
    /**
     * Genera un reporte con todos los clientes y sus tickets en un archivo CSV.
     * @param nombreArchivo ruta del archivo a crear/reescribir
     */
    public void guardarCambios(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            // Encabezado CSV
            writer.println("ID_Cliente,Nombre,Correo,ID_Ticket,Descripcion,Estado,TiempoRespuesta,Satisfaccion");

            // Recorrer clientes y tickets
            for (Cliente cliente : clientes.values()) {
                for (Ticket ticket : cliente.getTickets()) {
                    writer.printf("%s,%s,%s,%s,%s,%s,%d,%d%n",
                            cliente.getId(),
                            cliente.getNombre(),
                            cliente.getEmail(),
                            ticket.getId(),
                            ticket.getDescripcion().replace(",", ";"), // evitar conflicto con CSV
                            ticket.getEstado(),
                            ticket.getTiempoRespuesta(),
                            ticket.getSatisfaccion());
                }
            }
            System.out.println("Cambios guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
    
    public void generarReporte(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            // Encabezado
            writer.append("ID Cliente;Nombre;Correo;Acción\n");

            // Recorrer todos los clientes
            for (Cliente cliente : clientes.values()) {
                // Escribir datos del cliente en una primera fila
                writer.append(cliente.getId()).append(";")
                      .append(cliente.getNombre()).append(";")
                      .append(cliente.getEmail()).append(";");

                if (cliente.getHistorial().isEmpty()) {
                    writer.append("Sin acciones registradas\n");
                } else {
                    writer.append("\n"); // salto de línea para empezar historial

                    // Escribir cada acción en filas nuevas
                    for (String accion : cliente.getHistorial()) {
                        writer.append(";") // columna ID vacía
                              .append(";") // columna Nombre vacía
                              .append(";") // columna Correo vacía
                              .append(accion).append("\n");
                    }
                }

                writer.append("\n"); // línea en blanco para separar clientes
            }

            System.out.println("Reporte generado en " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al generar reporte: " + e.getMessage());
        }
    }
}