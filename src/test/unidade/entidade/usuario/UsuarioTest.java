package test.unidade.entidade.usuario;

import main.exception.JogoInvalidoException;
import main.entidade.usuario.Usuario;
import main.exception.UsuarioInvalidoException;
import main.entidade.usuario.role.Noob;
import main.entidade.usuario.role.Veterano;
import org.junit.Assert;
import org.junit.Test;
import test.util.TestUtils;

import java.util.HashMap;

/**
 * Testes para {@link Usuario} e suas respectivas filhas.
 * Created by rerissondcsm on 18/02/17.
 */
public class UsuarioTest {

    @Test(expected = UsuarioInvalidoException.class)
    public void testaCriacaoUsuarioNomeNulo() throws UsuarioInvalidoException {
        Usuario usuario = new Usuario(null, "joao", new HashMap<>(), new Noob());
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void testaCriacaoUsuarioNomeVazio() throws UsuarioInvalidoException {
        Usuario usuario = new Usuario("   ", "joao", new HashMap<>(), new Noob());
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void testaCriacaoUsuarioLoginNulo() throws UsuarioInvalidoException {
        Usuario usuario = new Usuario("Joao", null, new HashMap<>(), new Veterano());
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void testaCriacaoUsuarioLoginVazio() throws UsuarioInvalidoException {
        Usuario usuario = new Usuario("Joao", "   ", new HashMap<>(), new Noob());
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void testaCriacaoUsuarioSemRole() throws UsuarioInvalidoException {
        Usuario usuario = new Usuario("Joao", "joao", new HashMap<>(), null);
    }

    @Test
    public void testaRegistraJogada() throws UsuarioInvalidoException, JogoInvalidoException {
        Usuario usuario = new Usuario("Joao", "joao", TestUtils.getJogos(), new Noob());
        //usuario.registraJogada("Tekken", 10, true);
        Assert.assertEquals(0, usuario.getX2p());

        //usuario.registraJogada("Final Fantasy X", 50, false);
        Assert.assertEquals(10, usuario.getX2p());

        //usuario.registraJogada("GameX", 10, true);
        Assert.assertEquals(30, usuario.getX2p());
    }
}
