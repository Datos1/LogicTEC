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


    @Override
    public boolean getOutput() {

        for (int j = 0; j < getNumInputs(); j++)
            if (getInput(j)==false) return true;
        return false;
    }

}
