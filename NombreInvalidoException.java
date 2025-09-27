/**
 * Excepción que se lanza cuando se intenta usar un nombre inválido
 * para un cliente o ticket dentro del SistemaAtencion.
 */
public class NombreInvalidoException extends Exception {

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     * @param mensaje descripción del error
     */
    public NombreInvalidoException(String mensaje) {
        super(mensaje);
    }
}