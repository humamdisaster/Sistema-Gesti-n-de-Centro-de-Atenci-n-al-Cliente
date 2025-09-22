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
	* Constructor: crea un ticket en estado inicial "Pendiente" con tiempo 0.
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
	*  Representación completa del ticket.
	*/
	@Override
	public String toString() {
		return "[" + id + "] " + descripcion + " - Estado: " + estado + " | Tiempo resp: " + tiempoRespuesta + "h";
	}
	
	/**
     * Devuelve un resumen del ticket.
     * @return cadena con la información del ticket
     */
	public String resumen() {
		return toString();
	}

	/**
     * Devuelve un resumen del ticket con o sin detalle.
     * @param detallado si es true incluye tiempo de respuesta
     * @return cadena con la información del ticket
     */
    public String resumen(boolean detallado) {
        if (detallado) {
            return toString();
        }
        return "[" + id + "] " + descripcion + " - " + estado;
    }
}