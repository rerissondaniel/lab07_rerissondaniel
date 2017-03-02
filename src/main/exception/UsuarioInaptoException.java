package main.exception;

/**
 * Exceção para usuário inapto.
 * Created by rerissondcsm on 15/02/17.
 */
public class UsuarioInaptoException extends Exception {
    /**
     * {@inheritDoc}
     */
    public UsuarioInaptoException(String msg) {
        super(msg);
    }
}
