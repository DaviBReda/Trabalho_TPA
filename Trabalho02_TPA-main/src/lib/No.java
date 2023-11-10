package lib;

import java.util.HashMap;

public class No<T> {

    private T valor;
    private HashMap<No<T>, Integer> fronteira;

    public No(T valor){
        this.valor = valor;
        this.fronteira = new HashMap<No<T>, Integer>();
    }

    /**
     * @return the valor
     */
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

    /**
     * @param valor the valor to set
     */
    public void setValor(T valor) {
        this.valor = valor;
    }
}