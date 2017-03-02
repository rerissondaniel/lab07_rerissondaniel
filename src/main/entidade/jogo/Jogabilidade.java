package main.entidade.jogo;

/**
 * Enum que representa as possíveis jogabilidades para um jogo.
 *
 * @author rerissondcsm
 */
public enum Jogabilidade {
    ONLINE("Online"), OFFLINE("Offline"), MULTIPLAYER("Multiplayer"),
    COOPERATIVO("Cooperativo"), COMPETITIVO("Competitivo");

    /**
     * Estilo da jogabilidade.
     */
    private String estilo;

    /**
     * Construtor privado.
     *
     * @param estilo - Estilo deste jogabilidade.
     */
    Jogabilidade(String estilo) {
        this.estilo = estilo;
    }

    /**
     * Recupera o estilo desta jogabilidade.
     *
     * @return - {@link String} representando o estilo desse jogo.
     */
    public String getEstilo() {
        return estilo;
    }

    /**
     * Retorna a jogabilidade a partir de {@code estilo}.
     *
     * @param estilo - estilo da jogabilidade a ser recuperada.
     * @return uma {@link Jogabilidade}, caso alguma das jogabilidades tenha {@code estilo} como estilo. {@code null}
     * caso contrário.
     */
    public static Jogabilidade getPorEstilo(String estilo) {
        for (Jogabilidade jogabilidade : values()) {
            if (jogabilidade.getEstilo().equalsIgnoreCase(estilo)) {
                return jogabilidade;
            }
        }
        return null;
    }
}
