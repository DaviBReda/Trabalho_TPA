package lib;

import java.util.LinkedList;

public class Grafo<T> {
    private LinkedList<No<T>> listaNos;

    public Grafo(){
        this.listaNos = new LinkedList<No<T>>();
    }

    public Grafo(LinkedList<No<T>> Lista){
        this.listaNos = Lista;
    }


    public void addElemento(No<T> novoNo){//ADICIONA PASSANDO NÓ
        this.listaNos.add(novoNo);
    }

    public void addElemento(T elem){//ADICIONA PASSANDO ELEMENTO
        No<T> No = new No<T>(elem);
        this.addElemento(No);
    }

    public void removeElemento(No<T> no){//REMOVE PASSANDO NÓ
        this.listaNos.remove(no);
    }

    public void removeElemento(T elem){//REMOVE PASSANDO ELEMENTO
        for (int i=0 ; i<this.listaNos.size() ; i++){
            if (this.listaNos.get(i).getValor() == elem){
                this.listaNos.remove(i);
            }
        }
    }

    //-------------------ADICIONA ARESTA PASSANDO NÓS---------------------------------
    public void addCaminho(No<T> origem, No<T> dest, int peso){
        origem.setVizinho(dest, peso);
    }

    public void addCaminhoND(No<T> origem, No<T> dest, int peso){
        origem.setVizinhoND(dest, peso);
    }

    //-------------------ADICIONA ARESTA PASSANDO ELEMENTOS---------------------------------
    public void addCaminho(T origem, T dest, int peso){
        for (int i=0 ; i<this.listaNos.size() ; i++){
            if (this.listaNos.get(i).getValor() == origem){
                for (int j=0 ; j<this.listaNos.size() ; j++){
                    if (this.listaNos.get(j).getValor() == dest){
                        addCaminho(this.listaNos.get(i), this.listaNos.get(j), peso);
                    }
                }
            }
        }
    }

    public void addCaminhoND(T origem, T dest, int peso){
        for (int i=0 ; i<this.listaNos.size() ; i++){
            if (this.listaNos.get(i).getValor() == origem){
                for (int j=0 ; j<this.listaNos.size() ; j++){
                    if (this.listaNos.get(j).getValor() == dest){
                        addCaminhoND(this.listaNos.get(i), this.listaNos.get(j), peso);
                    }
                }
            }
        }
    }

    //-------------------REMOVE ARESTA PASSANDO NÓS---------------------------------
    public void removeCaminho(No<T> origem, No<T> dest, int peso){
        origem.removeVizinho(dest, peso);
    }

    public void removeCaminhoND(No<T> origem, No<T> dest, int peso){
        origem.removeVizinhoND(dest, peso);
    }

    //-------------------REMOVE ARESTA PASSANDO ELEMENTOS---------------------------------
    public void removeCaminho(T origem, T dest, int peso){
        for (int i=0 ; i<this.listaNos.size() ; i++){
            if (this.listaNos.get(i).getValor() == origem){
                for (int j=0 ; j<this.listaNos.size() ; j++){
                    if (this.listaNos.get(j).getValor() == dest){
                        removeCaminho(this.listaNos.get(i), this.listaNos.get(j), peso);
                    }
                }
            }
        }
    }

    public void removeCaminhoND(T origem, T dest, int peso){
        for (int i=0 ; i<this.listaNos.size() ; i++){
            if (this.listaNos.get(i).getValor() == origem){
                for (int j=0 ; j<this.listaNos.size() ; j++){
                    if (this.listaNos.get(j).getValor() == dest){
                        removeCaminhoND(this.listaNos.get(i), this.listaNos.get(j), peso);
                    }
                }
            }
        }
    }
}
