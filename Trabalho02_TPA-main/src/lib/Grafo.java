package lib;

import java.util.Iterator;
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

    public boolean verificarCiclo(){
        for (int i=0 ; i<this.listaNos.size() ; i++){
            if(verificarCicloGrafoConexoND(this.listaNos.get(i))){
                return true;
            }
        }
        return false;
    }

    private boolean verificarCicloGrafoConexoND(No<T> no_inicial){
        LinkedList<No<T>> nos_visitados = new LinkedList<No<T>>();
        LinkedList<No<T>> nos_restantes = new LinkedList<No<T>>();
        nos_restantes.add(no_inicial);
        No<T> no_atual;

        while(!(nos_restantes.isEmpty())){
            no_atual = nos_restantes.removeFirst();
            nos_visitados.add(no_atual);

            for (int i=0 ; i<no_atual.getVizinhos().size() ; i++){
                if(nos_visitados.contains(no_atual.getVizinhos().get(i))){
                    return true;
                }
                else if(!(nos_restantes.contains(no_atual.getVizinhos().get(i)))) {
                    nos_restantes.add(no_atual.getVizinhos().get(i));
                }
            }
        }
        return false;
    }

    // REMOVER INTERSEÇÂO ENTRE DUAS LINKED LISTS PARA ENCONTRAR OS NOS RESTANTES (FORA DO CAMINHO)
    public String ordem_topologica(){
        LinkedList<No<T>> todos_nos = this.listaNos;
        LinkedList<No<T>> ordem_topologica = new LinkedList<No<T>>();

        String ot = "";

        while(!todos_nos.isEmpty()){
                LinkedList<No<T>> ot_do_no_i = ordem_topologicaNo(todos_nos.get(0));
                ordem_topologica.addAll(ot_do_no_i);
                removerElementos(todos_nos, ot_do_no_i);
        }
        for (int i=0 ; i<ordem_topologica.size() ; i++) {
        ot = ot.concat(ordem_topologica.get(i).getValor().toString() + ", ");
        }
        ot = ot.substring(0, ot.length() - 2);
        return ot;
    }

    public LinkedList<No<T>> ordem_topologicaNo(No<T> no_inicial){
        LinkedList<No<T>> nos_visitados = new LinkedList<No<T>>();
        LinkedList<No<T>> nos_restantes = new LinkedList<No<T>>();
        nos_restantes.add(no_inicial);
        No<T> no_atual;

        while(!(nos_restantes.isEmpty())){
            no_atual = nos_restantes.removeFirst();
            nos_visitados.add(no_atual);

            for (int i=0 ; i<no_atual.getVizinhos().size() ; i++){
                if(nos_visitados.contains(no_atual.getVizinhos().get(i))){
                    LinkedList<No<T>> loop_list = new LinkedList<>();
                    return loop_list;
                }
                else if(!(nos_restantes.contains(no_atual.getVizinhos().get(i)))) {
                        nos_restantes.add(no_atual.getVizinhos().get(i));
                    }
            }
        }
        return nos_visitados;
    }

    public static <T> void removerElementos(LinkedList<No<T>> lista_1, LinkedList<No<T>> lista_2) {
        // Cria um iterador para percorrer a lista_2
        Iterator<No<T>> iterator = lista_2.iterator();

        // Itera sobre a lista_2
        while (iterator.hasNext()) {
            No<T> elemento = iterator.next();

            // Remove o elemento da lista_1, se presente
            lista_1.remove(elemento);
        }
    }

}
