import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaListarPacientes extends JFrame {
    private PacienteDAO pacienteDAO;
    private JTable tabelaPacientes;
    private DefaultTableModel modeloTabela;
    private JTextField txtBusca;
    
    public TelaListarPacientes(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
        inicializarInterface();
    }
    
    private void inicializarInterface() {
        setTitle("Listar Pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Título
        JLabel titulo = new JLabel("Lista de Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        // Painel de busca
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBusca.add(new JLabel("Buscar por nome:"));
        txtBusca = new JTextField(20);
        painelBusca.add(txtBusca);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });
        painelBusca.add(btnBuscar);
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBusca.setText("");
                atualizarLista();
            }
        });
        painelBusca.add(btnLimpar);
        
        painelPrincipal.add(painelBusca, BorderLayout.CENTER);
        
        // Tabela
        String[] colunas = {"ID", "Nome", "CPF", "Data Nascimento", "Telefone", "Email", "Cidade", "Estado"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabela não editável
            }
        };
        
        tabelaPacientes = new JTable(modeloTabela);
        tabelaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPacientes.getTableHeader().setReorderingAllowed(false);
        
        // Configurar larguras das colunas
        tabelaPacientes.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        tabelaPacientes.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
        tabelaPacientes.getColumnModel().getColumn(2).setPreferredWidth(120); // CPF
        tabelaPacientes.getColumnModel().getColumn(3).setPreferredWidth(100); // Data Nascimento
        tabelaPacientes.getColumnModel().getColumn(4).setPreferredWidth(100); // Telefone
        tabelaPacientes.getColumnModel().getColumn(5).setPreferredWidth(150); // Email
        tabelaPacientes.getColumnModel().getColumn(6).setPreferredWidth(100); // Cidade
        tabelaPacientes.getColumnModel().getColumn(7).setPreferredWidth(60);  // Estado
        
        JScrollPane scrollPane = new JScrollPane(tabelaPacientes);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton btnVisualizar = new JButton("Visualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar Lista");
        JButton btnFechar = new JButton("Fechar");
        
        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarPaciente();
            }
        });
        
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPaciente();
            }
        });
        
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirPaciente();
            }
        });
        
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLista();
            }
        });
        
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnFechar);
        
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
        // Duplo clique para visualizar
        tabelaPacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    visualizarPaciente();
                }
            }
        });
        
        add(painelPrincipal);
        
        // Carregar dados iniciais
        atualizarLista();
    }
    
    public void atualizarLista() {
        try {
            modeloTabela.setRowCount(0);
            List<Paciente> pacientes = pacienteDAO.listarTodos();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            for (Paciente paciente : pacientes) {
                Object[] linha = {
                    paciente.getId(),
                    paciente.getNome(),
                    formatarCpf(paciente.getCpf()),
                    paciente.getDataNascimento().format(formatter),
                    paciente.getTelefone(),
                    paciente.getEmail(),
                    paciente.getCidade(),
                    paciente.getEstado()
                };
                modeloTabela.addRow(linha);
            }
            
            // Atualizar título com contagem
            setTitle("Listar Pacientes - Total: " + pacientes.size());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar lista: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarPacientes() {
        try {
            String termoBusca = txtBusca.getText().trim().toLowerCase();
            if (termoBusca.isEmpty()) {
                atualizarLista();
                return;
            }
            
            modeloTabela.setRowCount(0);
            List<Paciente> pacientes = pacienteDAO.listarTodos();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            int contador = 0;
            
            for (Paciente paciente : pacientes) {
                if (paciente.getNome().toLowerCase().contains(termoBusca)) {
                    Object[] linha = {
                        paciente.getId(),
                        paciente.getNome(),
                        formatarCpf(paciente.getCpf()),
                        paciente.getDataNascimento().format(formatter),
                        paciente.getTelefone(),
                        paciente.getEmail(),
                        paciente.getCidade(),
                        paciente.getEstado()
                    };
                    modeloTabela.addRow(linha);
                    contador++;
                }
            }
            
            setTitle("Listar Pacientes - Encontrados: " + contador);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro na busca: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Paciente getPacienteSelecionado() {
        int linhaSelecionada = tabelaPacientes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Selecione um paciente na tabela", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        int id = (Integer) modeloTabela.getValueAt(linhaSelecionada, 0);
        return pacienteDAO.buscarPorId(id);
    }
    
    private void visualizarPaciente() {
        Paciente paciente = getPacienteSelecionado();
        if (paciente == null) return;
        
        try {
            StringBuilder detalhes = new StringBuilder();
            detalhes.append("=== DETALHES DO PACIENTE ===\n\n");
            detalhes.append("ID: ").append(paciente.getId()).append("\n");
            detalhes.append("Nome: ").append(paciente.getNome()).append("\n");
            detalhes.append("CPF: ").append(formatarCpf(paciente.getCpf())).append("\n");
            detalhes.append("Data de Nascimento: ").append(paciente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
            detalhes.append("Telefone: ").append(paciente.getTelefone()).append("\n");
            detalhes.append("Email: ").append(paciente.getEmail()).append("\n");
            detalhes.append("Endereço: ").append(paciente.getEndereco()).append("\n");
            detalhes.append("Cidade: ").append(paciente.getCidade()).append("\n");
            detalhes.append("Estado: ").append(paciente.getEstado()).append("\n");
            detalhes.append("CEP: ").append(formatarCep(paciente.getCep())).append("\n");
            
            if (paciente.getObservacoes() != null && !paciente.getObservacoes().trim().isEmpty()) {
                detalhes.append("Observações: ").append(paciente.getObservacoes()).append("\n");
            }
            
            JTextArea textArea = new JTextArea(detalhes.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            
            JOptionPane.showMessageDialog(this, scrollPane, 
                "Detalhes do Paciente", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao visualizar paciente: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarPaciente() {
        Paciente paciente = getPacienteSelecionado();
        if (paciente == null) return;
        
        try {
            // Aqui você poderia abrir uma tela de edição
            // Por simplicidade, vou mostrar uma mensagem
            JOptionPane.showMessageDialog(this, 
                "Funcionalidade de edição será implementada em uma versão futura.\n" +
                "Paciente selecionado: " + paciente.getNome(), 
                "Editar Paciente", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao editar paciente: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void excluirPaciente() {
        Paciente paciente = getPacienteSelecionado();
        if (paciente == null) return;
        
        try {
            int opcao = JOptionPane.showConfirmDialog(this, 
                "Deseja realmente excluir o paciente:\n" + paciente.getNome() + "?", 
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            
            if (opcao == JOptionPane.YES_OPTION) {
                pacienteDAO.removerPaciente(paciente.getId());
                JOptionPane.showMessageDialog(this, 
                    "Paciente excluído com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarLista();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao excluir paciente: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String formatarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
    
    private String formatarCep(String cep) {
        if (cep == null || cep.length() != 8) return cep;
        return cep.substring(0, 5) + "-" + cep.substring(5, 8);
    }
} 