package lib;

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
        No<Livro> livroNo = new No<>(livro);
        grafoLivros.addElemento(livroNo);

        System.out.println("O livro possui um pré-requisito? (S/N)");
        scanner.nextLine();  // Consumir a quebra de linha
        String respostaPrereq = scanner.nextLine();

        if (respostaPrereq.equalsIgnoreCase("S")) {
            System.out.println("Digite o nome do livro pré-requisito:");
            String nomePrereq = scanner.nextLine();
            No<Livro> livroEncontrado = this.consultarLivro(nomePrereq);

            if (livroEncontrado != null) {
                grafoLivros.addCaminho(livroEncontrado, livroNo, 1);  // Adapte o peso conforme necessário
                System.out.println("Pré-requisito cadastrado com sucesso!\n");
            } else {
                System.out.println("Livro usado como pré-requisito não cadastrado");
            }
        }

        System.out.println("Livro cadastrado com sucesso!\n");
    }

    public No<Livro> consultarLivro(String nome) {
        for (No<Livro> noLivro : grafoLivros.getListaNos()) {
            if (noLivro.getValor().getNome().equalsIgnoreCase(nome)) {
                return noLivro;
            }
        }
        return null;
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
}
