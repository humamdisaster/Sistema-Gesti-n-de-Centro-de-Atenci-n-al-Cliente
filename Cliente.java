
public class Cliente {
	private String id;
	private String nombre;
	private String email;
	
	//constructor
	public Cliente(String id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}
	
	//getters y setters
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	//sobrecarga
	public String info() {
		return nombre + " (" + id + ")";
	}
	
	public String info(boolean detallado) {
		if (detallado)
			return "ID: " + id + " Nombre: " + nombre + " Email: " + email;
		return info();
	}
}
