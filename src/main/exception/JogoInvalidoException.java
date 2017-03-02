package main.exception;

/**
 * Exceção para jogos inválidos.
 */
public class JogoInvalidoException extends Exception {

    private static final long serialVersionUID = 6865582828578518622L;

    /**
     * {@inheritDoc}
     */
    public JogoInvalidoException(String msg) {
        super(msg);
    }
}
