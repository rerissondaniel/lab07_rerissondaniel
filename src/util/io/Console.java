package util.io;

import util.io.Saida;

import java.io.PrintStream;

/**
 * Implementação de {@link Saida} como console(terminal).
 * Created by rerissondcsm on 15/02/17.
 */
public class Console implements Saida {
    /**
     * {@link PrintStream} de onde serão lidos os dados.
     */
    private final PrintStream console;

    /**
     * Construtor padrão.
     */
    public Console() {
        this.console = System.out;
    }

    /**
     * {@inheritDoc}
     */
    public void escreve(String str) {
        console.println(str);
    }
}
