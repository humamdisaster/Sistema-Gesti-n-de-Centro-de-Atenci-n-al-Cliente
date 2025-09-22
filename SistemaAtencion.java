import java.util.*;

/**
 * Clase que gestiona el sistema de atención de clientes y sus tickets.
 * Permite registrar, mostrar, editar, eliminar y filtrar tickets.
 */
public class SistemaAtencion {
    /** Mapa de clientes registrados, con su ID como clave. */
    private Map<String, Cliente> clientes = new HashMap<>();
    
    /** Mapa que relaciona cada cliente con su lista de tickets. */
    private Map<String, List<Ticket>> ticketsPorCliente = new HashMap<>();

    /**
     * Constructor que inicializa el sistema con algunos clientes y tickets de ejemplo.
     */
    public SistemaAtencion() {
        Cliente cliente1 = new Cliente("C01", "Ana Pérez", "ana@mail.com");
        Cliente cliente2 = new Cliente("C02", "Luis Gómez", "luis@mail.com");

        clientes.put(cliente1.getId(), cliente1);
        clientes.put(cliente2.getId(), cliente2);

        ticketsPorCliente.put(cliente1.getId(), new ArrayList<>());
        ticketsPorCliente.put(cliente2.getId(), new ArrayList<>());

        ticketsPorCliente.get(cliente1.getId()).add(new Ticket("T01", "Problema con facturación"));
        ticketsPorCliente.get(cliente2.getId()).add(new Ticket("T02", "No puedo iniciar sesión"));
    }

    /**
     * Agrega un nuevo ticket al cliente indicado.
     * 
     * @param idCliente   ID del cliente dueño del ticket
     * @param idTicket    ID único del ticket
     * @param descripcion Descripción del problema
     */
    public void agregarTicket(String idCliente, String idTicket, String descripcion) {
        if (clientes.containsKey(idCliente)) {
            ticketsPorCliente.get(idCliente).add(new Ticket(idTicket, descripcion));
            System.out.println("Ticket agregado al cliente " + idCliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    /**
     * Muestra todos los tickets de todos los clientes.
     */
    public void mostrarTickets() {
        for (String idCliente : ticketsPorCliente.keySet()) {
            Cliente clienteActual = clientes.get(idCliente);
            System.out.println("\nTickets de " + clienteActual.info());
            for (Ticket ticket : ticketsPorCliente.get(idCliente)) {
                System.out.println(" - " + ticket.resumen(true));
            }
        }
    }

    /**
     * Muestra todos los clientes registrados en el sistema.
     */
    public void mostrarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println(cliente.info(true));
        }
    }
    
    /**
     * Edita un ticket existente de un cliente.
     * 
     * @param idCliente         ID del cliente dueño del ticket
     * @param idTicket          ID del ticket a editar
     * @param nuevaDescripcion  Nueva descripción del problema
     * @param nuevoEstado       Nuevo estado (ej: Pendiente, Resuelto)
     * @param nuevoTiempo       Nuevo tiempo de respuesta en horas
     */
    public void editarTicket(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado, int nuevoTiempo) {
        if (ticketsPorCliente.containsKey(idCliente)) {
            for (Ticket ticket : ticketsPorCliente.get(idCliente)) {
                if (ticket.getId().equals(idTicket)) {
                    ticket.setDescripcion(nuevaDescripcion);
                    ticket.setEstado(nuevoEstado);
                    ticket.setTiempoRespuesta(nuevoTiempo);
                    System.out.println("Ticket editado.");
                    return;
                }
            }
            System.out.println("Ticket no encontrado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    /**
     * Elimina un ticket de un cliente.
     * 
     * @param idCliente ID del cliente dueño del ticket
     * @param idTicket  ID del ticket a eliminar
     */
    public void eliminarTicket(String idCliente, String idTicket) {
        if (ticketsPorCliente.containsKey(idCliente)) {
            List<Ticket> listaTickets = ticketsPorCliente.get(idCliente);
            
            for (int i = 0; i < listaTickets.size(); i++) {
                if (listaTickets.get(i).getId().equals(idTicket)) {
                    listaTickets.remove(i);
                    System.out.println("Ticket eliminado.");
                    return;
                }
            }
            System.out.println("Ticket no encontrado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    /**
     * Filtra y muestra los tickets que tienen el estado indicado.
     * 
     * @param estado Estado por el que se desea filtrar (ej: Pendiente, Resuelto)
     */
    public void filtrarTicketsPorEstado(String estado) {
        System.out.println("\nTickets con estado: " + estado);
        for (String idCliente : ticketsPorCliente.keySet()) {
            for (Ticket ticket : ticketsPorCliente.get(idCliente)) {
                if (ticket.getEstado().equalsIgnoreCase(estado)) {
                    Cliente clienteActual = clientes.get(idCliente);
                    System.out.println(clienteActual.getNombre() + " -> " + ticket.resumen(true));
                }
            }
        }
    }
    
    /**
     * Filtra y muestra los tickets cuyo tiempo de respuesta coincide con el límite indicado.
     * 
     * @param limiteHoras Tiempo de respuesta en horas
     */
    public void filtrarTicketsPorTiempo(int limiteHoras) {
        System.out.println("\nTickets con tiempo de respuesta " + limiteHoras + "h");

        for (String idCliente : ticketsPorCliente.keySet()) {
            List<Ticket> ticketsCliente = ticketsPorCliente.get(idCliente);
            Cliente clienteActual = clientes.get(idCliente);

            for (Ticket ticket : ticketsCliente) {
                if (ticket.getTiempoRespuesta() == limiteHoras) {
                    System.out.println(clienteActual.getNombre() + " -> " + ticket.resumen(true));
                }
            }
        }
    }
}