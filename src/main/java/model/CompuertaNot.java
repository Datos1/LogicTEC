package model;

/**
 * Created by pablo on 15/10/14.
 */
public class CompuertaNot extends CompuertaLogica {
    /**
     * Asigna la cantidad de entradas y salidas
     */
    public CompuertaNot(int id) {
        super(id, 1, 1);//Dos entradas , una salida
    }

    @Override
    public boolean getOutput() {

        return !getInput(0);
    }
}
