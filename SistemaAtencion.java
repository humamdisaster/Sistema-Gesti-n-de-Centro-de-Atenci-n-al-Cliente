import java.util.*;

public class SistemaAtencion {
    //Mapa de clientes
    private Map<String, Cliente> clientes = new HashMap<>();
    
    //Cada cliente tiene lista de tickets
    private Map<String, List<Ticket>> ticketsPorCliente = new HashMap<>();

    public SistemaAtencion() {
        //Datos iniciales
        Cliente c1 = new Cliente("C01", "Ana Pérez", "ana@mail.com");
        Cliente c2 = new Cliente("C02", "Luis Gómez", "luis@mail.com");

        clientes.put(c1.getId(), c1);
        clientes.put(c2.getId(), c2);

        ticketsPorCliente.put(c1.getId(), new ArrayList<>());
        ticketsPorCliente.put(c2.getId(), new ArrayList<>());

        ticketsPorCliente.get(c1.getId()).add(new Ticket("T01", "Problema con facturación"));
        ticketsPorCliente.get(c2.getId()).add(new Ticket("T02", "No puedo iniciar sesión"));
    }

    //Inserción manual de un ticket
    public void agregarTicket(String idCliente, String idTicket, String descripcion) {
        if (clientes.containsKey(idCliente)) {
            ticketsPorCliente.get(idCliente).add(new Ticket(idTicket, descripcion));
            System.out.println("Ticket agregado al cliente " + idCliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    //Mostrar listado de tickets de todos los clientes
    public void mostrarTickets() {
        for (String idCliente : ticketsPorCliente.keySet()) {
            Cliente c = clientes.get(idCliente);
            System.out.println("\nTickets de " + c.info());
            for (Ticket t : ticketsPorCliente.get(idCliente)) {
                System.out.println(" - " + t.resumen(true));
            }
        }
    }

    //Mostrar clientes
    public void mostrarClientes() {
        for (Cliente c : clientes.values()) {
            System.out.println(c.info(true));
        }
    }
}

