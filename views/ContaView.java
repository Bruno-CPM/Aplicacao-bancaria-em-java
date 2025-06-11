import java.util.*;

public class ContaView {
    private ContaController controller = new ContaController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Conta ---");
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
        Conta c = new Conta() {};
        System.out.print("Número: "); c.setNumero(scanner.nextLine());
        System.out.print("ID Cliente: "); c.setIdCliente(scanner.nextInt()); scanner.nextLine();
        System.out.print("ID Agência: "); c.setIdAgencia(scanner.nextInt()); scanner.nextLine();
        System.out.print("Saldo: "); c.setSaldo(scanner.nextDouble()); scanner.nextLine();
        System.out.print("Tipo: "); c.setTipo(scanner.nextLine());
        System.out.print("Status: "); c.setStatus(scanner.nextLine());
        System.out.print("Data Abertura (yyyy-mm-dd hh:mm:ss): "); c.setDataAbertura(java.sql.Timestamp.valueOf(scanner.nextLine()));
        System.out.print("Data Encerramento (yyyy-mm-dd hh:mm:ss ou ENTER): ");
        String encerramento = scanner.nextLine();
        if (!encerramento.isEmpty()) c.setDataEncerramento(java.sql.Timestamp.valueOf(encerramento));
        controller.inserir(c);
        System.out.println("Conta inserida!");
    }

    private void atualizar() {
        System.out.print("ID da conta: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Conta c = controller.buscarPorId(id);
        if (c == null) { System.out.println("Não encontrada."); return; }
        System.out.print("Novo número: "); c.setNumero(scanner.nextLine());
        System.out.print("Novo ID Cliente: "); c.setIdCliente(scanner.nextInt()); scanner.nextLine();
        System.out.print("Novo ID Agência: "); c.setIdAgencia(scanner.nextInt()); scanner.nextLine();
        System.out.print("Novo saldo: "); c.setSaldo(scanner.nextDouble()); scanner.nextLine();
        System.out.print("Novo tipo: "); c.setTipo(scanner.nextLine());
        System.out.print("Novo status: "); c.setStatus(scanner.nextLine());
        System.out.print("Nova data abertura (yyyy-mm-dd hh:mm:ss): "); c.setDataAbertura(java.sql.Timestamp.valueOf(scanner.nextLine()));
        System.out.print("Nova data encerramento (yyyy-mm-dd hh:mm:ss ou ENTER): ");
        String encerramento = scanner.nextLine();
        if (!encerramento.isEmpty()) c.setDataEncerramento(java.sql.Timestamp.valueOf(encerramento));
        controller.atualizar(c);
        System.out.println("Conta atualizada!");
    }

    private void deletar() {
        System.out.print("ID da conta: ");
        int id = scanner.nextInt(); scanner.nextLine();
        controller.deletar(id);
        System.out.println("Conta deletada!");
    }

    private void buscarPorId() {
        System.out.print("ID da conta: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Conta c = controller.buscarPorId(id);
        if (c == null) System.out.println("Não encontrada.");
        else System.out.println(c);
    }

    private void listarTodos() {
        List<Conta> lista = controller.listarTodos();
        for (Conta c : lista) System.out.println(c);
    }
} 