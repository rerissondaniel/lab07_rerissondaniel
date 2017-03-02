package main.entidade.jogo.tipo;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;

import java.util.Objects;
import java.util.Set;

/**
 * Classe para jogos do tipo Plataforma.
 * * Created by rerissondcsm on 17/02/17.
 */
public class Plataforma extends Jogo {

    /**
     * Representação desta classe de jogos como {@link String}.
     */
    public static final String REPRESENTACAO_STRING = "Plataforma";

    /**
     * Fator de pontuação para jogos de plataforma.
     */
    private static final int FATOR_PONTUACAO_PLATAFORMA = 20;

    /**
     * Quantidade de vezes que o jogo foi zerado anteriormente.
     */
    private int qtdeZerouAnterior;

    /**
     * {@inheritDoc}
     */
    public Plataforma(final String nome, final double preco,
                      final Set<Jogabilidade> jogabilidade) throws JogoInvalidoException {
        super(nome, preco, jogabilidade);
        this.qtdeZerouAnterior = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX2pJogada() {
        if (qtdeZerouAnterior < getQtdeZerado()) {
            qtdeZerouAnterior = getQtdeZerado();
            return FATOR_PONTUACAO_PLATAFORMA;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Plataforma{{" +
                "qtdeZerouAnterior=" + qtdeZerouAnterior +
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
        Plataforma that = (Plataforma) o;
        return qtdeZerouAnterior == that.qtdeZerouAnterior;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), qtdeZerouAnterior);
    }
}
