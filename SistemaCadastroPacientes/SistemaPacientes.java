import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaPacientes extends JFrame {
    private PacienteDAO pacienteDAO;
    private TelaAdicionarPaciente telaAdicionar;
    private TelaListarPacientes telaListar;
    
    public SistemaPacientes() {
        try {
            pacienteDAO = new PacienteDAO();
            inicializarInterface();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao inicializar o sistema: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    private void inicializarInterface() {
        setTitle("Sistema de Cadastro de Pacientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titulo = new JLabel("Sistema de Cadastro de Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(3, 1, 10, 10));
        
        JButton btnAdicionar = new JButton("Adicionar Paciente");
        JButton btnListar = new JButton("Listar Pacientes");
        JButton btnSair = new JButton("Sair");
        
        // Estilo dos botões
        btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnListar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSair.setFont(new Font("Arial", Font.PLAIN, 14));
        
        btnAdicionar.setPreferredSize(new Dimension(200, 40));
        btnListar.setPreferredSize(new Dimension(200, 40));
        btnSair.setPreferredSize(new Dimension(200, 40));
        
        // Ações dos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionar();
            }
        });
        
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaListar();
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnSair);
        
        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);
        
        // Informações do sistema
        JLabel info = new JLabel("Dados salvos automaticamente em pacientes.dat", SwingConstants.CENTER);
        info.setFont(new Font("Arial", Font.ITALIC, 10));
        info.setForeground(Color.GRAY);
        painelPrincipal.add(info, BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }
    
    private void abrirTelaAdicionar() {
        try {
            if (telaAdicionar == null) {
                telaAdicionar = new TelaAdicionarPaciente(pacienteDAO);
            }
            telaAdicionar.setVisible(true);
            telaAdicionar.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao abrir tela de adicionar: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void abrirTelaListar() {
        try {
            if (telaListar == null) {
                telaListar = new TelaListarPacientes(pacienteDAO);
            }
            telaListar.atualizarLista();
            telaListar.setVisible(true);
            telaListar.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao abrir tela de listar: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void sair() {
        int opcao = JOptionPane.showConfirmDialog(this, 
            "Deseja realmente sair do sistema?", 
            "Confirmar Saída", 
            JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        try {
            // Configurar look and feel do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            System.err.println("Erro ao configurar look and feel: " + e.getMessage());
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new SistemaPacientes().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, 
                        "Erro ao iniciar o sistema: " + e.getMessage(), 
                        "Erro Fatal", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
        });
    }
} 