package main.entidade.jogo.tipo;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;

import java.util.Objects;
import java.util.Set;

/**
 * Classe para jogos do tipo Luta.
 * Created by rerissondcsm on 17/02/17.
 */
public class Luta extends Jogo {

    public static final String REPRESENTACAO_STRING = "Luta";
    private static final int FATOR_PONTUACAO_LUTA = 1000;

    private int maiorScoreAnterior;

    /**
     * {@inheritDoc}
     */
    public Luta(final String nome, final double preco,
                final Set<Jogabilidade> jogabilidade) throws JogoInvalidoException {
        super(nome, preco, jogabilidade);
        maiorScoreAnterior = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX2pJogada() {
        if (this.maiorScoreAnterior <= getMaiorScore()) {
            maiorScoreAnterior = getMaiorScore();
            return getMaiorScore() / FATOR_PONTUACAO_LUTA;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Luta{{" +
                "maiorScoreAnterior=" + maiorScoreAnterior +
                '}' + super.toString() + "}";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Luta luta = (Luta) o;
        return maiorScoreAnterior == luta.maiorScoreAnterior;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maiorScoreAnterior);
    }
}
