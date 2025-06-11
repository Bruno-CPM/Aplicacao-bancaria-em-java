import java.util.*;

public class RelatorioController {
    private RelatorioDAO relatorioDAO = new RelatorioDAO();

    public void inserir(Relatorio relatorio) {
        relatorioDAO.inserir(relatorio);
    }

    public void atualizar(Relatorio relatorio) {
        relatorioDAO.atualizar(relatorio);
    }

    public void deletar(int id) {
        relatorioDAO.deletar(id);
    }

    public Relatorio buscarPorId(int id) {
        return relatorioDAO.buscarPorId(id);
    }

    public List<Relatorio> listarTodos() {
        return relatorioDAO.listarTodos();
    }
} 