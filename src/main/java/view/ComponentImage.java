package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by pablo on 24/10/14.
 */
public class ComponentImage {
    private Image image;
    private Rectangle rectangle;
    private int reference;

    /**
     * Constructor assigns label and reference
     *
     * @param path
     * @param pRef
     */
    public ComponentImage(String path, int pRef, Rectangle posRect) {
        reference = pRef;
        image = getImage(path);
        rectangle = posRect;
    }

    /**
     * Creates a label
     *
     * @param path
     * @return
     */
    private Image getImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar programa");
        }
        return null;
    }

    /**
     * Tells You if the input object is equal to the original
     *
     * @param pReference
     * @return
     */

    public boolean equals(int pReference) {
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

    /**
     * @return label for image representation
     */
    public Image getImage() {
        return image;
    }
}

