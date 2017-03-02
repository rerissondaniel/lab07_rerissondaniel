package test.unidade.entidade.usuario.role;

import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;
import main.entidade.usuario.Usuario;
import main.exception.UsuarioInvalidoException;
import main.entidade.usuario.role.Noob;
import org.junit.Assert;
import org.junit.Test;
import test.util.TestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Testes para {@link Noob}
 * Created by rerisson on 18/02/17.
 */
public class NoobTest {

    @Test
    public void testaCriacaoUsuarioNoobValido() throws UsuarioInvalidoException {
        Usuario usuario = new Usuario("Joao", "joao", new HashMap<>(), new Noob());
        Assert.assertEquals(0, usuario.getX2p());
    }

    @Test
    public void testaAdicionaJogoNoob() throws UsuarioInvalidoException, JogoInvalidoException {
        Usuario usuario = new Usuario("Joao", "joao", new HashMap<>(), new Noob());
        Map<String, Jogo> jogos = TestUtils.getJogos();
        Jogo tekken = jogos.get("Tekken");
        double saldoAnterior = usuario.getCredito();

        usuario.adicionaJogo(tekken);

        Assert.assertEquals((int) (10 * tekken.getPreco()), usuario.getX2p());
        Assert.assertEquals(saldoAnterior, usuario.getCredito() +
                (tekken.getPreco() - tekken.getPreco() * usuario.getDesconto()), 0.005);
    }
}
