import java.util.ArrayList;
import java.util.List;

/**
 * Cliente.java
 * Representa un cliente del sistema con su lista de tickets asociados
 * y un historial de acciones realizadas sobre esos tickets.
 */
public class Cliente extends Persona {
    private List<Ticket> tickets;
    private List<String> historial;

    /**
     * Crea un nuevo cliente con una lista de tickets vacía.
     * @param id identificador del cliente
     * @param nombre nombre completo
     * @param email correo electrónico
     */
    public Cliente(String id, String nombre, String email) {
        super(id, nombre, email);
        this.tickets = new ArrayList<>();
        this.historial = new ArrayList<>();
    }

    /**
     * Agrega un ticket a este cliente.
     * @param ticket ticket a agregar
     */
    public void agregarTicket(Ticket ticket) {
        tickets.add(ticket);
        historial.add("Se agregó ticket con ID: " + ticket.getId() + 
                      " y descripción: \"" + ticket.getDescripcion() + "\"");
    }

    /**
     * Registra una edición en el historial.
     */
    public void registrarEdicion(Ticket ticket, String nuevaDescripcion, String nuevoEstado, int nuevaSatisfaccion) {
        historial.add("Se editó ticket " + ticket.getId() +
                      " → nueva descripción: \"" + nuevaDescripcion + "\", estado: " + nuevoEstado +
                      ", satisfacción: " + nuevaSatisfaccion);
    }

    /**
     * Elimina un ticket por su ID.
     * @param idTicket identificador del ticket
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarTicket(String idTicket) {
        boolean eliminado = tickets.removeIf(t -> t.getId().equals(idTicket));
        if (eliminado) {
            historial.add("Se eliminó ticket con ID: " + idTicket);
        }
        return eliminado;
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
     * Obtiene el historial de modificaciones del cliente.
     * @return lista de strings con las acciones realizadas
     */
    public List<String> getHistorial() {
        return historial;
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