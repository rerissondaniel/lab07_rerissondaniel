package main.factory;

import java.util.HashMap;

import main.entidade.usuario.Usuario;
import main.exception.UsuarioInvalidoException;
import main.entidade.usuario.role.Noob;
import main.entidade.usuario.role.Veterano;

/**
 * Classe responsável pela criação de usuários.
 *
 * @author rerissondcsm
 */
public class UsuarioFactory {

    /**
     * Cria um usuario a partir dos parâmetros.
     *
     * @param nome  Nome do usuário a ser criado.
     * @param login Login do usuário a ser criado.
     * @param tipo  Tipo do usuário a ser criado.
     * @return {@link Usuario} criado.
     * @throws UsuarioInvalidoException Caso algum dos dados seja inválido.
     */
    public Usuario criaUsuario(String nome, String login, String tipo) throws UsuarioInvalidoException {
        Usuario usuario;
        if (Noob.REPRESENTACAO_STRING.equalsIgnoreCase(tipo)) {
            usuario = new Usuario(nome, login, new HashMap<>(), new Noob());
        } else if (Veterano.REPRESENTACAO_STRING.equalsIgnoreCase(tipo)) {
            usuario = new Usuario(nome, login, new HashMap<>(), new Veterano());
        } else {
            usuario = new Usuario(nome, login, new HashMap<>(), null);
        }
        return usuario;
    }
}
