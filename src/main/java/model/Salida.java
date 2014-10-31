package model;

/**
 * Clase que almacena una de las salidas
 * Created by pablo on 15/10/14.
 */
public class Salida {
    CompuertaLogica parent;
    int numSalida;

    /**
     * Iniciamos con el padre y numero de salida almacenados
     *
     * @param pParent
     * @param pNumSalida
     */
    public Salida(CompuertaLogica pParent, int pNumSalida) {
        this.parent = pParent;
        this.numSalida = pNumSalida;
    }

    /**
     * Devolvemos el valor de la salida
     *
     * @return
     */
    public boolean getValue() {
        return parent.getOutput();
    }
}
