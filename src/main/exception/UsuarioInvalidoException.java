package main.exception;

/**
 * Exceção para usuário inválido.
 */
public class UsuarioInvalidoException extends Exception {

    private static final long serialVersionUID = 4206668353242739893L;

    /**
     * {@inheritDoc}
     */
    public UsuarioInvalidoException(String msg) {
        super(msg);
    }
}
