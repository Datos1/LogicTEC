package view;

import data.List;
import main.Commons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by pablo on 24/10/14.
 * This class is a Rectangle with image and a Reference
 */
public class Componente extends Rectangle implements Commons {
    private Image image;
    private int reference;
    private int inputs;
    private int outputs;
    private List<Salida> entradas = new List<Salida>();


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
        for (int i = 0; i < inputs; i++) {
            entradas.append(null);
        }
    }
    /**
     * Creates a label
     *
     * @param path ruta de imagen
     * @return la imagen
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
     * hace el set de la entrada
     * @param id numero de entrada
     * @param salida apuntando
     */
    public void setEntrada(int id, Salida salida) {
        entradas.set(id, salida);
    }

    /**
     * @param id number of input
     * @return salida
     */
    public Salida getEntrada(int id) {
        return entradas.get(id);
    }

    /**
     * @return number of inputs
     */
    public int getInputs() {
        return inputs;
    }

    /**
     * @return number of outputs
     */
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

    /**
     * Tells you if a point is in an input
     *
     * @return devuelve la posicion o -1 como error
     */
    public int pointInInputs(Point point) {
        return pointInAux(point, inputs, this.x);
    }

    /**
     * Tells you if a point is in an input
     *
     * @return devuelve la posicion o -1 como error
     */
    public int pointInOutputs(Point point) {
        return pointInAux(point, outputs, this.x + IMAGE_SIZE);
    }

    /**
     * Ayuda a revisar collision en los puntos clave
     *
     * @param num
     * @return posicion o -1 como error
     */
    private int pointInAux(Point point, int num, int posX) {
        final int HALF_SIZE = NODO_SIZE / 2;
        double largoDiv = IMAGE_SIZE / (num + 1);
        double posXInicial = posX - HALF_SIZE;
        double posYInicial = largoDiv - HALF_SIZE;
        for (int i = 0; i < num; i++) {
            Rectangle rect = new Rectangle((int) (this.x + posXInicial), (int) (this.y + posYInicial + (i * largoDiv)), NODO_SIZE, NODO_SIZE);
            if (rect.contains(point))
                return i;
        }
        return -1;
    }
}

