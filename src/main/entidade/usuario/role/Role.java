package main.entidade.usuario.role;

import main.entidade.jogo.Jogo;

/**
 * Classe que representa o papel deste usuário no sistema.
 *
 * @author rerissondcsm
 */
public interface Role {
    /**
     * Método que retorna o desconto para este usuário.
     *
     * @return O valor decimal do desconto para este usuário. ex.:(0.10, 0.25, etc.).
     */
    double getDesconto();

    /**
     * Retorna o x2p para determinada compra.
     *
     * @param precoJogo
     * @return
     */
    int getx2pCompra(final double precoJogo);

    /**
     * Altera o valor inicial do x2p do Usuário.
     */
    int getX2pInicial();

    /**
     * Recupera o valor da recompensa com base em {@code jogo}.
     *
     * @param jogo {@link Jogo} avaliado para decisão do valor da recompensa.
     * @return O valor da recompensa para o jogo.
     */
    int getX2pRecompensa(final Jogo jogo);

    /**
     * Recupera o valor da punição com base em {@code jogo}.
     *
     * @param jogo {@link Jogo} avaliado para decisão do valor da punição.
     * @return O punição da recompensa para o jogo.
     */
    int getX2pPunicao(final Jogo jogo);
}
