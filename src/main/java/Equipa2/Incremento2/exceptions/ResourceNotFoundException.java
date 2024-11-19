package Equipa2.Incremento2.exceptions;

/**
 * Exceção lançada quando um recurso não é encontrado.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor que aceita uma mensagem de erro.
     *
     * @param message a mensagem de erro que descreve a exceção.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
