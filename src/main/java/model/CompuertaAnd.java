package model;

/**
 * Created by pablo on 15/10/14.
 */
public class CompuertaAnd extends CompuertaLogica {

    /**
     * Asigna la cantidad de entradas y salidas
     */
    public CompuertaAnd(int id, int numEntradas) {
        super(id, numEntradas, 1);//x entradas , una salida
    }


    @Override
    public boolean getOutput() {
        for (int j = 0; j < getNumInputs(); j++)
            if (!getInput(j)) return false;
        return true;
    }

}
