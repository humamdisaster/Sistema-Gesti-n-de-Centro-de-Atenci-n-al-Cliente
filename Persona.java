/**
 * Persona.java
 * Superclase que representa una persona con id, nombre y email.
 */
public class Persona {
    protected String id;
    protected String nombre;
    protected String email;

    /**
     * Constructor de Persona.
     * @param id identificador único
     * @param nombre nombre completo
     * @param email correo electrónico
     */
    public Persona(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    /**
     * Devuelve una representación breve de la persona.
     * @return nombre e id en una cadena
     */
    public String info() {
        return nombre + " (" + id + ")";
    }
}