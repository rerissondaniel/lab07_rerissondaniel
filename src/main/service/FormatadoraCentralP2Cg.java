package main.service;

import main.entidade.jogo.Jogo;
import main.entidade.usuario.Usuario;
import main.service.Formatadora;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementação de {@link Formatadora} para a CentralP2Cg.
 * Created by rerissondcsm on 15/02/17.
 */
public class FormatadoraCentralP2Cg implements Formatadora {

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<String> formataDadosUsuario(final Collection<Usuario> usuarios) {
        List<String> relatorio = new ArrayList<>();
        double total = 0;
        relatorio.add("=== Central P2-CG ===" + System.lineSeparator());
        for (Usuario usuario : usuarios) {
            String dadosUsuario = getStringFormatadaUsuario(usuario);
            total = 0;
            for (Jogo jogo : usuario.getJogosComprados()) {
                dadosUsuario += getStringFormatadaJogo(jogo);
                total += jogo.getPreco();
            }
            relatorio.add(dadosUsuario);
            String totalStr = "Total de preço dos jogos: " + String.format("%.2f", total) + System.lineSeparator() + System.lineSeparator();
            totalStr += "--------------------------------------------" + System.lineSeparator();
            relatorio.add(totalStr);
        }

        return relatorio;
    }

    /**
     * Formata os dados de {@code usuario}.
     *
     * @param usuario {@link Usuario} a ter os dados formatados.
     * @return - {@link String} com os dados formatados do usuário.
     */
    private String getStringFormatadaUsuario(final Usuario usuario) {
        String dadosUsuario = usuario.getLogin() + System.lineSeparator();
        dadosUsuario += usuario.getNome() + " - " + usuario.getRole() + System.lineSeparator() + System.lineSeparator();
        dadosUsuario += "Lista de Jogos:" + System.lineSeparator();
        return dadosUsuario;
    }

    /**
     * Formata os dados de {@code jogo}.
     *
     * @param jogo - {@link Jogo} a ser formatado.
     * @return - {@link String} formatada do jogo.
     */
    private String getStringFormatadaJogo(final Jogo jogo) {
        String dadosUsuario = "";
        dadosUsuario += "+ " + jogo.getNome() + System.lineSeparator();
        dadosUsuario += "==> Jogou " + jogo.getQtdeVezesJogadas() + " vez(es)" + System.lineSeparator();
        dadosUsuario += "==> Zerou " + jogo.getQtdeZerado() + " vez(es)" + System.lineSeparator();
        dadosUsuario += "==> Maior score: " + jogo.getMaiorScore() + "" + System.lineSeparator();
        return dadosUsuario;
    }
}
