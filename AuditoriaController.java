import java.util.*;

public class AuditoriaController {
    private AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

    public void inserir(Auditoria auditoria) {
        auditoriaDAO.inserir(auditoria);
    }

    public void atualizar(Auditoria auditoria) {
        auditoriaDAO.atualizar(auditoria);
    }

    public void deletar(int id) {
        auditoriaDAO.deletar(id);
    }

    public Auditoria buscarPorId(int id) {
        return auditoriaDAO.buscarPorId(id);
    }

    public List<Auditoria> listarTodos() {
        return auditoriaDAO.listarTodos();
    }
} 