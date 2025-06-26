import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private static final String ARQUIVO_PACIENTES = "pacientes.dat";
    private List<Paciente> pacientes;
    private int proximoId;
    
    public PacienteDAO() {
        this.pacientes = new ArrayList<>();
        this.proximoId = 1;
        carregarPacientes();
    }
    
    public void adicionarPaciente(Paciente paciente) {
        try {
            paciente.setId(proximoId++);
            pacientes.add(paciente);
            salvarPacientes();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar paciente: " + e.getMessage(), e);
        }
    }
    
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes);
    }
    
    public Paciente buscarPorId(int id) {
        try {
            return pacientes.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar paciente: " + e.getMessage(), e);
        }
    }
    
    public void atualizarPaciente(Paciente paciente) {
        try {
            for (int i = 0; i < pacientes.size(); i++) {
                if (pacientes.get(i).getId() == paciente.getId()) {
                    pacientes.set(i, paciente);
                    salvarPacientes();
                    return;
                }
            }
            throw new RuntimeException("Paciente não encontrado");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar paciente: " + e.getMessage(), e);
        }
    }
    
    public void removerPaciente(int id) {
        try {
            pacientes.removeIf(p -> p.getId() == id);
            salvarPacientes();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover paciente: " + e.getMessage(), e);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void carregarPacientes() {
        try {
            File arquivo = new File(ARQUIVO_PACIENTES);
            if (arquivo.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                    pacientes = (List<Paciente>) ois.readObject();
                    if (!pacientes.isEmpty()) {
                        proximoId = pacientes.stream().mapToInt(Paciente::getId).max().orElse(0) + 1;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar pacientes: " + e.getMessage());
            pacientes = new ArrayList<>();
            proximoId = 1;
        }
    }
    
    private void salvarPacientes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_PACIENTES))) {
            oos.writeObject(pacientes);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar pacientes: " + e.getMessage(), e);
        }
    }
} 