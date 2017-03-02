package main.facade;

import main.controller.LojaController;
import main.controller.LojaControllerImpl;
import main.exception.JogoInvalidoException;
import main.exception.UsuarioInvalidoException;
import main.exception.SaldoInsuficienteException;
import main.exception.UsuarioInaptoException;
import main.service.FormatadoraCentralP2Cg;
import util.io.Entrada;
import util.io.Saida;
import util.io.Console;
import util.io.Teclado;

import java.util.HashMap;
import java.util.Set;

/**
 * Classe responsável pela comunicação com o usuario e exibição de menus.
 */
public class LojaFacade {
    private static final String ADICIONAR_USUARIO_MSG = "Adicionar Usuário";
    private static final String ADICIONAR_DINHEIRO_USUARIO_MSG = "Adicionar dinheiro à conta de usuário";
    private static final String VENDER_JOGOS_USUARIO_MSG = "Vender jogo a um usuário";
    private static final String IMPRIMIR_RELATORIO_USUARIOS_MSG = "Imprimir relatório de usuários";
    private static final String USUARIO_NAO_ENCONTRADO_MSG = "Usuário não encontrado";
    private static final String UPGRADE_USUARIO_MSG = "Fazer upgrade de usuário ";
    private static final String SAIR_MSG = "Sair";
    private static final String OPCAO_INVALIDA_MSG = "Opção inválida";

    private static final String INSIRA_UMA_OPCAO_MSG = "Insira uma opção: ";
    private static final String INSIRA_NOME_MSG = "Insira o nome: ";
    private static final String INSIRA_LOGIN_MSG = "Insira o login: ";
    private static final String INSIRA_QUANTIA_MSG = "Insira quanto deseja adicionar à conta de usuário: ";
    private static final String INSIRA_NOME_JOGO_MSG = "Insira o nome do jogo: ";
    private static final String INSIRA_JOGABILIDADE_MSG = "Insira a jogabilidade do jogo (insira \"acabou\", quando terminar): ";
    private static final String INSIRA_PRECO_JOGO_MSG = "Insira o preço do jogo: ";
    private static final String NAO_MSG = "Nao";
    private static final String SIM_MSG = "Sim";
    private static final String INSIRA_TIPO_USUARIO_MSG = "Insira o tipo do usuário(\"Veterano\" ou \"Noob\" ): ";
    private static final String USUARIO_ZEROU_JOGO_MSG = "O usuário zerou o jogo? (" + SIM_MSG + "/" + NAO_MSG + ")";
    private static final String INSIRA_O_SCORE_MSG = "Insira o score: ";
    private static final String INSIRA_TIPO_JOGO_MSG = "Insira o tipo do jogo (Rpg, Luta ou Plataforma): ";
    private static final String RECOMPENSAR_MSG = "Recompensar usuário";
    private static final String REGISTRAR_JOGADA_MSG = "Registrar jogada de usuário";

    private static final String PUNIR_MSG = "Punir usuário ";

    private static final String ACABOU_OP = "acabou";
    private static final int ADICIONAR_USUARIO_OP = 1;
    private static final int ADICIONAR_DINHEIRO_USUARIO_OP = 2;
    private static final int VENDER_JOGOS_USUARIO_OP = 3;
    private static final int IMPRIMIR_RELATORIO_USUARIOS_OP = 4;

    private static final int PUNIR_OP = 5;
    private static final int RECOMPENSAR_OP = 6;


    private static final int UPGRADE_USUARIO_OP = 5;
    private static final int REGISTRAR_JOGADA_OP = 5;
    private static final int SAIR_OP = 7;
    private static final String CENTRAL_P2_CG_MSG = "=== Central P2-CG ===";

    /**
     * Entrada da qual serão lidos os dados.
     */
    private final Entrada entrada;
    /**
     * Saida na qual serão escritos os resultados.
     */
    private final Saida saida;
    /**
     * Controller ao qual será delegada a execução das operações.
     */
    private final LojaController lojaController;

    /**
     * Construtor onde são atribuídas as dependências deste objeto.
     *
     * @param entrada        - {@link Entrada} da qual serão lidos os dados.
     * @param saida          - {@link Saida} na qual serão escritos os resultados.
     * @param lojaController - {@link LojaController} ao qual será delegada a execução das operações.
     */
    public LojaFacade(final Entrada entrada, final Saida saida, LojaController lojaController) {
        this.entrada = entrada;
        this.saida = saida;
        this.lojaController = lojaController;
    }

    public LojaFacade() {
        this.entrada = new Teclado();
        this.saida = new Console();
        this.lojaController = new LojaControllerImpl(new HashMap<>(), new FormatadoraCentralP2Cg());
    }

