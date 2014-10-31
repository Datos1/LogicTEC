package model;

import data.Node;

/**
 * Compuerta que se comporta como una caja negra dependiendo de un xml
 * Created by pablo on 15/10/14.
 */
public class CompuertaCustom extends CompuertaLogica {
    VFTable tabla;
    /**
     * Asigna la cantidad de entradas y salidas
     *
     * @param pEntradas
     * @param pSalidas
     */
    public CompuertaCustom(int id, int pEntradas, int pSalidas) {
        super(id, pEntradas, pSalidas);
    }

    public void agregarTabla(VFTable Tabla){
        tabla=Tabla;
    }
    public boolean getOutput(int Salida) {
        boolean o=tabla.getSalida(super.getInputs(),Salida);
        return o;
    }
}
