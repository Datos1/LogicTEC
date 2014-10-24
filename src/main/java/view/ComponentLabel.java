package view;

import model.CompuertaLogica;

import java.awt.*;

/**
 * Created by pablo on 24/10/14.
 */
public class ComponentLabel {
    private Rectangle rectangle;
    private CompuertaLogica reference;

    /**
     * Tells You if the input object is equal to the original
     *
     * @param pReference
     * @return
     */
    public boolean equals(CompuertaLogica pReference) {
        return reference == pReference;
    }

    /**
     * returns rectangle
     *
     * @return rectangle from position
     */
    public Rectangle getRect() {
        return rectangle;
    }
}

