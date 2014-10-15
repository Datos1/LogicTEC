package model;

/**
 * Created by pablo on 15/10/14.
 */
public interface CompuertaLogica {

    /**
     * @param i      numero de la entrada de 0 a n-1
     * @param pInput valor de entrada
     * @return devuelve true si el i existe, false al contrario
     */
    public boolean setInput(int i, boolean pInput);

    /**
     * Obtener salida en pin
     *
     * @param i numero de la salida de 0 a n-1
     * @return -1 si la salida no existe, 1 si es true y 0 si es false
     */
    public int getOutput(int i);

}
