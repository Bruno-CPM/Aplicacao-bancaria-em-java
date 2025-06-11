import java.util.*;

public class TransacaoView {
    private TransacaoController controller = new TransacaoController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Transação ---");
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
        Transacao t = new Transacao();
        System.out.print("ID Conta Origem (ou ENTER): ");
        String origem = scanner.nextLine();
        t.setIdContaOrigem(origem.isEmpty() ? null : Integer.parseInt(origem));
        System.out.print("ID Conta Destino (ou ENTER): ");
        String destino = scanner.nextLine();
        t.setIdContaDestino(destino.isEmpty() ? null : Integer.parseInt(destino));
        System.out.print("Tipo: "); t.setTipo(scanner.nextLine());
        System.out.print("Valor: "); t.setValor(scanner.nextDouble()); scanner.nextLine();
        System.out.print("Data/Hora (yyyy-mm-dd hh:mm:ss): "); t.setDataHora(java.sql.Timestamp.valueOf(scanner.nextLine()));
        System.out.print("Descrição: "); t.setDescricao(scanner.nextLine());
        controller.inserir(t);
        System.out.println("Transação inserida!");
    }

    private void atualizar() {
        System.out.print("ID da transação: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Transacao t = controller.buscarPorId(id);
        if (t == null) { System.out.println("Não encontrada."); return; }
        System.out.print("Novo ID Conta Origem (ou ENTER): ");
        String origem = scanner.nextLine();
        t.setIdContaOrigem(origem.isEmpty() ? null : Integer.parseInt(origem));
        System.out.print("Novo ID Conta Destino (ou ENTER): ");
        String destino = scanner.nextLine();
        t.setIdContaDestino(destino.isEmpty() ? null : Integer.parseInt(destino));
        System.out.print("Novo tipo: "); t.setTipo(scanner.nextLine());
        System.out.print("Novo valor: "); t.setValor(scanner.nextDouble()); scanner.nextLine();
        System.out.print("Nova data/hora (yyyy-mm-dd hh:mm:ss): "); t.setDataHora(java.sql.Timestamp.valueOf(scanner.nextLine()));
        System.out.print("Nova descrição: "); t.setDescricao(scanner.nextLine());
        controller.atualizar(t);
        System.out.println("Transação atualizada!");
    }

    private void deletar() {
        System.out.print("ID da transação: ");
        int id = scanner.nextInt(); scanner.nextLine();
        controller.deletar(id);
        System.out.println("Transação deletada!");
    }

    private void buscarPorId() {
        System.out.print("ID da transação: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Transacao t = controller.buscarPorId(id);
        if (t == null) System.out.println("Não encontrada.");
        else System.out.println(t);
    }

    private void listarTodos() {
        List<Transacao> lista = controller.listarTodos();
        for (Transacao t : lista) System.out.println(t);
    }
} 