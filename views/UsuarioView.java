import java.util.*;

public class UsuarioView {
    private UsuarioController controller = new UsuarioController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Usuário ---");
            System.out.println("1. Inserir");
            System.out.println("2. Atualizar");
            System.out.println("3. Deletar");
            System.out.println("4. Buscar por ID");
            System.out.println("5. Listar todos");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1: inserir(); break;
                case 2: atualizar(); break;
                case 3: deletar(); break;
                case 4: buscarPorId(); break;
                case 5: listarTodos(); break;
            }
        } while (opcao != 0);
    }

    private void inserir() {
        Usuario u = new Usuario();
        System.out.print("Nome: "); u.setNome(scanner.nextLine());
        System.out.print("Email: "); u.setEmail(scanner.nextLine());
        System.out.print("Senha (MD5): "); u.setSenhaMd5(scanner.nextLine());
        System.out.print("CPF: "); u.setCpf(scanner.nextLine());
        System.out.print("Telefone: "); u.setTelefone(scanner.nextLine());
        System.out.print("Data Nascimento (yyyy-mm-dd): "); u.setDataNascimento(java.sql.Date.valueOf(scanner.nextLine()));
        System.out.print("Tipo (CLIENTE/FUNCIONARIO): "); u.setTipo(scanner.nextLine());
        System.out.print("Ativo (true/false): "); u.setAtivo(Boolean.parseBoolean(scanner.nextLine()));
        controller.inserir(u);
        System.out.println("Usuário inserido!");
    }

    private void atualizar() {
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Usuario u = controller.buscarPorId(id);
        if (u == null) { System.out.println("Não encontrado."); return; }
        System.out.print("Novo nome: "); u.setNome(scanner.nextLine());
        System.out.print("Novo email: "); u.setEmail(scanner.nextLine());
        System.out.print("Nova senha (MD5): "); u.setSenhaMd5(scanner.nextLine());
        System.out.print("Novo CPF: "); u.setCpf(scanner.nextLine());
        System.out.print("Novo telefone: "); u.setTelefone(scanner.nextLine());
        System.out.print("Nova data nascimento (yyyy-mm-dd): "); u.setDataNascimento(java.sql.Date.valueOf(scanner.nextLine()));
        System.out.print("Novo tipo: "); u.setTipo(scanner.nextLine());
        System.out.print("Ativo (true/false): "); u.setAtivo(Boolean.parseBoolean(scanner.nextLine()));
        controller.atualizar(u);
        System.out.println("Usuário atualizado!");
    }

    private void deletar() {
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt(); scanner.nextLine();
        controller.deletar(id);
        System.out.println("Usuário deletado!");
    }

    private void buscarPorId() {
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Usuario u = controller.buscarPorId(id);
        if (u == null) System.out.println("Não encontrado.");
        else System.out.println(u);
    }

    private void listarTodos() {
        List<Usuario> lista = controller.listarTodos();
        for (Usuario u : lista) System.out.println(u);
    }
} 