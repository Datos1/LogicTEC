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

    /**
     * Obtener salida en pin
     *
     * @param i numero de la salida de 0 a n-1
     * @return el valor de verdad de la salida
     */
    @Override
    public boolean getOutput(int i) {
        if (super.getOutput(i)) return false;
        return !getInput(i);
    }
}
