/**
* Cliente.java
* Subclase de Persona que representa un cliente del sistema.
*/
public class Cliente extends Persona {
	/**
	* Crea un nuevo cliente.
	* @param id identificador del cliente
	* @param nombre nombre completo
	* @param email correo electrónico
	*/
	public Cliente(String id, String nombre, String email) {
		super(id, nombre, email);
	}
	
	/**
	* Sobreescritura de info() para mostrar detalles propios de Cliente.
	* @return cadena descriptiva del cliente
	*/
	@Override
	public String info() {
		return "Cliente: " + nombre + " [ID: " + id + ", Email: " + email + "]";
	}
	
	/**
     * Muestra información del cliente, con opción detallada.
     * @param detallado si es true incluye todos los campos
     * @return cadena con información del cliente
     */
	public String info(boolean detallado) {
		if (detallado) {
			return "ID: " + id + " Nombre: " + nombre + " Email: " + email;
		}
		return info();
	}
}