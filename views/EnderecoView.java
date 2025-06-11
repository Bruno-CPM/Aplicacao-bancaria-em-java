import java.util.*;

public class EnderecoView {
    private EnderecoController controller = new EnderecoController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Endereço ---");
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
        Endereco e = new Endereco();
        System.out.print("ID Usuário: "); e.setIdUsuario(scanner.nextInt()); scanner.nextLine();
        System.out.print("Logradouro: "); e.setLogradouro(scanner.nextLine());
        System.out.print("Número: "); e.setNumero(scanner.nextLine());
        System.out.print("Complemento: "); e.setComplemento(scanner.nextLine());
        System.out.print("Bairro: "); e.setBairro(scanner.nextLine());
        System.out.print("Cidade: "); e.setCidade(scanner.nextLine());
        System.out.print("Estado: "); e.setEstado(scanner.nextLine());
        System.out.print("CEP: "); e.setCep(scanner.nextLine());
        controller.inserir(e);
        System.out.println("Endereço inserido!");
    }

    private void atualizar() {
        System.out.print("ID do endereço: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Endereco e = controller.buscarPorId(id);
        if (e == null) { System.out.println("Não encontrado."); return; }
        System.out.print("Novo ID Usuário: "); e.setIdUsuario(scanner.nextInt()); scanner.nextLine();
        System.out.print("Novo logradouro: "); e.setLogradouro(scanner.nextLine());
        System.out.print("Novo número: "); e.setNumero(scanner.nextLine());
        System.out.print("Novo complemento: "); e.setComplemento(scanner.nextLine());
        System.out.print("Novo bairro: "); e.setBairro(scanner.nextLine());
        System.out.print("Nova cidade: "); e.setCidade(scanner.nextLine());
        System.out.print("Novo estado: "); e.setEstado(scanner.nextLine());
        System.out.print("Novo CEP: "); e.setCep(scanner.nextLine());
        controller.atualizar(e);
        System.out.println("Endereço atualizado!");
    }

    private void deletar() {
        System.out.print("ID do endereço: ");
        int id = scanner.nextInt(); scanner.nextLine();
        controller.deletar(id);
        System.out.println("Endereço deletado!");
    }

    private void buscarPorId() {
        System.out.print("ID do endereço: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Endereco e = controller.buscarPorId(id);
        if (e == null) System.out.println("Não encontrado.");
        else System.out.println(e);
    }

    private void listarTodos() {
        List<Endereco> lista = controller.listarTodos();
        for (Endereco e : lista) System.out.println(e);
    }
} 