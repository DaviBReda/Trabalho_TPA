package lib;

import javax.swing.plaf.nimbus.NimbusStyle;
import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comp){
        super(comp);
    }

    @Override
    public void adicionar(T novoValor){
        No<T> novoNo = new No<>(novoValor);
        if(this.raiz==null){
            this.raiz = novoNo;
        }else {
            this.raiz = addRecursao(this.raiz, novoNo);
        }
    }
    @Override
    protected No<T> addRecursao(No<T> raiz, No<T> novo){
        raiz = super.addRecursao(raiz, novo);

        if (raiz.fatorBalanceamento()>1){
            if(raiz.getFilhoDireita().fatorBalanceamento()>0)
                raiz = this.rotacaoEsquerda(raiz);
            else
                raiz = this.rotacaoDireitaEsquerda(raiz);
        }else if(raiz.fatorBalanceamento()<-1){
            if(raiz.getFilhoEsquerda().fatorBalanceamento()<0)
                raiz = this.rotacaoDireita(raiz);
            else
                raiz = this.rotacaoEsquerdaDireita(raiz);
        }
        return raiz;
    }

    protected No<T> rotacaoEsquerda(No<T> r){
        No<T> aux = r.getFilhoDireita();
        r.setFilhoDireita(aux.getFilhoEsquerda());
        aux.setFilhoEsquerda(r);
        return aux;
    }

    protected No<T> rotacaoDireitaEsquerda(No<T> r){
        r.setFilhoDireita(rotacaoDireita(r.getFilhoDireita()));
        return rotacaoEsquerda(r);
    }

    protected No<T> rotacaoDireita(No<T> r){
        No<T> aux = r.getFilhoEsquerda();
        r.setFilhoEsquerda(aux.getFilhoDireita());
        aux.setFilhoDireita(r);
        return aux;
    }

    protected No<T> rotacaoEsquerdaDireita(No<T> r){
        r.setFilhoEsquerda(rotacaoEsquerda(r.getFilhoEsquerda()));
        return rotacaoDireita(r);
    }
}
