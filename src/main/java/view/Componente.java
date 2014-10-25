package view;

import data.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by pablo on 24/10/14.
 * This class is a Rectangle with image and a Reference
 */
public class Componente extends Rectangle {
    private Image image;
    private int reference;
    private int inputs;
    private int outputs;
    private List<Salida> entradas;

    /**
     * Constructor assigns label and reference
     *
     * @param path
     * @param pRef
     */
    public Componente(int pRef, Rectangle posRect, String path, int pInputs, int pOutputs) {
        reference = pRef;
        image = getImage(path);
        this.setRect(posRect);
        inputs = pInputs;
        outputs = pOutputs;
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

    public int getInputs() {
        return inputs;
    }

    public int getOutputs() {
        return outputs;
    }

    /**
     * @return label for image representation
     */
    public Image getImage() {
        return image;
    }

    /**
     * Moves rectangle a distance
     *
     * @param point
     */
    public void move(Point point) {
        this.setLocation(this.x + point.x, this.y + point.y);
    }

    /**
     * Moves object according to a reference
     *
     * @param reference
     * @param distance
     */
    public void move(Point reference, Point distance) {
        distance.setLocation(distance.x - reference.x, distance.y - reference.y);
        this.move(distance);
    }
}

