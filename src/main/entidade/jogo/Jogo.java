package main.entidade.jogo;

import java.util.HashSet;
import java.util.Set;

import main.exception.JogoInvalidoException;
import util.Util;

/**
 * Classe que representa um jogo.
 *
 * @author rerissondcsm
 */
public abstract class Jogo {
    /**
     * Nome deste jogo.
     */
    private String nome;
    /**
     * Preço para compra deste jogo.
     */
    private double preco;

    /**
     * Maior score realizado por um jogador deste jogo.
     */
    private int maiorScore;

    /**
     * Quantidade de vezes que este jogo foi jogada.
     */
    private int qtdeVezesJogadas;

    /**
     * Quantidade de vezes que este jogo foi "zerado" (concluido pelo jogador).
     */
    private int qtdeZerado;

    /**
     * Indica o estilo de jogabilidades do jogo. Veja {@link Jogabilidade}
     */
    private Set<Jogabilidade> jogabilidades;

    /**
     * Construtor.
     *
     * @param nome          - O nome deste jogo.
     * @param preco         - O preço deste jogo.
     * @param jogabilidades - O conjunto de {@link Jogabilidade} deste jogo.
     */
    public Jogo(final String nome, final double preco,
                final Set<Jogabilidade> jogabilidades) throws JogoInvalidoException {
        if (Util.ehNulaOuVazia(nome)) {
            throw new JogoInvalidoException("O nome do jogo não pode ser vazio.");
        }
        if (jogabilidades == null) {
            throw new JogoInvalidoException("A jogabilidades do jogo não pode ser nula.");
        }
        this.nome = nome;
        this.preco = preco;
        this.maiorScore = 0;
        this.jogabilidades = jogabilidades;
    }

    /**
     * Registra uma jogada. Caso o {@code score} seja maior que
     * {@code maiorScore}, {@code maiorScore} passa a ter o valor de score. Caso
     * {@code zerou} seja true, {@code qtdeZerado} será incrementada em 1.
     *
     * @param score - Score do jogador na jogada.
     * @param zerou - Indica se o jogador zerou o jogo.
     * @return a quantiddade de x2p dessa jogada.
     */
    public int registraJogada(final int score, final boolean zerou) {
        this.maiorScore = Math.max(score, this.maiorScore);
        this.qtdeVezesJogadas++;
        if (zerou) {
            this.qtdeZerado++;
        }
        return getX2pJogada();
    }

    /**
     * @return o x2p para determinada jogada
     */
    public abstract int getX2pJogada();

    /**
     * Recupera o preço deste jogo.
     *
     * @return O preço deste jogo.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Recupera o conjunto de jogabilidades deste jogo. Caso o conjunto de
     * jogabilidades seja null, retorna um HashSet vazio para evitar
     * {@link NullPointerException}.
     *
     * @return - O conjunto de jogabilidades deste jogo.
     */
    public Set<Jogabilidade> getJogabilidades() {
        if (jogabilidades == null) {
            jogabilidades = new HashSet<>();
        }
        return jogabilidades;
    }

    /**
     * @param jogabilidade
     * @return {@code true}, caso este jogo tenha {@code jogabilidade}.
     */
    public boolean contemJogabilidade(Jogabilidade jogabilidade) {
        return jogabilidades.contains(jogabilidade);
    }

    /**
     * Recupera o nome deste jogo.
     *
     * @return {@link String} o nome deste jogo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera o maior score para este jogo.
     *
     * @return O maior escore para este jogo.
     */
    public int getMaiorScore() {
        return maiorScore;
    }

    /**
     * Recupera a quantidade de vezes que este jogo foi jogado.
     *
     * @return A quantidade de vezes que este jogo foi jogado.
     */
    public int getQtdeVezesJogadas() {
        return qtdeVezesJogadas;
    }

    /**
     * Recupera a quantidade de vezes que este jogo foi zerado.
     *
     * @return A quantidade de vezes que este jogo foi zerado.
     */
    public int getQtdeZerado() {
        return qtdeZerado;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((jogabilidades == null) ? 0 : jogabilidades.hashCode());
        result = prime * result + maiorScore;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        long temp;
        temp = Double.doubleToLongBits(preco);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + qtdeVezesJogadas;
        result = prime * result + qtdeZerado;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jogo other = (Jogo) obj;
        if (jogabilidades == null) {
            if (other.jogabilidades != null)
                return false;
        } else if (!jogabilidades.equals(other.jogabilidades))
            return false;
        if (maiorScore != other.maiorScore)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (Double.doubleToLongBits(preco) != Double
                .doubleToLongBits(other.preco))
            return false;
        if (qtdeVezesJogadas != other.qtdeVezesJogadas)
            return false;
        if (qtdeZerado != other.qtdeZerado)
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Jogo [nome=" + nome + ", preco=" + preco + ", maiorScore="
                + maiorScore + ", qtdeVezesJogadas=" + qtdeVezesJogadas
                + ", qtdeZerado=" + qtdeZerado + ", jogabilidades="
                + jogabilidades + "]";
    }
}
