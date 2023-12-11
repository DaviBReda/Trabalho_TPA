import java.util.Scanner;

public class Biblioteca {

    private Grafo<Livro> grafoLivros;

    public Biblioteca() {
        this.grafoLivros = new Grafo<>();
    }

    public void cadastrarLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do livro:");
        String nome = scanner.nextLine();

        System.out.println("Digite o número de páginas do livro:");
        int numeroPaginas = scanner.nextInt();

        Livro livro = new Livro(nome, numeroPaginas);
        grafoLivros.addElemento(livro);

        System.out.println("Livro cadastrado com sucesso!\n");
    }

    public void consultarLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do livro para consultar:");
        String nome = scanner.nextLine();

        boolean encontrado = false;
        for (No<Livro> noLivro : grafoLivros.getListaNos()) {
            if (noLivro.getValor().getNome().equalsIgnoreCase(nome)) {
                System.out.println("Livro encontrado:");
                noLivro.getValor().imprimirDetalhes();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado.\n");
        }
    }

    public void excluirLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do livro para excluir:");
        String nome = scanner.nextLine();

        for (No<Livro> noLivro : grafoLivros.getListaNos()) {
            if (noLivro.getValor().getNome().equalsIgnoreCase(nome)) {
                grafoLivros.removeElemento(noLivro);
                System.out.println("Livro excluído com sucesso!\n");
                return;
            }
        }

        System.out.println("Livro não encontrado.\n");
    }

    public void exibirTodos() {
        if (grafoLivros.getListaNos().isEmpty()) {
            System.out.println("A biblioteca está vazia.\n");
        } else {
            System.out.println("Livros na biblioteca:");
            for (No<Livro> noLivro : grafoLivros.getListaNos()) {
                noLivro.getValor().imprimirDetalhes();
                System.out.println("---------------");
            }
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Consultar Livro");
            System.out.println("3 - Excluir Livro");
            System.out.println("4 - Exibir Todos");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    biblioteca.cadastrarLivro();
                    break;
                case 2:
                    biblioteca.consultarLivro();
                    break;
                case 3:
                    biblioteca.excluirLivro();
                    break;
                case 4:
                    biblioteca.exibirTodos();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
