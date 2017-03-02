package main.factory;

import java.util.HashSet;
import java.util.Set;

import main.entidade.jogo.Jogabilidade;
import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;
import main.entidade.jogo.tipo.Luta;
import main.entidade.jogo.tipo.Plataforma;
import main.entidade.jogo.tipo.Rpg;

/**
 * Classe responsável pela criação de jogos.
 *
 * @author rerissondcsm
 */
public class JogoFactory {

    /**
     * Template para a mensagem de jogabilidade inexistente.
     */
    private static final String TEMPLATE_JOGABILIDADE_NAO_EXISTENTE = "Jogo inválido, a jogabilidade  \"%s\" não existe.";

    private static final String TIPO_JOGO_NAO_ENCONTRADO = "Não há o tipo de jogo indicado";

    /**
     * Cria um jogo a partir dos atributos passados como parâmetro.
     *
     * @param nomeJogo         Nome do jogo a ser criado.
     * @param preco            Preço do jogo a ser criado.
     * @param tipo             Tipo do jogo a ser criado.
     * @param jogabilidadesStr {@link Set<String>} das jogabilidades do jogo a ser criado.
     * @return
     * @throws JogoInvalidoException - Caso o tipo do jogo não exista no sistema.
     */
    public Jogo criaJogo(final String nomeJogo, final double preco, final String tipo,
                         final String jogabilidadesStr) throws JogoInvalidoException {
        Jogo jogo;

        Set<Jogabilidade> jogabilidades = criaJogabilidades(jogabilidadesStr);

        if (tipo.equalsIgnoreCase(Luta.REPRESENTACAO_STRING)) {
            jogo = new Luta(nomeJogo, preco, jogabilidades);
        } else if (tipo.equalsIgnoreCase(Rpg.REPRESENTACAO_STRING)) {
            jogo = new Rpg(nomeJogo, preco, jogabilidades);
        } else if (tipo.equalsIgnoreCase(Plataforma.REPRESENTACAO_STRING)) {
            jogo = new Plataforma(nomeJogo, preco, jogabilidades);
        } else {
            throw new JogoInvalidoException(TIPO_JOGO_NAO_ENCONTRADO);
        }

        return jogo;
    }

    /**
     * Cria um {@link Set<Jogabilidade>} a partir de {@code jogabilidadesStr}.
     *
     * @param jogabilidadesStr {@link String} contendo as jogabilidades.
     * @return {@link Set<Jogabilidade>}
     * @throws JogoInvalidoException Caso alguma das jogabilidaes não exista.
     */
    private Set<Jogabilidade> criaJogabilidades(String jogabilidadesStr) throws JogoInvalidoException {
        Set<Jogabilidade> jogabilidades = new HashSet<>();

        for (String jogabilidade : jogabilidadesStr.split(" ")) {
            Jogabilidade jogabilidadeAux = Jogabilidade.getPorEstilo(jogabilidade);
            if (jogabilidadeAux == null) {
                throw new JogoInvalidoException(String.format(TEMPLATE_JOGABILIDADE_NAO_EXISTENTE, jogabilidade));
            }
            jogabilidades.add(jogabilidadeAux);
        }
        return jogabilidades;
    }
}
