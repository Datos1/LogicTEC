package model;

/**
 * Compuerta que se comporta como una caja negra dependiendo de un xml
 * Created by pablo on 15/10/14.
 */
public class CompuertaCustom extends CompuertaLogica {
    /**
     * Asigna la cantidad de entradas y salidas
     *
     * @param pEntradas 
     * @param pSalidas
     */
    public CompuertaCustom(int id, int pEntradas, int pSalidas) {
        super(id, pEntradas, pSalidas);
    }


}
