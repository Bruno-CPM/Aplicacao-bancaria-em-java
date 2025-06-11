import java.util.*;

public class RelatorioView {
    private RelatorioController controller = new RelatorioController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Relatório ---");
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
        Relatorio r = new Relatorio();
        System.out.print("ID Funcionário: "); r.setIdFuncionario(scanner.nextInt()); scanner.nextLine();
        System.out.print("Tipo: "); r.setTipo(scanner.nextLine());
        System.out.print("Data Geração (yyyy-mm-dd hh:mm:ss): "); r.setDataGeracao(java.sql.Timestamp.valueOf(scanner.nextLine()));
        System.out.print("Caminho Arquivo: "); r.setCaminhoArquivo(scanner.nextLine());
        controller.inserir(r);
        System.out.println("Relatório inserido!");
    }

    private void atualizar() {
        System.out.print("ID do relatório: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Relatorio r = controller.buscarPorId(id);
        if (r == null) { System.out.println("Não encontrado."); return; }
        System.out.print("Novo ID Funcionário: "); r.setIdFuncionario(scanner.nextInt()); scanner.nextLine();
        System.out.print("Novo tipo: "); r.setTipo(scanner.nextLine());
        System.out.print("Nova data geração (yyyy-mm-dd hh:mm:ss): "); r.setDataGeracao(java.sql.Timestamp.valueOf(scanner.nextLine()));
        System.out.print("Novo caminho arquivo: "); r.setCaminhoArquivo(scanner.nextLine());
        controller.atualizar(r);
        System.out.println("Relatório atualizado!");
    }

    private void deletar() {
        System.out.print("ID do relatório: ");
        int id = scanner.nextInt(); scanner.nextLine();
        controller.deletar(id);
        System.out.println("Relatório deletado!");
    }

    private void buscarPorId() {
        System.out.print("ID do relatório: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Relatorio r = controller.buscarPorId(id);
        if (r == null) System.out.println("Não encontrado.");
        else System.out.println(r);
    }

    private void listarTodos() {
        List<Relatorio> lista = controller.listarTodos();
        for (Relatorio r : lista) System.out.println(r);
    }
} 