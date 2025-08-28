public class Ticket {
    private String id;
    private String descripcion;
    private String estado;
    private int tiempoRespuesta; //en horas

    //constructor
    public Ticket(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = "Pendiente";
        this.tiempoRespuesta = 0;
    }

    //getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getTiempoRespuesta() { return tiempoRespuesta; }
    public void setTiempoRespuesta(int tiempoRespuesta) { this.tiempoRespuesta = tiempoRespuesta; }

    //sobrecarga
    public String resumen() {
        return "[" + id + "] " + descripcion + " - " + estado;
    }

    public String resumen(boolean detallado) {
        return resumen() + " | Tiempo resp: " + tiempoRespuesta + "h";
    }
}
