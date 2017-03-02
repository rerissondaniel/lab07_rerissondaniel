package main.controller;

import java.util.List;
import java.util.Map;

import main.entidade.jogo.Jogo;
import main.exception.JogoInvalidoException;
import main.entidade.usuario.Usuario;
import main.exception.UsuarioInvalidoException;
import main.entidade.usuario.role.Role;
import main.entidade.usuario.role.Noob;
import main.entidade.usuario.role.Veterano;
import main.factory.JogoFactory;
import main.factory.UsuarioFactory;
import main.service.Formatadora;
import main.exception.SaldoInsuficienteException;
import main.exception.UsuarioInaptoException;

/**
 * Implementação de {@link LojaController}. Created by rerissondcsm on 15/02/17.
 */
public class LojaControllerImpl implements LojaController {

    /**
     * Mapa de login para {@link Usuario} desta loja.
     */
    private Map<String, Usuario> usuarios;

    /**
     * {@link Formatadora} responsável por formatar adequadamente o relatório do
     * usuário.
     */
    private Formatadora formatadora;

    /**
     * {@link UsuarioFactory} para criação dos usuários.
     */
    private UsuarioFactory usuarioFactory;

    /**
     * {@link JogoFactory} para criação dos jogos;
     */
    private JogoFactory jogoFactory;