    /**
     * Inicia o sistema e exibe menus ao usuário.
     */
    public void iniciaSistema() {
        int op;
        do {
            imprimeOpcoes();

            saida.escreve(SAIR_OP + " - " + SAIR_MSG);
            saida.escreve(INSIRA_UMA_OPCAO_MSG);
            op = entrada.leInteiro();

            trataOpcaoInserida(op);
        } while (op != SAIR_OP);
    }

    /**
     * Trada a opção inserida pelo usuário.
     *
     * @param op - Opção inserida pelo usuário.
     */
    private void trataOpcaoInserida(final int op) {
        switch (op) {
            case ADICIONAR_USUARIO_OP:
                criaUsuario();
                break;

            case ADICIONAR_DINHEIRO_USUARIO_OP:
                adicionaCredito();
                break;

            case VENDER_JOGOS_USUARIO_OP:
                vendeJogo();
                break;

            case IMPRIMIR_RELATORIO_USUARIOS_OP:
                imprimeRelatorioUsuario();
                break;

            case PUNIR_OP:
                puneUsuario();
                break;

            case RECOMPENSAR_OP:
                recompensaUsuario();
                break;

            case SAIR_OP:
                break;

            default:
                saida.escreve(OPCAO_INVALIDA_MSG);
        }
    }

    /**
     * * Realiza operações de entrada e saída e recompensar um usuário.
     */
    private void recompensaUsuario() {
        String login = leLogin();
        String nomeJogo = leNomeJogo();
        int score = leScore();
        boolean zerou = leZerou();
        saida.escreve(recompensar(login, nomeJogo, score, zerou));
    }

