package test.unidade.service;

import main.entidade.usuario.Usuario;
import main.exception.UsuarioInvalidoException;
import main.service.Formatadora;
import main.service.FormatadoraCentralP2Cg;
import org.junit.Assert;
import org.junit.Test;
import test.util.TestUtils;

import java.util.Collection;
import java.util.List;

/**
 * Testes para {@link FormatadoraCentralP2Cg}
 * Created by rerissondcsm on 18/02/17.
 */
public class FormatadoraCentralP2CgTest {

    @Test
    public void formataDadosUsuario() throws UsuarioInvalidoException {
        Formatadora formatadora = new FormatadoraCentralP2Cg();
        Collection<Usuario> usuarios = TestUtils.getUsuarios();
        List<String> relatorio = formatadora.formataDadosUsuario(usuarios);
        String resultado = "";
        for (String dadosUsuario : relatorio) {
            resultado += dadosUsuario;
        }
        Assert.assertEquals("=== Central P2-CG ===" + System.lineSeparator() +
                "antonio" + System.lineSeparator() +
                "Antonio - Veterano" + System.lineSeparator() + System.lineSeparator() +
                "Lista de Jogos:" + System.lineSeparator() +
                "Total de preço dos jogos: 0,00" + System.lineSeparator() + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "joao" + System.lineSeparator() +
                "Joao - Veterano" + System.lineSeparator() + System.lineSeparator() +
                "Lista de Jogos:" + System.lineSeparator() +
                "Total de preço dos jogos: 0,00" + System.lineSeparator() + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "pedro" + System.lineSeparator() +
                "Pedro - Veterano" + System.lineSeparator() + System.lineSeparator() +
                "Lista de Jogos:" + System.lineSeparator() +
                "Total de preço dos jogos: 0,00" + System.lineSeparator() + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "mauro" + System.lineSeparator() +
                "Mauro - Noob" + System.lineSeparator() + System.lineSeparator() +
                "Lista de Jogos:" + System.lineSeparator() +
                "Total de preço dos jogos: 0,00" + System.lineSeparator() + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "jose" + System.lineSeparator() +
                "Jose - Noob" + System.lineSeparator() + System.lineSeparator() +
                "Lista de Jogos:" + System.lineSeparator() +
                "Total de preço dos jogos: 0,00" + System.lineSeparator() + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator(), resultado);
    }
}
