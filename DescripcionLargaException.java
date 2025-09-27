
/**
 * Excepción personalizada que se lanza cuando la descripción de un ticket
 * es inválida, ya sea porque está vacía o supera la longitud máxima permitida.
 * 
 * <p>Se utiliza al agregar o editar tickets en la aplicación para validar
 * que la descripción cumpla con las reglas definidas.</p>
 */
public class DescripcionLargaException extends Exception{
	public DescripcionLargaException(String mensaje) {
        super(mensaje);
    }
}
