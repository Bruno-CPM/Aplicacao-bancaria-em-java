import java.util.*;

public class ContaController {
    private ContaDAO contaDAO = new ContaDAO();

    public void inserir(Conta conta) {
        contaDAO.inserir(conta);
    }

    public void atualizar(Conta conta) {
        contaDAO.atualizar(conta);
    }

    public void deletar(int id) {
        contaDAO.deletar(id);
    }

    public Conta buscarPorId(int id) {
        return contaDAO.buscarPorId(id);
    }

    public List<Conta> listarTodos() {
        return contaDAO.listarTodos();
    }
} 