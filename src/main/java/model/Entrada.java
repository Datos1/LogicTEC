package model;

/**
 * Clase para iniciar en Ground o Vcc
 * Created by pablo on 15/10/14.
 */
public class Entrada extends CompuertaLogica {
    private boolean value;

    /**
     * Constructor asigna numero de entradas y salidas
     * y asigna el valor para la entrada
     *
     * @param pValue boolean
     */
    public Entrada(int id, boolean pValue) {
        super(id, 0, 1);//0 entradas, 1 salida
        this.value = pValue;

    }

    public void change(boolean pValue) {
        value = pValue;
    }

    @Override
    public boolean getOutput() {
        return this.value;
    }

}