package lib;
public class Livro {

    private String nome;
    private int numeroPaginas;

    public Livro(String nome, int numeroPaginas) {
        this.nome = nome;
        this.numeroPaginas = numeroPaginas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroPaginas() {
        return this.numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void imprimirDetalhes() {
        System.out.println("Nome do livro: " + nome);
        System.out.println("Número de páginas: " + numeroPaginas);
    }
}
