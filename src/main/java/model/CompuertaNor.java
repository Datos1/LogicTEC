package model;

/**
 * Created by Stiven on 10/30/2014.
 */
public class CompuertaNor extends CompuertaLogica{

    /**
     * Asigna la cantidad de entradas y salidas
     */
    public CompuertaNor(int id, int numEntradas) {
        super(id, numEntradas, 1);//x entradas , una salida
    }



    @Override
    public boolean getOutput() {

        for (int j = 0; j < getNumInputs(); j++)
            if (getInput(j)) return false;
        return true;
    }
}
