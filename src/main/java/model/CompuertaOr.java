package model;

/**
 * Created by pablo on 15/10/14.
 */
public class CompuertaOr extends CompuertaLogica {
    /**
     * Asigna la cantidad de entradas y salidas
     */
    public CompuertaOr() {
        super(2, 1);//Dos entradas , una salida
    }

    public CompuertaOr(int numEntradas) {
        super(numEntradas, 1);//x entradas , una salida
    }

    /**
     * Obtener salida en pin
     *
     * @param i numero de la salida de 0 a n-1
     * @return el valor de verdad de la salida
     */
    @Override
    public boolean getOutput(int i) {
        if (super.getOutput(i)) return false;
        for (int j = 0; j < getNumInputs(); j++)
            if (getInput(i)) return true;
        return false;
    }
}
