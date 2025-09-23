/**
 * Ticket.java
 * Representa un ticket de soporte asociado a un cliente.
 */
public class Ticket {
    private String id;
    private String descripcion;
    private String estado;
    private int tiempoRespuesta; // en horas

    /**
     * Constructor: crea un ticket en estado "Pendiente" con tiempo 0.
     * @param id identificador del ticket
     * @param descripcion breve descripción del problema
     */
    public Ticket(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = "Pendiente";
        this.tiempoRespuesta = 0;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getTiempoRespuesta() { return tiempoRespuesta; }
    public void setTiempoRespuesta(int tiempoRespuesta) { this.tiempoRespuesta = tiempoRespuesta; }

    /**
     * Sobrescribe toString().
     * @return representación textual del ticket
     */
    @Override
    public String toString() {
        return "[" + id + "] " + descripcion + " - Estado: " + estado + " | Tiempo resp: " + tiempoRespuesta + "h";
    }

    /**
     * Retorna un resumen básico del ticket.
     * Se mantiene como alias de toString() para consistencia.
     * @return cadena con información del ticket
     */
    public String resumen() {
        return toString();
    }

    /**
     * Resumen con parámetro (compatibilidad).
     * @param detallado ignorado actualmente
     * @return resumen básico del ticket
     */
    public String resumen(boolean detallado) {
        return resumen();
    }
}