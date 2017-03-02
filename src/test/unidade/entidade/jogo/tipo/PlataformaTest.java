package test.unidade.entidade.jogo.tipo;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;
import main.entidade.jogo.tipo.Plataforma;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.util.TestUtils;

import java.util.Set;

/**
 * Testes para {@link Plataforma}
 * Created by rerissondcsm on 18/02/17.
 */
public class PlataformaTest {

    private Set<Jogabilidade> jogabilidades;

    @Before
    public void setup() throws JogoInvalidoException {
        jogabilidades = TestUtils.getJogabilidades1();
    }


    @Test
    public void testaRegistraJogada() throws JogoInvalidoException {
        Jogo jogo2 = new Plataforma("Valkyrie Profile", 200.00, jogabilidades);
        Assert.assertEquals(20, jogo2.registraJogada(1000, true));
    }

    @Test
    public void testaGetX2pJogada() throws JogoInvalidoException {
        Jogo jogo2 = new Plataforma("Valkyrie Profile", 200.00, jogabilidades);
        Assert.assertEquals(0, jogo2.getX2pJogada());
        Assert.assertEquals(20, jogo2.registraJogada(1000, true));
    }

    @Test
    public void testaGetRepresentacaoString() throws JogoInvalidoException {
        Assert.assertEquals("Plataforma", Plataforma.REPRESENTACAO_STRING);
    }
}
