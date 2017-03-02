package test.unidade.entidade.jogo.tipo;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;
import main.entidade.jogo.tipo.Luta;
import main.entidade.jogo.tipo.Rpg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.util.TestUtils;

import java.util.Set;

/**
 * Testes para {@link Luta}
 * Created by rerissondcsm on 18/02/17.
 */
public class LutaTest {

    private Set<Jogabilidade> jogabilidades;

    @Before
    public void setup() throws JogoInvalidoException {
        jogabilidades = TestUtils.getJogabilidades1();
    }

    @Test
    public void testaRegistraJogada() throws JogoInvalidoException {
        Jogo jogo3 = new Luta("Tekken", 200.00, jogabilidades);
        Assert.assertEquals(1, jogo3.registraJogada(1000, true));
    }

    @Test
    public void testaGetX2pJogada() throws JogoInvalidoException {
        Jogo jogo1 = new Rpg("Final Fantasy XIV", 50.00, jogabilidades);
        Assert.assertEquals(10, jogo1.getX2pJogada());
    }

    @Test
    public void testaGetRepresentacaoString() throws JogoInvalidoException {
        Assert.assertEquals("Luta", Luta.REPRESENTACAO_STRING);
    }
}
