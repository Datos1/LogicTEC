package model;

/**
 * Created by Stiven on 10/30/2014.
 */
public class CompuertaNAnd extends CompuertaLogica{

    /**
     * Asigna la cantidad de entradas y salidas
     */
    public CompuertaNAnd(int id, int numEntradas) {
       super(id, numEntradas, 1);//x entradas , una salida
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
            if (getInput(j)==false) return true;
        return false;
    }

}
