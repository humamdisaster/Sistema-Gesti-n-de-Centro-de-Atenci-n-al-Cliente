/**
 * Excepción que se lanza cuando se intenta asignar
 * un tiempo de respuesta inválido a un ticket en el SistemaAtencion.
 */
public class TiempoRespuestaInvalidoException extends Exception {

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     * @param mensaje descripción del error
     */
    public TiempoRespuestaInvalidoException(String mensaje) {
        super(mensaje);
    }
}
