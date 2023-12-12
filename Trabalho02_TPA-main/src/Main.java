import lib.*;
import java.util.Scanner;



public class Main {
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
                    System.out.println("Digite o nome do livro para consultar:");
                    String nomeConsulta = scanner.nextLine();
                    No<Livro> livroEncontrado = biblioteca.consultarLivro(nomeConsulta);

                    if (livroEncontrado != null) {
                        System.out.println("Livro encontrado!");
                        // Faça o que desejar com o nó encontrado, por exemplo, imprimir os detalhes do livro
                        livroEncontrado.getValor().imprimirDetalhes();
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
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