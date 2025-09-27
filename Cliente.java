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

    /** Registra una edición en el historial. */
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

    /**
     * Devuelve información básica del cliente.
     * 
     * <p>Este método sobrescribe un posible método {@code info()} de una clase padre
     * o interfaz.</p>
     *
     * @return una cadena con el nombre, ID y correo del cliente
     */
    @Override
    public String info() {
        return "Cliente: " + nombre + " [ID: " + id + ", Email: " + email + "]";
    }

    /**
     * Devuelve información del cliente, en versión básica o detallada.
     *
     * @param detallado si es {@code true}, devuelve información detallada (ID, nombre y correo);
     *                  si es {@code false}, devuelve información básica
     * @return una cadena con los datos del cliente según el nivel de detalle
     */
    public String info(boolean detallado) {
        if (detallado) {
            return "ID: " + id + " Nombre: " + nombre + " Email: " + email;
        }
        return info();
    }
}