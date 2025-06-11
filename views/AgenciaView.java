import java.util.*;

public class AgenciaView {
    private AgenciaController controller = new AgenciaController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Agência ---");
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
        Agencia a = new Agencia();
        System.out.print("Nome: "); a.setNome(scanner.nextLine());
        System.out.print("Endereço: "); a.setEndereco(scanner.nextLine());
        controller.inserir(a);
        System.out.println("Agência inserida!");
    }

    private void atualizar() {
        System.out.print("ID da agência: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Agencia a = controller.buscarPorId(id);
        if (a == null) { System.out.println("Não encontrada."); return; }
        System.out.print("Novo nome: "); a.setNome(scanner.nextLine());
        System.out.print("Novo endereço: "); a.setEndereco(scanner.nextLine());
        controller.atualizar(a);
        System.out.println("Agência atualizada!");
    }

    private void deletar() {
        System.out.print("ID da agência: ");
        int id = scanner.nextInt(); scanner.nextLine();
        controller.deletar(id);
        System.out.println("Agência deletada!");
    }

    private void buscarPorId() {
        System.out.print("ID da agência: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Agencia a = controller.buscarPorId(id);
        if (a == null) System.out.println("Não encontrada.");
        else System.out.println(a);
    }

    private void listarTodos() {
        List<Agencia> lista = controller.listarTodos();
        for (Agencia a : lista) System.out.println(a);
    }
} 