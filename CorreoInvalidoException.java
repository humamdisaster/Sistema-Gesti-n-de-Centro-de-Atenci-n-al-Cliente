
/**
 * Excepción personalizada que se lanza cuando se detecta
 * un correo electrónico inválido dentro del sistema.
 * 
 * <p>Se puede usar al agregar o editar clientes para validar
 * que el correo no esté vacío o no se repita.</p>
 */
public class CorreoInvalidoException extends Exception{
	public CorreoInvalidoException(String mensaje) {
		super(mensaje);
	}
}