    /**
     * Construtor.
     *
     * @param usuarios    - {@link Map} com valores na forma loginUsuario,usuario.
     * @param formatadora - {@link Formatadora} para formatação de dados.
     */
    public LojaControllerImpl(Map<String, Usuario> usuarios,
                              Formatadora formatadora) {
        this.usuarios = usuarios;
        this.formatadora = formatadora;
        this.usuarioFactory = new UsuarioFactory();
        this.jogoFactory = new JogoFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void adicionaUsuario(final String nome, final String login,
                                final String tipo) throws UsuarioInvalidoException {
        if (!existeUsuario(login)) {
            Usuario usuario = usuarioFactory.criaUsuario(nome, login, tipo);
            usuarios.put(login, usuario);
        } else {
            throw new UsuarioInvalidoException(USUARIO_EXISTENTE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean adicionarDinheiroUsuario(final String login,
                                            final double quantia) {
        Usuario usuario = usuarios.get(login);
        if (usuario == null) {
            return false;
        }
        usuario.adicionaSaldo(quantia);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getRelatorioUsuarios() {
        return this.formatadora.formataDadosUsuario(usuarios.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void punir(String nomeJogo, String login, int score,
                      boolean zerou) throws JogoInvalidoException,
            UsuarioInvalidoException {
        Usuario usuario = usuarios.get(login);
        verificaValidadeUsuario(usuario);
        usuario.punir(nomeJogo, score, zerou);
        ajustaPapelUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void recompensar(String nomeJogo, String login, int score,
                            boolean zerou) throws JogoInvalidoException,
            UsuarioInvalidoException {
        Usuario usuario = usuarios.get(login);
        verificaValidadeUsuario(usuario);
        usuario.recompensar(nomeJogo, score, zerou);
        ajustaPapelUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public double confereCredito(String login) throws UsuarioInvalidoException {
        if (existeUsuario(login)) {
            Usuario usuario = usuarios.get(login);
            return usuario.getCredito();
        }
        throw new UsuarioInvalidoException(USUARIO_NAO_ENCONTRADO);
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public int getX2pUsuario(String login) throws UsuarioInvalidoException {
        if (existeUsuario(login)) {
            Usuario usuario = usuarios.get(login);
            return usuario.getX2p();
        }
        throw new UsuarioInvalidoException(USUARIO_NAO_ENCONTRADO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void vendeJogo(final String jogoNome, final double preco, final String jogabilidades, final String estiloJogo, final String loginUser) throws JogoInvalidoException,
            SaldoInsuficienteException, UsuarioInvalidoException {

        Usuario usuario = usuarios.get(loginUser);
        verificaValidadeUsuario(usuario);

        Jogo jogo = jogoFactory.criaJogo(jogoNome, preco, estiloJogo,
                jogabilidades);

        verificaSaldoSuficiente(usuario, jogo);
        usuario.adicionaJogo(jogo);
        ajustaPapelUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgrade(final String login) throws UsuarioInvalidoException, UsuarioInaptoException {
        if (existeUsuario(login)) {
            Usuario usuario = usuarios.get(login);
            verificaValidadeUsuario(usuario);
            verificaUsuarioAptoUpgrade(usuario);
            upgrade(usuario);
        }
        throw new UsuarioInvalidoException(USUARIO_NAO_ENCONTRADO);
    }

    /**
     * Verifica se o papel do usuário deve ser atualizado e o atualiza, caso necessário.
     *
     * @param usuario {@link Usuario} a ter seu papel ajustado.
     */
    private void ajustaPapelUsuario(Usuario usuario) {
        if (verificaUsuarioAptoDowngrade(usuario)) {
            downgrade(usuario);
        }
        if (verificaUsuarioAptoUpgrade(usuario)) {
            upgrade(usuario);
        }
    }

    /**
     * Verifica a existencia de {@code usuario} entre os usuários desta loja.
     *
     * @param login login do usuário.
     * @throws UsuarioInvalidoException Caso o usuário seja inválido.
     */
    private boolean existeUsuario(final String login)
            throws UsuarioInvalidoException {
        Usuario usuario = usuarios.get(login);
        return usuario != null;
    }

    /**
     * Verifica a validade de um usuário.
     *
     * @param usuario - {@link Usuario} a ser validado
     * @throws UsuarioInvalidoException Caso o usuário não seja válido.
     */
    private void verificaValidadeUsuario(final Usuario usuario)
            throws UsuarioInvalidoException {
        if (usuario == null) {
            throw new UsuarioInvalidoException(USUARIO_NAO_ENCONTRADO);
        }
    }

    /**
     * Verifica se {@code usuario} tem saldo suficiente para a compra de um
     * jogo.
     *
     * @param usuario - {@link Usuario} a ser verificado.
     * @param jogo    - Jogo a ser verificado.
     * @throws SaldoInsuficienteException Caso o usuário não tenha saldo suficiente para a compra do
     *                                    jogo.
     */
    private void verificaSaldoSuficiente(final Usuario usuario, final Jogo jogo)
            throws SaldoInsuficienteException {
        if (usuario.getCredito() < jogo.getPreco() * (1 - usuario.getDesconto())) {
            throw new SaldoInsuficienteException(SALDO_DE_USUARIO_INSUFICIENTE);
        }
    }

    /**
     * Verifica se {@code usuario} é apto ao upgrade.
     *
     * @param usuario {@link Usuario} a ser validado.
     */
    private boolean verificaUsuarioAptoUpgrade(Usuario usuario) {
        Role papel = usuario.getRole();
        if (papel != null && !Noob.class.equals(papel.getClass())) {
            return false;
        } else if (usuario.getX2p() <= X2P_MINIMO_VETERANO) {
            return false;
        }
        return true;
    }

    /**
     * Verifica se {@code usuario} é apto ao downgrade.
     *
     * @param usuario {@link Usuario} a ser validado.
     */
    private boolean verificaUsuarioAptoDowngrade(Usuario usuario) {
        Role papel = usuario.getRole();
        if (papel != null && !Noob.class.equals(papel.getClass())) {
            return false;
        } else if (usuario.getX2p() <= X2P_MINIMO_VETERANO) {
            return false;
        }
        return true;
    }

    /**
     * Realiza o upgrade de um usuário.
     *
     * @param usuario - login do usuário;
     */
    private void upgrade(final Usuario usuario) {
        usuario.setRole(new Veterano());
    }

    /**
     * Realiza o downgrade de um usuário.
     *
     * @param usuario - login do usuário;
     */
    private void downgrade(final Usuario usuario) {
        usuario.setRole(new Noob());
    }
}
