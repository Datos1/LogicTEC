package model;

import data.List;

/**
 * Created by pablo on 29/10/14.
 */
public class CompuertaAbsorbente extends CompuertaLogica {
    private List<CompuertaLogica> salidas = new List<CompuertaLogica>();

    /**
     * Asigna la cantidad de entradas y salidas
     *
     * @param id
     * @param pSalidas
     */
    public CompuertaAbsorbente(int id, List<CompuertaLogica> pSalidas) {

        super(id, pSalidas.getLength(), pSalidas.getLength());
        for (int i = 0; i < salidas.getLength(); i++) {
            ;
        }

    }

    @Override
    public boolean getOutput() {
        for (int j = 0; j < getNumInputs(); j++) {
            getInput(j);
        }
        return false;
    }
}
