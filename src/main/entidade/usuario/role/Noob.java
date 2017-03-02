package main.entidade.usuario.role;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.entidade.usuario.role.Role;

/**
 * Classe que representa o role do usuário noob.
 *
 * @author rerissondcsm
 */
public class Noob implements Role {

    private static final int X2P_INICIAL_NOOB = 0;
    private static final double DESCONTO_NOOB = 0.10;

    public static final String REPRESENTACAO_STRING = "Noob";

    /**
     * Constantes para recompensa do usuário Noob.
     */
    private static final int RECOMPENSA_JOGO_OFFLINE = 30;
    private static final int RECOMPENSA_JOGO_MULTIPLAYER = 10;

    /**
     * Constantes para punição do usuário Noob.
     */
    private static final int PUNICAO_JOGO_ONLINE = 10;
    private static final int PUNICAO_JOGO_COMPETITIVO = 20;
    private static final int PUNICAO_JOGO_COOPERATIVO = 50;


    /**
     * {@inheritDoc}
     */
    @Override
    public double getDesconto() {
        return DESCONTO_NOOB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getX2pInicial() {
        return X2P_INICIAL_NOOB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX2pPunicao(Jogo jogo) {
        int totalPunicao = 0;

        if (jogo.contemJogabilidade(Jogabilidade.ONLINE)) {
            totalPunicao += PUNICAO_JOGO_ONLINE;
        }
        if (jogo.contemJogabilidade(Jogabilidade.COMPETITIVO)) {
            totalPunicao += PUNICAO_JOGO_COMPETITIVO;
        }
        if (jogo.contemJogabilidade(Jogabilidade.COOPERATIVO)) {
            totalPunicao += PUNICAO_JOGO_COOPERATIVO;
        }

        return totalPunicao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX2pRecompensa(Jogo jogo) {
        int recompensaTotal = 0;

        if (jogo.contemJogabilidade(Jogabilidade.OFFLINE)) {
            recompensaTotal += RECOMPENSA_JOGO_OFFLINE;
        }
        if (jogo.contemJogabilidade(Jogabilidade.MULTIPLAYER)) {
            recompensaTotal += RECOMPENSA_JOGO_MULTIPLAYER;
        }

        return recompensaTotal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return REPRESENTACAO_STRING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getx2pCompra(double precoJogo) {
        return (int) precoJogo * 10;
    }
}
