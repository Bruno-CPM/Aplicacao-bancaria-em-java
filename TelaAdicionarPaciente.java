import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaAdicionarPaciente extends JFrame {
    private PacienteDAO pacienteDAO;
    
    // Campos de entrada
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtDataNascimento;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtEndereco;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtCep;
    private JTextArea txtObservacoes;
    
    public TelaAdicionarPaciente(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
        inicializarInterface();
    }
    
    private void inicializarInterface() {
        setTitle("Adicionar Paciente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Painel principal com scroll
        JScrollPane scrollPane = new JScrollPane();
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titulo = new JLabel("Adicionar Novo Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        // Painel de formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Campos do formulário
        int linha = 0;
        
        // Nome
        gbc.gridx = 0; gbc.gridy = linha;
        painelFormulario.add(new JLabel("Nome *:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtNome = new JTextField(20);
        painelFormulario.add(txtNome, gbc);
        
        // CPF
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("CPF *:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtCpf = new JTextField(20);
        painelFormulario.add(txtCpf, gbc);
        
        // Data de Nascimento
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Data Nascimento * (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtDataNascimento = new JTextField(20);
        painelFormulario.add(txtDataNascimento, gbc);
        
        // Telefone
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Telefone *:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtTelefone = new JTextField(20);
        painelFormulario.add(txtTelefone, gbc);
        
        // Email
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Email *:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtEmail = new JTextField(20);
        painelFormulario.add(txtEmail, gbc);
        
        // Endereço
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Endereço *:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtEndereco = new JTextField(20);
        painelFormulario.add(txtEndereco, gbc);
        
        // Cidade
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Cidade *:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtCidade = new JTextField(20);
        painelFormulario.add(txtCidade, gbc);
        
        // Estado
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Estado * (ex: SP):"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtEstado = new JTextField(20);
        painelFormulario.add(txtEstado, gbc);
        
        // CEP
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("CEP *:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtCep = new JTextField(20);
        painelFormulario.add(txtCep, gbc);
        
        // Observações
        linha++;
        gbc.gridx = 0; gbc.gridy = linha; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Observações:"), gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 1.0;
        txtObservacoes = new JTextArea(4, 20);
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);
        JScrollPane scrollObservacoes = new JScrollPane(txtObservacoes);
        painelFormulario.add(scrollObservacoes, gbc);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPaciente();
            }
        });
        
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnCancelar);
        
        // Adicionar componentes ao painel principal
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelFormulario, BorderLayout.CENTER);
        painelCentral.add(painelBotoes, BorderLayout.SOUTH);
        
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        
        // Adicionar ao scroll pane
        scrollPane.setViewportView(painelPrincipal);
        add(scrollPane);
        
        // Focar no primeiro campo
        txtNome.requestFocus();
    }
    
    private void salvarPaciente() {
        try {
            // Validar e criar paciente
            Paciente paciente = criarPaciente();
            
            // Validar dados
            PacienteValidator.validarPaciente(paciente);
            
            // Salvar no DAO
            pacienteDAO.adicionarPaciente(paciente);
            
            JOptionPane.showMessageDialog(this, 
                "Paciente cadastrado com sucesso!\nID: " + paciente.getId(), 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            limparCampos();
            txtNome.requestFocus();
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro de validação: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao salvar paciente: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Paciente criarPaciente() throws IllegalArgumentException {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String dataStr = txtDataNascimento.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();
        String endereco = txtEndereco.getText().trim();
        String cidade = txtCidade.getText().trim();
        String estado = txtEstado.getText().trim().toUpperCase();
        String cep = txtCep.getText().trim();
        String observacoes = txtObservacoes.getText().trim();
        
        // Parse da data
        LocalDate dataNascimento = PacienteValidator.parseData(dataStr);
        
        Paciente paciente = new Paciente(nome, cpf, dataNascimento, telefone, email, endereco, cidade, estado, cep);
        paciente.setObservacoes(observacoes);
        
        return paciente;
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtDataNascimento.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtCep.setText("");
        txtObservacoes.setText("");
    }
} 