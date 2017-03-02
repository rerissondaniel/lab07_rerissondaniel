package main.controller;

import java.util.List;

import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;
import main.entidade.usuario.Usuario;
import main.exception.UsuarioInvalidoException;
import main.exception.SaldoInsuficienteException;
import main.exception.UsuarioInaptoException;

/**
 * Interface responsável pelas operações da loja.
 */
public interface LojaController {

    String USUARIO_EXISTENTE = "Login já cadastrado";
    String SALDO_DE_USUARIO_INSUFICIENTE = "Saldo do usuário insuficiente";
    String USUARIO_NAO_ENCONTRADO = "O usuário não pode ser encontrado.";
    int X2P_MINIMO_VETERANO = 1000;

    /**
     * Adiciona um {@link Usuario} à loja.
     *
     * @param nome  - Nome do usuário.
     * @param login - Login do usuário.
     * @throws UsuarioInvalidoException Caso o usuário seja inválido.
     */
    void adicionaUsuario(final String nome, final String login,
                         final String tipo) throws UsuarioInvalidoException;

    /**
     * @param login   - Login do usuário.
     * @param quantia - Quantia a ser adicionada à conta.
     * @return {@code true}, caso a operação seja realizada.
     */
    boolean adicionarDinheiroUsuario(final String login, final double quantia);

    /**
     * @return {@link List<String>} com as tuplas do relatório de usuários desta
     * loja.
     */
    List<String> getRelatorioUsuarios();

    /**
     * Vende um jogo a um usuário.
     *
     * @param jogoNome      - Nome do jogo a ser vendido.
     * @param preco         - preço do jogo.
     * @param jogabilidades - String com a jogabilidade do jogo.
     * @throws JogoInvalidoException      Caso o jogo seja inválido.
     * @throws SaldoInsuficienteException Caso o saldo do usuário seja insuficiente
     * @throws UsuarioInvalidoException   Caso o usuário seja inválido.
     */
    void vendeJogo(final String jogoNome, final double preco, final String jogabilidades, final String estiloJogo, final String loginUser) throws JogoInvalidoException,
            SaldoInsuficienteException, UsuarioInvalidoException;

    /**
     * Puni o usuário que tem {@code login} como login,
     * de acordo com o {@link Jogo} que tem {@code nomeJogo} como nome.
     *
     * @param nomeJogo Nome do jogo.
     * @param login    Login do usuário.
     * @param score    Score obtido no jogo.
     * @param zerou    Indica se o usuário zerou o jogo.
     * @throws JogoInvalidoException
     */
    void punir(String nomeJogo, String login, int score,
               boolean zerou) throws JogoInvalidoException, UsuarioInvalidoException;

    /**
     * Recompensa o usuário que tem {@code login} como login,
     * de acordo com o {@link Jogo} que tem {@code nomeJogo} como nome.
     *
     * @param nomeJogo Nome do jogo.
     * @param login    Login do usuário.
     * @param score    Score obtido no jogo.
     * @param zerou    Indica se o usuário zerou o jogo.
     * @throws JogoInvalidoException
     */
    void recompensar(String nomeJogo, String login, int score,
                     boolean zerou) throws JogoInvalidoException, UsuarioInvalidoException;

    /**
     * Realiza o upgrade de um usuário.
     *
     * @param login - login do usuário;
     * @throws UsuarioInvalidoException Caso o login do usuário seja inválido
     * @throws UsuarioInaptoException   caso o usuário seja inapto a upgrade.
     */
    void upgrade(final String login) throws UsuarioInvalidoException, UsuarioInaptoException;

    /**
     * Recupera o crédito de um usuário na loja.
     *
     * @param login Login do usuário.
     * @return O crédito para o usuário que tem {@code login} como login.
     * @throws UsuarioInvalidoException Caso o usuário seja inválido.
     */
    double confereCredito(String login) throws UsuarioInvalidoException;

    /**
     * Recupera a quantidade de x2p para o {@link Usuario} que tem {@code login} como login.
     *
     * @param login Login do usuário.
     * @return quantidade de x2p para o usuário.
     */
    int getX2pUsuario(String login) throws UsuarioInvalidoException;
}
