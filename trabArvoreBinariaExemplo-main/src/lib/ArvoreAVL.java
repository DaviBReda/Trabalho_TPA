package lib;

import javax.swing.plaf.nimbus.NimbusStyle;
import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comp){
        super(comp);
    }

    @Override
    public void adicionar(T novoValor){// metodo que adiciona um novo nó na arvore, ou cria a raiz caso a arvore esteja vazia, modifiquei a função pra ser recursiva
        No<T> novoNo = new No<>(novoValor);
        if(this.raiz==null){
            this.raiz = novoNo;
        }else {
            this.raiz = addRecursao(this.raiz, novoNo);
        }
    }
    @Override
    protected No<T> addRecursao(No<T> raiz, No<T> novo){// metodo responsavel por adicionar o novo nó e depois balancear a arvore caso necessario
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

    protected No<T> rotacaoEsquerda(No<T> r){// metodo pega a raiz e o seu filho a direita, transforma o filho a direita na nova raiz, e a raiz no filho a esquerda da nova raiz
        No<T> aux = r.getFilhoDireita();
        r.setFilhoDireita(aux.getFilhoEsquerda());
        aux.setFilhoEsquerda(r);
        return aux;
    }

    protected No<T> rotacaoDireitaEsquerda(No<T> r){// metodo faz uma rotação a direita do filho da raiz depois faz uma rotação a esquerda com a raiz
        r.setFilhoDireita(rotacaoDireita(r.getFilhoDireita()));
        return rotacaoEsquerda(r);
    }

    protected No<T> rotacaoDireita(No<T> r){// metodo pega a raiz e o seu filho a esquerda, transforma o filho a esquerda na nova raiz, e a raiz no filho a direita da nova raiz
        No<T> aux = r.getFilhoEsquerda();
        r.setFilhoEsquerda(aux.getFilhoDireita());
        aux.setFilhoDireita(r);
        return aux;
    }

    protected No<T> rotacaoEsquerdaDireita(No<T> r){// metodo faz uma rotação a esquerda do filho da raiz e depois faz uma rotação a direita com a raiz
        r.setFilhoEsquerda(rotacaoEsquerda(r.getFilhoEsquerda()));
        return rotacaoDireita(r);
    }
}
