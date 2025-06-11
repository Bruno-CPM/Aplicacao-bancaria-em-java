import java.util.*;

public class AgenciaController {
    private AgenciaDAO agenciaDAO = new AgenciaDAO();

    public void inserir(Agencia agencia) {
        agenciaDAO.inserir(agencia);
    }

    public void atualizar(Agencia agencia) {
        agenciaDAO.atualizar(agencia);
    }

    public void deletar(int id) {
        agenciaDAO.deletar(id);
    }

    public Agencia buscarPorId(int id) {
        return agenciaDAO.buscarPorId(id);
    }

    public List<Agencia> listarTodos() {
        return agenciaDAO.listarTodos();
    }
} 