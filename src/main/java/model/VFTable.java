package model;

import data.List;
import data.Node;

/**
 * Created by Briam on 30/10/2014.
 */
public class VFTable {
    int alternado;
    int LargoLista;
    int NumeroEntradas;
    int NumeroSalidas;
    List<List> entradas;
    List<List> salidas;
    LogicTecModel parent;

    public VFTable(int Entradas,int Salidas,LogicTecModel par){
        entradas=new List();
        salidas=new List();
        NumeroEntradas=Entradas;
        NumeroSalidas=Salidas;
        LargoLista=(int)Math.pow(2,Entradas);
        alternado=LargoLista/2;
        parent=par;
    }
    public void crearTabla(){
        int x=0;
        while(x<NumeroEntradas){
            List<Boolean> newLista=new List();
            int y=0;
            while(y<LargoLista){
                for(int i=0;i<alternado;i++){
                    newLista.append(true);
                    y++;
                }
                for(int i=0;i<alternado;i++){
                    newLista.append(false);
                    y++;
                }
            }
            entradas.append(newLista);
            alternado=alternado/2;
            x++;
        }

    }
    public void completarTabla(){
        for(int i=0;i<NumeroSalidas;i++){
            List<Boolean> newList=new List();
            for(int j=0;j<LargoLista;j++){
                for(int k=0;k<entradas.getLength();k++){
                    boolean o=(Boolean)entradas.get(k).get(j);
                    parent.setEntrada(k,o);
                }
                boolean p=parent.getSalida(i);
                newList.append(p);

            }
            salidas.append(newList);
        }
    }
    public boolean getSalida(List<Salida> Listaentradas,int numSalida){
        int x=0;
        while(x<LargoLista){
            boolean esta=false;
            for(int y=0;y<entradas.getLength();y++){
                if((Boolean)Listaentradas.get(y).getValue()==entradas.get(y).get(x)){
                    if(y==entradas.getLength()-1)
                        esta=true;
                }
                else{break;}
            }
            if(esta)
                return (Boolean)salidas.get(numSalida).get(x);
            x++;
        }
        return true;
    }

    public void printTabla(){

        int x=0;
        Node tmp=entradas.getHead();
        while(x<entradas.getLength()){
            List<Integer> list= (List<Integer>) tmp.getData();
            list.print();
            tmp=tmp.getNext();
            x++;
        }
        int y=0;
        Node tmp1=salidas.getHead();
        while(y<salidas.getLength()){
            List<Integer> list= (List<Integer>) tmp1.getData();
            list.print();
            tmp1=tmp1.getNext();
            y++;
        }
    }
}
