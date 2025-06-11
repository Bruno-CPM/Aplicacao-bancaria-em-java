import java.util.*;

public class EnderecoController {
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public void inserir(Endereco endereco) {
        enderecoDAO.inserir(endereco);
    }

    public void atualizar(Endereco endereco) {
        enderecoDAO.atualizar(endereco);
    }

    public void deletar(int id) {
        enderecoDAO.deletar(id);
    }

    public Endereco buscarPorId(int id) {
        return enderecoDAO.buscarPorId(id);
    }

    public List<Endereco> listarTodos() {
        return enderecoDAO.listarTodos();
    }
} 