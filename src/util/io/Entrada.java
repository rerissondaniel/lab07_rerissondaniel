package util.io;

/**
 * Interface de entrada do sistema.
 * Created by rerissondcsm on 15/02/17.
 */
public interface Entrada {
    /**
     * Lê um {@link Integer} da entrada.
     *
     * @return - O {@link Integer} lido.
     */
    Integer leInteiro();

    /**
     * Lê uma {@link String} da entrada.
     *
     * @return a {@link String} lida.
     */
    String leString();

    /**
     * Lê um {@link Double} da entrada.
     *
     * @return - {@link Double} lido.
     */
    Double leDouble();
}
