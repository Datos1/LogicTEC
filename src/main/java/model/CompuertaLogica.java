package model;

import data.List;
import data.Node;

/**
 * Created by pablo on 15/10/14.
 * Compuerta Logica contiene los standards para cada compuerta
 */
public class CompuertaLogica {
    private int numOutputs;
    private List<Salida> inputs;
    private int reference;

    /**
     * Asigna la cantidad de entradas y salidas
     *
     * @param pEntradas
     * @param pSalidas
     */
    public CompuertaLogica(int id, int pEntradas, int pSalidas) {
        reference = id;
        numOutputs = pSalidas;
        inputs = new List<Salida>();
        for (int i = 0; i < pEntradas; i++) {
            inputs.append(null);
        }
    }

    /**
     * @param i       numero de la entrada de 0 a n-1
     * @param pSalida La compuerta de entrada
     * @return devuelve true si el i existe, false al contrario
     */
    public boolean setInput(int i, Salida pSalida) {
        return inputs.set(i, pSalida);
    }

    protected boolean getInput(int i) {
        if (inputs.get(i) == null) {
           // System.out.println("Circuito Incompleto");
            throw new NullPointerException();
        }
        return inputs.get(i).getValue();
    }

    public List<Salida> getInputs(){return inputs;}

    public boolean getOutput() {
        Node o=inputs.getHead();
        Salida a=(Salida)o.getData();
        return a.getValue();
    }

    /**
     * @return Number of Inputs
     */
    public int getNumInputs() {
        return inputs.getLength();
    }

    /**
     * @return Number of outputs
     */
    public int getNumOutputs() {
        return numOutputs;
    }

    public boolean equals(int id) {
        return reference == id;
    }

    public int getReference() {
        return reference;
    }
}
