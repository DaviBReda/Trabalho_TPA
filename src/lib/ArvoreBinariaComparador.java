package arvorebinaria.lib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;

public class ArvoreBinariaComparador<T> implements IArvoreBinaria<T>{
    protected No<T> raiz;
    protected Comparator<T> comparador;
    protected Integer altura, countProx;
    protected LinkedList<No<T>> pilha;

    public ArvoreBinariaComparador(Comparator<T> comp) {
        comparador = comp;
        pilha = new LinkedList<No<T>>();
    }

    public void adicionar(T novoValor){
        int count = 0;
        No<T> novoNo = new No<T>(novoValor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No<T> atual = raiz;
            boolean inserido = false;
            while (!inserido) {
                //Se o novo elemento for menor do que o atual vou pra esquerda
                if(comparador.compare(novoNo.getValor(),atual.getValor()) < 0){

                    if (atual.getFilhoEsquerda() == null) {
                        atual.setFilhoEsquerda(novoNo);
                        inserido = true;
                    } else {
                        atual = atual.getFilhoEsquerda();
                        count += 1;
                    }
                } //Se o novo elemento for maior ou igual vou pra direita
                else {
                    if (atual.getFilhoDireita() == null) {
                        atual.setFilhoDireita(novoNo);
                        inserido = true;
                    } else {
                        atual = atual.getFilhoDireita();
                        count += 1;
                    }
                }
            }
            if (count > altura) {
                altura = count;
            }
        }
    };

    public T pesquisar(T valor){
        return pesquisarRaiz(valor, this.raiz);
    };

    public T pesquisarRaiz(T valor, No<T> raiz){
        if (valor == null) // trocar '== null' pelo comparator
            return null;
        else if(comparador.compare(valor, raiz.getValor()) == 0 /*valor == aux.getValor()*/) // trocar '== info' pelo comparator
            return valor;
        else if (comparador.compare(valor, raiz.getValor()) > 0 /*valor < aux.getValor()*/) // trocar '< aux.valor' pelo comparator
            return pesquisarRaiz(valor, raiz.getFilhoEsquerda()); // dar um jeito de aux andar para esquerda
        else
            return pesquisarRaiz(valor, raiz.getFilhoDireita()); // dar um jeito de aux andar para direita
    };
    
    public T remover(T valor){
        /*
            aux == raiz
            Se valor == aux.valor, remover aux e subir o da esquerda
            Se não
                Se valor > aux.valor, aux = aux.filhoDireita; chamar metodo recursivamente
                Se não, aux = aux.filhoEsquerda; chamar metodo recursivamente
        */
        return null;
    };

    public int altura(){
        return altura;
    };

    public int quantidadeNos(){
        return caminhaEmOrdemCount(this.raiz);
    };

    private int caminhaEmOrdemCount(No<T> raiz) {
        int soma = 0;
        if(raiz!=null){
            soma += caminhaEmOrdemCount(raiz.getFilhoEsquerda());
            soma += 1;
            soma += caminhaEmOrdemCount(raiz.getFilhoDireita());
            return soma;
        }
        return soma;
    }

    
//    public ArrayList<No<T>> todosNos(){
//        No<T> aux = this.raiz;
//        ArrayList<No<T>> frontier = new ArrayList<No<T>>();
//        ArrayList<No<T>> todos = new ArrayList<No<T>>();
//        todos.add(aux);
//        frontier.add(aux);
//        while(!frontier.isEmpty()){
//            for(i=0,x,i++){
//                frontier.add(frontier[i].getFilhoEsquerda);
//                frontier.add(frontier[i].getFilhoDireita);
//            };
//            frontier = subtract(frontier, todos);
//            todos = add(todos, frontier);
//        };
//        return todos;
//
//        // Equanto frontier != null
//        // Chama get filhos de frontier
//        // Limpa anteriores (frontier - todos)
//        // Todos += frontier
//        // repete
//    };

    
    public String caminharEmNivel(){
            ArrayList<No<T>> fila = new ArrayList<No<T>>();
            StringBuilder result = new StringBuilder();
            if (this.raiz == null){
                // System.out.println("Caminhamento por Nível - Árvore Vazia");
                return null;
            }
            else{
                // System.out.println("Caminhamento por Nível: ");
                No<T> atual;
                fila.add(this.raiz);
                while (!fila.isEmpty()){
                    atual = fila.get(0);
                    fila.remove(0);
                    result.append(atual.getValor()).append(" - ");
                    if (atual.getFilhoEsquerda() != null)
                        fila.add(atual.getFilhoEsquerda());
                    if (atual.getFilhoDireita() != null){
                        fila.add(atual.getFilhoDireita());
                    }
                }
            }
            return result.toString();
        };
    
    public String caminharEmOrdem(){
        // System.out.println("Saída do Caminhamento em Ordem");
        String str = caminhaEmOrdem(this.raiz);
        // System.out.println("Fim da Saída do Caminhamento em Ordem");
        return str;
    };

    private String caminhaEmOrdem(No<T> raiz) {
        String result = "";
        if(raiz!=null){
            result += caminhaEmOrdem(raiz.getFilhoEsquerda());
            result += raiz.getValor()+"/n";
            result += caminhaEmOrdem(raiz.getFilhoDireita());
            return result;
        }
        return result;
    }

    void popularPilha(No<T> raiz){
        if(raiz.getFilhoEsquerda()==null){
            this.pilha.push(raiz);
            return;
        }
        else{
            this.pilha.push(raiz);
            popularPilha(raiz.getFilhoEsquerda());
        }
    }
    public T obterProximo(){
        /*
            self.countProx = 0;
            Caminha para esquerda (direita se nao tiver) - count, mostra valor
            ----------
            pilha filhos a esquerda é melhor
        */
        No<T> aux = null;
        if (this.raiz==null){
            return null;
        }
        if(this.pilha.peekFirst()==null){
            popularPilha(this.raiz);
        }
        aux = this.pilha.pop();
        if(aux.getFilhoDireita()==null){
            return aux.getValor();
        }else{
            popularPilha(aux.getFilhoDireita());
            return aux.getValor();
        }
    };

    public void reiniciarNavegacao(){
        this.pilha.clear();
    };
}
