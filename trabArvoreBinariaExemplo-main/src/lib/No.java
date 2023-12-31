/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author victoriocarvalho
 */
public class No<T> {
    
    private T valor;
    private No<T> filhoDireita;
    private No<T> filhoEsquerda;

    
    public No(T valor){
        this.valor = valor;
        this.filhoDireita = null;
        this.filhoEsquerda = null;
    }
    
    /**
     * @return the valor
     */
    public T getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * @return the filhoDireita
     */
    public No<T> getFilhoDireita() {
        return filhoDireita;
    }

    /**
     * @param filhoDireita the filhoDireita to set
     */
    public void setFilhoDireita(No<T> filhoDireita) {
        this.filhoDireita = filhoDireita;
    }

    /**
     * @return the filhoEsquerda
     */
    public No<T> getFilhoEsquerda() {
        return filhoEsquerda;
    }

    /**
     * @param filhoEsquerda the filhoEsquerda to set
     */
    public void setFilhoEsquerda(No<T> filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

//    public int fatorBalanceamento() {
//        int altura_direita = 0;
//        int altura_esquerda = 0;
//
//        if( (getFilhoEsquerda() == null) & (getFilhoDireita() == null) ){
//            return 0;
//        }
//        else{
//            if(getFilhoDireita() != null){
//                altura_direita += getFilhoDireita().fatorBalanceamento();
//            }
//            if(getFilhoEsquerda() != null){
//                altura_esquerda += getFilhoEsquerda().fatorBalanceamento();
//            }
//            if(altura_direita > altura_esquerda){
//                return (altura_direita + 1);
//            }
//            else{
//                return (altura_esquerda + 1);
//            }
//        }
//    }

    public int obterAltura(){
        return obterAltura(this);
    }

    private int obterAltura(No<T> r){
        if (r==null) return -1;
        else{
            int hd = obterAltura(r.getFilhoDireita());
            int he = obterAltura(r.getFilhoEsquerda());
            if(hd>he)
                return hd+1;
            else
                return he+1;
        }
    }

    public int fatorBalanceamento(){
        return obterAltura(this.filhoDireita) - obterAltura(this.filhoEsquerda);
    }
}
