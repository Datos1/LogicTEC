package view;

/**
 * Created by pablo on 25/10/14.
 */
public class Salida {
    private Componente parent;

    public Salida(Componente pParent, int id) {
        parent = pParent;
    }

    public Componente getParent() {
        return parent;
    }
}
