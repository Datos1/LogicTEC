package model;

import data.List;
import data.Node;
import main.Commons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pablo on 24/10/14.
 */
public class LogicTecModel implements Commons {
    private VFTable tablaVerdad;
    private ActionListener listener;
    private List<CompuertaLogica> compuertas = new List<CompuertaLogica>();
    private List<Entrada> entradas = new List<Entrada>();
    private List<CompuertaLogica> salidas = new List<CompuertaLogica>();

    public void add(String[] command) {
        int id = Integer.parseInt(command[0]);
        String type = command[1];
        if (type.equals(CUSTOM))
            compuertas.append(new CompuertaCustom(id, 0, 0));
        else {
            int inputs = Integer.parseInt(command[2]);
            if (type.equals(AND)) compuertas.append(new CompuertaAnd(id, inputs));
            else if (type.equals(OR)) compuertas.append(new CompuertaOr(id, inputs));
            else if (type.equals(XOR)) compuertas.append(new CompuertaXor(id, inputs));
            else if (type.equals(NOT)) compuertas.append(new CompuertaNot(id));
            else if (type.equals(NAND)) compuertas.append(new CompuertaNAnd(id,inputs));
            else if (type.equals(NOR)) compuertas.append(new CompuertaNor(id,inputs));
            else if (type.equals(XNOR)) compuertas.append(new CompuertaXNor(id,inputs));
            else if (type.equals(IN)) {
                Entrada entrada = new Entrada(id, false);
                entradas.append(entrada);
                compuertas.append(entrada);
            } else if (type.equals(OUT)) {
                CompuertaLogica salida = new CompuertaLogica(id, 1, 0);
                salidas.append(salida);//cero salidas una entrada
                compuertas.append(salida);
            }
        }


    }

    public void setEntrada(int entrada,boolean i){
        Node o=entradas.getNode(entrada);
        Entrada a=(Entrada)o.getData();
        a.change(i);
    }
    public boolean getSalida(int Salida){
        Node o=salidas.getNode(Salida);
        CompuertaLogica a=(CompuertaLogica)o.getData();
        return a.getOutput();
    }

    public void remove(String[] command) {
        int id = Integer.parseInt(command[0]);
        for (int i = 0; i < compuertas.getLength(); i++) {
            if (compuertas.get(i).equals(id)) compuertas.remove(i);
        }
    }

    public void set(String[] command) {

        int idIn = Integer.parseInt(command[0]);
        int noIn = Integer.parseInt(command[1]);
        int idOut = Integer.parseInt(command[2]);
        int noOut = Integer.parseInt(command[3]);
        CompuertaLogica compIn = null;
        CompuertaLogica compOut = null;
        for (int i = 0; i < compuertas.getLength(); i++) {
            CompuertaLogica compuerta = compuertas.get(i);
            if (compuerta.equals(idIn)) compIn = compuerta;
            else if (compuerta.equals(idOut)) compOut = compuerta;
            if (compIn != null && compOut != null) break;

        }
        if (compIn == null || compOut == null)
            System.out.println("ERROR");
        compIn.setInput(noIn, new Salida(compOut, noOut));
        check();
    }

    public void crearTabla(){
        tablaVerdad=new VFTable(this.entradas.getLength(),this.salidas.getLength(),this);
        tablaVerdad.crearTabla();
        tablaVerdad.completarTabla();
        tablaVerdad.printTabla();
    }

    public void check() {
        try {
            for (int i = 0; i < salidas.getLength(); i++) {
                CompuertaLogica salida = salidas.get(i);
                boolean valor = salida.getInput(0);
                listener.actionPerformed(new ActionEvent(this, SET_INOUT, salida.getReference() + "#" + valor));
            }
        } catch (NullPointerException e) {
            listener.actionPerformed(new ActionEvent(this, CHECKC, ""));
        }
    }

    public void addListener(ActionListener pListener) {
        listener = pListener;
    }

    public void setInOut(String[] command) {
        int id = Integer.parseInt(command[0]);
        boolean bool = Boolean.parseBoolean(command[1]);
        for (int i = 0; i < entradas.getLength(); i++) {
            Entrada entrada = entradas.get(i);
            if (entrada.equals(id)) entrada.change(bool);
        }
        check();
    }
}
