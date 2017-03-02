/**
 *
 */
package test.unidade.entidade.jogo;

import java.util.Set;

import main.entidade.jogo.*;

import main.exception.JogoInvalidoException;
import main.entidade.jogo.tipo.Luta;
import main.entidade.jogo.tipo.Rpg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.util.TestUtils;

/**
 * Testes para {@link Jogo}.
 * <p>
 * Created by rerissondcsm on 18/02/2017.
 */
public class JogoTest {

    private Set<Jogabilidade> jogabilidades1, jogabilidades2;

    @Before
    public void setup() throws JogoInvalidoException {
        jogabilidades1 = TestUtils.getJogabilidades1();
        jogabilidades2 = TestUtils.getJogabilidades1();
    }

    @Test(expected = JogoInvalidoException.class)
    public void testaCriacaoNomeNulo() throws JogoInvalidoException {
        Jogo jogo = new Luta(null, 50.00, jogabilidades1);
    }

    @Test(expected = JogoInvalidoException.class)
    public void testaCriacaoNomeVazio() throws JogoInvalidoException {
        Jogo jogo = new Luta("", 50.00, jogabilidades2);
    }

    @Test(expected = JogoInvalidoException.class)
    public void testaCriacaoJogabilidadeNula() throws JogoInvalidoException {
        Jogo jogo = new Luta(null, 50.00, null);
    }

    @Test
    public void testHashCodeEquals() throws JogoInvalidoException {
        Jogo jogo1 = new Rpg("Final Fantasy X", 25.00, jogabilidades2);
        Jogo jogo2 = new Rpg("Final Fantasy VII", 25.00, jogabilidades2);
        Assert.assertNotSame(jogo1, jogo2);
        Jogo aux = new Rpg(jogo1.getNome(), jogo1.getPreco(), jogo1.getJogabilidades());
        Assert.assertEquals(jogo1, aux);
        Assert.assertEquals(jogo1.hashCode(), aux.hashCode());
    }


    @Test
    public void testaRegistraJogada() {
        try {
            Jogo jogo1 = new Rpg("Final Fantasy XIV", 5000, jogabilidades1);
            jogo1.registraJogada(2, true);
            jogo1.registraJogada(5, false);
            jogo1.registraJogada(1, true);
            Assert.assertEquals(5, jogo1.getMaiorScore());
            Assert.assertEquals(2, jogo1.getQtdeZerado());
            Assert.assertEquals(3, jogo1.getQtdeVezesJogadas());
        } catch (JogoInvalidoException e) {
            Assert.fail();
        }
    }

}
