package util;

/**
 * Classe utilitária de propósito geral.
 */

public class Util {
    private Util() {

    }

    /**
     * Verifica se uma {@link String} é nula ou vazia.
     *
     * @param str {@link String} a ser verificada.
     * @return {@code true} se str for {@code null} ou vazia.
     */
    public static boolean ehNulaOuVazia(final String str) {
        return (str == null || "".equals(str.trim()));
    }
}
