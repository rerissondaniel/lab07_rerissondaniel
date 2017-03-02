package util.io;

import util.io.Entrada;

import java.util.Scanner;

/**
 * Implementação de {@link Entrada} do teclado.
 */
public class Teclado implements Entrada {
    /**
     * Teclado de onde serão lidos os dados.
     */
    private Scanner teclado = new Scanner(System.in);

    /**
     * {@inheritDoc}
     */
    public Integer leInteiro() {
        String numeroLido = teclado.nextLine();
        if ("".equals(numeroLido)) {
            return 0;
        }
        return Integer.parseInt(numeroLido);
    }

    /**
     * {@inheritDoc}
     */
    public String leString() {
        return teclado.nextLine();
    }

    /**
     * {@inheritDoc}
     */
    public Double leDouble() {
        String numeroLido = teclado.nextLine();
        return Double.parseDouble(numeroLido.replace(",", "."));
    }
}
