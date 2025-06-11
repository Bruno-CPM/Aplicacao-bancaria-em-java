import java.util.*;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void inserir(Usuario usuario) {
        usuarioDAO.inserir(usuario);
    }

    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public void deletar(int id) {
        usuarioDAO.deletar(id);
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }
} 