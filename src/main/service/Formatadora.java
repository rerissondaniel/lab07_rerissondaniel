package main.service;

import main.entidade.usuario.Usuario;

import java.util.Collection;
import java.util.List;

/**
 * Interface de formatação de dados.
 * Created by rerissondcsm on 15/02/17.
 */
public interface Formatadora {

    /**
     * Formata os dados do usuário para impressão.
     *
     * @param usuarios - Usuários que serão formatados.
     * @return Uma {@String} formatada com os dados dos usuários.
     */
    List<String> formataDadosUsuario(final Collection<Usuario> usuarios);
}