    /**
     * Recompensa um usuário.
     *
     * @param login       Login do usuário.
     * @param nomeJogo    Nome do jogo jogado.
     * @param scoreObtido Score obtido na jogada.
     * @param zerou       Indica se o usuário zerou o jogo.
     * @return Uma String vazia, caso o usuário tenha sido recompensado.
     * Uma string com a causa da falha, caso contrário.
     */
    public String recompensar(String login, String nomeJogo, int scoreObtido, boolean zerou) {
        try {
            lojaController.recompensar(nomeJogo, login, scoreObtido, zerou);
            return "";
        } catch (JogoInvalidoException | UsuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    /**
     * Realiza operações de entrada e saída e pune um usuário.
     */
    private void puneUsuario() {
        String login = leLogin();
        String nomeJogo = leNomeJogo();
        int score = leScore();
        boolean zerou = leZerou();
        saida.escreve(punir(login, nomeJogo, score, zerou));
    }

    /**
     * Pune um usuário.
     *
     * @param login    Login do usuário a ser punido.
     * @param nomeJogo Nome do jogo jogado.
     * @param score    Score no jogo jogado.
     * @param zerou    Indica se o jogador zerou o jogo.
     * @return Uma string vazia, caso o usuário tenha sido punido.
     * Uma String com a causa da falha, caso contrário.
     */
    public String punir(String login, String nomeJogo, int score, boolean zerou) {
        try {
            lojaController.punir(nomeJogo, login, score, zerou);
            return "";
        } catch (JogoInvalidoException | UsuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    /**
     * Reliza operações de entrada e saída para adicionar um usuário.
     */
    private void criaUsuario() {
        String nome = leNome();
        String login = leLogin();
        String tipo = leTipo();
        criaUsuario(nome, login, tipo);
    }

    /**
     * Cria um usuário e o adiciona aos usuários do controller.
     * Caso o usuário não seja válido, uma mensagem será escrita na saída.
     *
     * @param nome        Nome do usuário.
     * @param login       Login do usuário.
     * @param tipoUsuario Tipo do usuário.
     */
    public void criaUsuario(final String nome, final String login, final String tipoUsuario) {
        try {
            lojaController.adicionaUsuario(nome, login, tipoUsuario);
        } catch (UsuarioInvalidoException usuarioInvalido) {
            saida.escreve(usuarioInvalido.getMessage());
        }
    }

    /**
     * * Reliza operações de entrada e saída para adicionar credito à conta de um usuário.
     */
    private void adicionaCredito() {
        String login = leLogin();
        double quantia = leQuantia();
        adicionaCredito(login, quantia);
    }

    /**
     * Adiciona crédito à conta de um usuário.
     *
     * @param login   Login do usuário.
     * @param credito Quantia a ser adicionada à conta.
     */
    public void adicionaCredito(final String login, final double credito) {
        if (!lojaController.adicionarDinheiroUsuario(login, credito)) {
            saida.escreve(USUARIO_NAO_ENCONTRADO_MSG);
        }
    }

    /**
     * @param login login do usuário.
     */
    public String confereCredito(String login) {
        try {
            return String.format("%.1f", lojaController.confereCredito(login)).replace(",", ".");
        } catch (UsuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    /**
     * Realiza o upgrade de um usuário.
     *
     * @param login login do usuário.
     * @return Uma string vazia, caso o upgrade tenha sido realizado com sucesso.
     * Uma mensagem com a causa da falha, caso não.
     */
    public String upgrade(final String login) {
        try {
            lojaController.upgrade(login);
            return "";
        } catch (UsuarioInvalidoException | UsuarioInaptoException e) {
            return e.getMessage();
        }
    }

    /**
     * Recupera o x2p para um usuário.
     *
     * @param login Login do usuário.
     * @return String com o valor do X2p do usuário que tem {@code login} como login, caso ele exista.
     * Uma mensagem indicando que ele não existe, caso ele não exista.
     */
    public String getX2p(final String login) {
        try {
            return String.valueOf(lojaController.getX2pUsuario(login));
        } catch (UsuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    /**
     * Reliza operações de entrada e saída para imprimir o relatório de usuários desta lojaController.
     */
    private void imprimeRelatorioUsuario() {
        for (String item : lojaController.getRelatorioUsuarios()) {
            saida.escreve(item);
        }
    }

    /**
     * Reliza operações de entrada e saída para vender um jogo a um usuário.
     */
    private void vendeJogo() {
        String login = leLogin();
        String jogo = leNomeJogo();
        String jogabilidades = leJogabilidades();

        double preco = lePrecoJogo();
        String tipo = leTipoJogo();

        vendeJogo(jogo, preco, jogabilidades, tipo, login);
    }

    /**
     * Cria um jogo.
     *
     * @param preco
     * @param jogabilidades
     */
    public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {
        try {
            lojaController.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
        } catch (UsuarioInvalidoException | SaldoInsuficienteException | JogoInvalidoException e) {
            saida.escreve(e.getMessage());
        }
    }

    /**
     * Imprime as opções para o usuário.
     */
    private void imprimeOpcoes() {
        saida.escreve(CENTRAL_P2_CG_MSG);
        saida.escreve(ADICIONAR_USUARIO_OP + " - "
                + ADICIONAR_USUARIO_MSG);
        saida.escreve(ADICIONAR_DINHEIRO_USUARIO_OP + " - "
                + ADICIONAR_DINHEIRO_USUARIO_MSG);
        saida.escreve(VENDER_JOGOS_USUARIO_OP + " - "
                + VENDER_JOGOS_USUARIO_MSG);
        saida.escreve(IMPRIMIR_RELATORIO_USUARIOS_OP + " - "
                + IMPRIMIR_RELATORIO_USUARIOS_MSG);
        saida.escreve(PUNIR_OP + " - "
                + PUNIR_MSG);
        saida.escreve(RECOMPENSAR_OP + " - "
                + RECOMPENSAR_MSG);
    }

    /**
     * @return true, se o valor lido indicar que o jogo foi zerado.
     */
    private boolean leZerou() {
        saida.escreve(USUARIO_ZEROU_JOGO_MSG);
        String resposta = entrada.leString();
        if (SIM_MSG.equalsIgnoreCase(resposta)) {
            return true;
        }
        return false;
    }

    /**
     * Le um score.
     *
     * @return - o score lido.
     */
    private int leScore() {
        saida.escreve(INSIRA_O_SCORE_MSG);
        return entrada.leInteiro();
    }

    /**
     * Lê o tipo do usuário.
     *
     * @return tipo lido.
     */
    private String leTipo() {
        saida.escreve(INSIRA_TIPO_USUARIO_MSG);
        return entrada.leString();
    }

    /**
     * Lê o login de um usuário.
     *
     * @return o login lido.
     */
    private String leLogin() {
        saida.escreve(INSIRA_LOGIN_MSG);
        return entrada.leString();
    }

    /**
     * Lê o nome de um usuário.
     *
     * @return o nome lido.
     */
    private String leNome() {
        saida.escreve(INSIRA_NOME_MSG);
        return entrada.leString();
    }

    /**
     * Lê uma quantia.
     *
     * @return a quantia lida.
     */
    private double leQuantia() {
        saida.escreve(INSIRA_QUANTIA_MSG);
        return entrada.leDouble();
    }

    /**
     * lê o tipo de um jogo.
     *
     * @return - o tipo do jogo.
     */
    private String leTipoJogo() {
        saida.escreve(INSIRA_TIPO_JOGO_MSG);
        return entrada.leString();
    }

    /**
     * Lê o preço de um jogo.
     *
     * @return - o preço do jogo.
     */
    private double lePrecoJogo() {
        saida.escreve(INSIRA_PRECO_JOGO_MSG);
        return entrada.leDouble();
    }

    /**
     * lê o nome de um jogo.
     *
     * @return o nome do jogo lido.
     */
    private String leNomeJogo() {
        saida.escreve(INSIRA_NOME_JOGO_MSG);
        return entrada.leString();
    }

    /**
     * Lê um conjunto de jogabilidades.
     *
     * @return {@link String} com as jogabilidades.
     */
    private String leJogabilidades() {
        saida.escreve(INSIRA_JOGABILIDADE_MSG);
        String jogabilidades = entrada.leString();

        return jogabilidades;
    }
}
