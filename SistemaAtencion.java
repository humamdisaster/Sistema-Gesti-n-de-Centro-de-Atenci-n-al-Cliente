import java.util.*;

/**
 * SistemaAtencion
 * Gestiona clientes y delega operaciones sobre sus tickets.
 */
public class SistemaAtencion {
    private Map<String, Cliente> clientes = new TreeMap<>();

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
     * Agrega un ticket a un cliente existente.
     */
    public void agregarTicket(String idCliente, String idTicket, String descripcion) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            cliente.agregarTicket(new Ticket(idTicket, descripcion));
            System.out.println("Ticket agregado al cliente " + idCliente);
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

    
    public void agregarCliente(String idCliente, String nomCliente, String mailCliente) {
    	Cliente nuevoCliente = new Cliente(idCliente, nomCliente, mailCliente);
    	if (clientes.containsKey(nuevoCliente.getId())) {
    		System.out.println("El cliente ya existe");
    		return;
    	}
    	clientes.put(nuevoCliente.getId(), nuevoCliente);
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
    public void editarTicket(String idCliente, String idTicket, String nuevaDescripcion, String nuevoEstado, int nuevoTiempo) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            Ticket ticket = cliente.buscarTicket(idTicket);
            if (ticket != null) {
                ticket.setDescripcion(nuevaDescripcion);
                ticket.setEstado(nuevoEstado);
                ticket.setTiempoRespuesta(nuevoTiempo);
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
    
}