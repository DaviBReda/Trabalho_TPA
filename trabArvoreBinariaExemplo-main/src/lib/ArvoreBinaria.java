package lib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T>{
    protected No<T> raiz;
    protected Comparator<T> comparador;
    protected Integer altura;
    protected LinkedList<No<T>> pilha, pilhaAltura;

    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
        pilha = null;
        pilhaAltura = null;
        altura = 0;
    }

    public void adicionar(T novoValor){
        int count = 0;
        No<T> novoNo = new No<>(novoValor);
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
    }

    public T pesquisar(T valor){
        return pesquisarRaiz(valor, this.raiz);
    }

    public T pesquisarRaiz(T valor, No<T> raiz){
        if (valor == null) // trocar '== null' pelo comparator
            return null;
        else if(comparador.compare(valor, raiz.getValor()) == 0)
            return valor;
        else if (comparador.compare(valor, raiz.getValor()) > 0)
            return pesquisarRaiz(valor, raiz.getFilhoEsquerda()); // recursão usando o filho a esquerda como raiz
        else
            return pesquisarRaiz(valor, raiz.getFilhoDireita()); //recursão usando o filho a direita como raiz
    }

    public T remover(T valor){return removerRaiz(valor, this.raiz, null);}
    
    public T removerRaiz(T valor, No<T> raiz, No<T> pai){
        No<T> aux;
        if(raiz == null){
            return null;
        }
        if(comparador.compare(valor, raiz.getValor()) == 0){
            aux = raiz.getFilhoEsquerda();
            if (pai == null){
                this.raiz = aux;
                while (aux.getFilhoDireita() != null){
                    aux = aux.getFilhoDireita();
                }
                this.raiz.setFilhoDireita(raiz.getFilhoDireita());
                return raiz.getValor();
            }
            if(comparador.compare(pai.getValor(), raiz.getValor()) < 0){
                pai.setFilhoDireita(aux);
                while (aux.getFilhoDireita() != null){
                    aux = aux.getFilhoDireita();
                }
                aux.setFilhoDireita(raiz.getFilhoDireita());
                return raiz.getValor();
            } else {
                pai.setFilhoEsquerda(aux);
                while (aux.getFilhoDireita() != null){
                    aux = aux.getFilhoDireita();
                }
                aux.setFilhoDireita(raiz.getFilhoDireita());
                return raiz.getValor();
            }
        } else {
            if (comparador.compare(valor, raiz.getValor()) > 0) {
                return removerRaiz(valor, raiz.getFilhoEsquerda(), raiz);
            }
            else
                return removerRaiz(valor, raiz.getFilhoDireita(), raiz);
            }
    }

    public int altura(){
        return calcAltura(this.raiz);
    }

    public int calcAltura(No<T> no_at) {
        int altura_direita = 0;
        int altura_esquerda = 0;

        if( (no_at.getFilhoEsquerda() == null) & (no_at.getFilhoDireita() == null) ){
            return 0;
        }
        else{
            if(no_at.getFilhoDireita() != null){
                altura_direita += calcAltura(no_at.getFilhoDireita());
            }
            if(no_at.getFilhoEsquerda() != null){
                altura_esquerda += calcAltura(no_at.getFilhoEsquerda());
            }
            if(altura_direita > altura_esquerda){
                return (altura_direita + 1);
            }
            else{
                return (altura_esquerda + 1);
            }
        }
    }

    //___________________________________________Metodo extra______________________________________________________________________________________
    public int alturaIlimitado(){
        this.pilhaAltura = new LinkedList<>();
        this.pilhaAltura.clear();
        this.pilhaAltura.push(this.raiz);
        int val;
        int soma = 0;
        while(!this.pilhaAltura.isEmpty()){
            try{
                val = calcAlturaIlimitado(this.pilhaAltura.pop(), 0);
                soma += val;
            }
            catch(Exception e){
                System.out.println("Exception caught: "+ e);
            }
        }
        return soma;
    }

    //________________________________________________Metodo extra____________________________________________________________________________________
    public int calcAlturaIlimitado(No<T> no_at, int count) {
        int altura_direita = 0;
        int altura_esquerda = 0;
        count += 1;
        if(no_at == null){
            return 0;
        }
        if( (no_at.getFilhoEsquerda() == null) & (no_at.getFilhoDireita() == null) ){
            return 0;
        }
        else{
            if(no_at.getFilhoDireita() != null){
                if(count < 100){
                    altura_direita += calcAlturaIlimitado(no_at.getFilhoDireita(), count);
                }
                else{
                    this.pilhaAltura.push(no_at.getFilhoDireita());
                }
            }
            if(no_at.getFilhoEsquerda() != null){
                if(count < 100){
                    altura_esquerda += calcAlturaIlimitado(no_at.getFilhoEsquerda(), count);
                }
                else{
                    this.pilhaAltura.push(no_at.getFilhoEsquerda());
                }
            }
            if(altura_direita > altura_esquerda){
                return (altura_direita + 1);
            }
            else{
                return (altura_esquerda + 1);
            }
        }
    }

    public int quantidadeNos(){
        return caminhaEmOrdemCount(this.raiz);
    }

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
    
    public String caminharEmNivel(){
        ArrayList<No<T>> fila = new ArrayList<>();
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
    }

    public String caminharEmOrdem(){return caminhaEmOrdem(this.raiz);}

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

    private void popularPilha(No<T> raiz){
        if(raiz.getFilhoEsquerda()==null){
            this.pilha.push(raiz);
        }
        else{
            this.pilha.push(raiz);
            popularPilha(raiz.getFilhoEsquerda());
        }
    }
    public T obterProximo(){
        No<T> aux;
        if (this.raiz==null){
            return null;
        }
        if(this.pilha==null){
            this.pilha = new LinkedList<>();
            popularPilha(this.raiz);
        }
        if(this.pilha.isEmpty()){
            return null;
        }
        aux = this.pilha.pop();
        if(aux.getFilhoDireita()==null){
            return aux.getValor();
        }else {
            popularPilha(aux.getFilhoDireita());
            return aux.getValor();
        }
    }

    public void reiniciarNavegacao(){
        this.pilha = null;
    }
}
