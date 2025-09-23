import java.util.ArrayList;
import java.util.List;

/**
 * Cliente.java
 * Representa un cliente del sistema con su lista de tickets asociados.
 */
public class Cliente extends Persona {
    private List<Ticket> tickets;

    /**
     * Crea un nuevo cliente con una lista de tickets vacía.
     * @param id identificador del cliente
     * @param nombre nombre completo
     * @param email correo electrónico
     */
    public Cliente(String id, String nombre, String email) {
        super(id, nombre, email);
        this.tickets = new ArrayList<>();
    }

    /**
     * Agrega un ticket a este cliente.
     * @param ticket ticket a agregar
     */
    public void agregarTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    /**
     * Obtiene la lista de tickets del cliente.
     * @return lista de tickets
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Busca un ticket por su ID.
     * @param idTicket identificador del ticket
     * @return el ticket si existe, null en caso contrario
     */
    public Ticket buscarTicket(String idTicket) {
        for (Ticket t : tickets) {
            if (t.getId().equals(idTicket)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Elimina un ticket por su ID.
     * @param idTicket identificador del ticket
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarTicket(String idTicket) {
        return tickets.removeIf(t -> t.getId().equals(idTicket));
    }

    @Override
    public String info() {
        return "Cliente: " + nombre + " [ID: " + id + ", Email: " + email + "]";
    }

    public String info(boolean detallado) {
        if (detallado) {
            return "ID: " + id + " Nombre: " + nombre + " Email: " + email;
        }
        return info();
    }
}