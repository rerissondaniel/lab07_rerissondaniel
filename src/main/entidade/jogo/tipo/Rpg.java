package main.entidade.jogo.tipo;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;

import java.util.Set;

/**
 * Classe para jogos do tipo RPG.
 * Created by rerissondcsm on 17/02/17.
 */
public class Rpg extends Jogo {

    /**
     * Representação desta classe de jogos como {@link String}.
     */
    public static final String REPRESENTACAO_STRING = "Rpg";

    /**
     * Fator de pontuação para jogos dessa classe.
     */
    private static final int FATOR_PONTUACAO_RPG = 10;

    /**
     * {@inheritDoc}
     */
    public Rpg(final String nome, final double preco,
               final Set<Jogabilidade> jogabilidade) throws JogoInvalidoException {
        super(nome, preco, jogabilidade);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getX2pJogada() {
        return FATOR_PONTUACAO_RPG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Rpg{" + super.toString() + "}";
    }
}
