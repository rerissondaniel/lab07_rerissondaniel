package main.entidade.usuario.role;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.entidade.usuario.role.Role;

/**
 * Classe que representa o Role do usuário veterano.
 *
 * @author rerissondcsm
 */
public class Veterano implements Role {

    private static final double DESCONTO_VETERANO = 0.20;
    private static final int X2P_INICIAL_VETERANO = 1000;
    public static final String REPRESENTACAO_STRING = "Veterano";

    /**
     * Constantes para recompensa do usuário veterano.
     */
    private static final int RECOMPENSA_ONLINE = 10;
    private static final int RECOMPENSA_COOPERATIVO = 20;

    /**
     * Constantes para punição do usuário veterano.
     */
    private static final int PUNICAO_OFFLINE = 20;
    private static final int PUNICAO_COMPETITIVO = 20;

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDesconto() {
        return DESCONTO_VETERANO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getX2pInicial() {
        return X2P_INICIAL_VETERANO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX2pRecompensa(final Jogo jogo) {
        int recompensaTotal = 0;
        if (jogo.contemJogabilidade(Jogabilidade.ONLINE)) {
            recompensaTotal += RECOMPENSA_ONLINE;
        }
        if (jogo.contemJogabilidade(Jogabilidade.COOPERATIVO)) {
            recompensaTotal += RECOMPENSA_COOPERATIVO;
        }
        return recompensaTotal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX2pPunicao(final Jogo jogo) {
        int punicaoTotal = 0;

        if (jogo.contemJogabilidade(Jogabilidade.COMPETITIVO)) {
            punicaoTotal += PUNICAO_COMPETITIVO;
        }
        if (jogo.contemJogabilidade(Jogabilidade.OFFLINE)) {
            punicaoTotal += PUNICAO_OFFLINE;
        }

        return punicaoTotal;
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
        return (int) precoJogo * 15;
    }
}
