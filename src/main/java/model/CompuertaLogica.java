package model;

import data.List;

/**
 * Created by pablo on 15/10/14.
 * Compuerta Logica contiene los standards para cada compuerta
 */
public class CompuertaLogica {
    private int numOutputs;
    private List<Salida> inputs;

    /**
     * Asigna la cantidad de entradas y salidas
     *
     * @param pEntradas
     * @param pSalidas
     */
    public CompuertaLogica(int pEntradas, int pSalidas) {
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
        return inputs.get(i).getValue();
    }

    /**
     * Obtener salida en pin
     *
     * @param i numero de la salida de 0 a n-1
     * @return el valor de verdad de la salida o si no existe false
     */
    public boolean getOutput(int i) {
        if (i >= getNumOutputs())
            throw new NullPointerException();
        return false;
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

}
