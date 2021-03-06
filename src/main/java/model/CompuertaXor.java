package model;

/**
 * Created by pablo on 15/10/14.
 */
public class CompuertaXor extends CompuertaLogica {
    /**
     * Asigna la cantidad de entradas y salidas
     */
    public CompuertaXor(int id, int numEntradas) {
        super(id, numEntradas, 1);//x entradas , una salida
    }


    @Override
    public boolean getOutput() {

        int counter = 0;
        for (int j = 0; j < getNumInputs(); j++)
            if (getInput(j))
                counter++;

        return (counter % 2 != 0);
    }
}
