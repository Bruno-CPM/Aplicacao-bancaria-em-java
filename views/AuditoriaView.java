import java.util.*;

public class AuditoriaView {
    private AuditoriaController controller = new AuditoriaController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Auditoria ---");
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
        Auditoria a = new Auditoria();
        // Adapte os campos conforme o model Auditoria
        System.out.println("Implementar campos de Auditoria");
        controller.inserir(a);
        System.out.println("Auditoria inserida!");
    }

    private void atualizar() {
        System.out.print("ID da auditoria: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Auditoria a = controller.buscarPorId(id);
        if (a == null) { System.out.println("Não encontrada."); return; }
        // Adapte os campos conforme o model Auditoria
        System.out.println("Implementar atualização de campos de Auditoria");
        controller.atualizar(a);
        System.out.println("Auditoria atualizada!");
    }

    private void deletar() {
        System.out.print("ID da auditoria: ");
        int id = scanner.nextInt(); scanner.nextLine();
        controller.deletar(id);
        System.out.println("Auditoria deletada!");
    }

    private void buscarPorId() {
        System.out.print("ID da auditoria: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Auditoria a = controller.buscarPorId(id);
        if (a == null) System.out.println("Não encontrada.");
        else System.out.println(a);
    }

    private void listarTodos() {
        List<Auditoria> lista = controller.listarTodos();
        for (Auditoria a : lista) System.out.println(a);
    }
} 