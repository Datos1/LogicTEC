package view;

import java.awt.*;

/**
 * Created by pablo on 25/10/14.
 */
public class Nodo extends Rectangle {
    private Componente parent;
    private Nodo inLink;
    private int id;

    public Nodo(Rectangle rect, int pId, Componente pParent) {
        id = pId;
        this.setRect(rect);
        parent = pParent;
    }

    public Componente getParent() {
        return parent;
    }

    /**
     * Tells you if it has a link
     *
     * @return bool
     */
    public boolean hasInLink() {
        return inLink != null;
    }

    public Nodo getInLink() {
        return inLink;
    }

    /**
     * Adds a link for drawing
     *
     * @param in entrada
     */
    public void setInLink(Nodo in) {
        inLink = in;
    }

    public int getId() {
        return id;
    }
}
