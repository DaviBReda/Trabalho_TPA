package lib;

import java.util.HashMap;

public class No<T> {

    private T valor;
    private HashMap<No<T>, Integer> fronteira;

    public No(T valor){
        this.valor = valor;
        this.fronteira = new HashMap<No<T>, Integer>();
    }

    public T getValor() {
        return valor;
    }

    public void setVizinho(No<T> no, int peso){
        this.fronteira.put(no, peso);
    }

    public void setVizinhoND(No<T> no, int peso){
        this.fronteira.put(no, peso);
        no.fronteira.put(this, peso);
    }

    public void removeVizinho(No<T> no, int peso){
        this.fronteira.remove(no, peso);
    }

    public void removeVizinhoND(No<T> no, int peso){
        this.fronteira.remove(no, peso);
        no.fronteira.remove(this, peso);
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}
