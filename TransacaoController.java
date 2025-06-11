import java.util.*;

public class TransacaoController {
    private TransacaoDAO transacaoDAO = new TransacaoDAO();

    public void inserir(Transacao transacao) {
        transacaoDAO.inserir(transacao);
    }

    public void atualizar(Transacao transacao) {
        transacaoDAO.atualizar(transacao);
    }

    public void deletar(int id) {
        transacaoDAO.deletar(id);
    }

    public Transacao buscarPorId(int id) {
        return transacaoDAO.buscarPorId(id);
    }

    public List<Transacao> listarTodos() {
        return transacaoDAO.listarTodos();
    }
} 