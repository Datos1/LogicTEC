package view;

import java.awt.*;

/**
 * Created by pablo on 25/10/14.
 */
public class Nodo extends Rectangle {
    private Componente parent;

    public Nodo(Rectangle rect, Componente pParent) {
        this.setRect(rect);
        parent = pParent;
    }

    public Componente getParent() {
        return parent;
    }
}
