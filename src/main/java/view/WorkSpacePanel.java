package view;

import controller.LogicTecController;
import data.List;
import main.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created by pablo on 24/10/14.
 */
public class WorkSpacePanel extends JPanel implements Commons, MouseListener, MouseMotionListener {
    private List<Componente> components = new List<Componente>();
    private Dimension size;
    private Rectangle dropArea;
    private LogicTecController controller;
    private ActionListener listener;
    private boolean dragging = false;
    private Componente draggedImage;
    private Point startPoint;


    public WorkSpacePanel(int w, int h, LogicTecController controller) {
        super();
        this.size = new Dimension(w, h);
        this.setSize(size);
        this.setBackground(new Color(218, 218, 218));
        dropArea = new Rectangle(0, size.height - IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawRect((int) dropArea.getX(), (int) dropArea.getY(),
                (int) dropArea.getWidth(), (int) dropArea.getHeight());// draw drop area
        for (int i = 0; i < components.getLength(); i++) {
            Componente component = components.get(i);
            g.drawImage(component.getImage(), component.x, component.y, null);
            // draws the image in the current position
            for (int j = 0; j < component.getInputs(); j++) {
                //int size =
                //g.setColor(Color.white);
                //g.fillRect(component.get,100,10,10);
            }
            for (int j = 0; j < component.getOutputs(); j++) {

            }
        }

    }

    private void deleteComponent(int id) {
        listener.actionPerformed(new ActionEvent(this, id, DELETE));
    }
    /**
     * Notifies logic to create something
     * @param text
     */
    public void createComponent(String text) {
        if (freeSpace()) {
            int id = components.getLength();
            List<Integer> options = new List();
            if (text.equals(CUSTOM)) { // if file is a Json
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    listener.actionPerformed(new ActionEvent(this, id,
                            text + "#" + selectedFile.getPath()));
                    options = new List(0, 0);
                }
            } else {

                options = getOptions(text);
                listener.actionPerformed(new ActionEvent(this, id,
                        text + "#" + options.get(0) + "#" + options.get(1)));
            }
            String path = getPath(text);
            components.append(new Componente(id, dropArea, path, options.get(0), options.get(1)));

            repaint();
        }
    }

    private int askNumber(String type, String text) {
        try {
            String txt = JOptionPane.showInputDialog("Inserte numero de " + type + " para " + text + " :");
            int num = Integer.parseInt(txt);
            return num;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, ERROR_INT);
            return askNumber(type, text);
        }
    }

    private List<Integer> getOptions(String text) {

        int inputs = askNumber("Entradas", text);
        int outputs = askNumber("Salidas", text);
        return new List<Integer>(inputs, outputs);
    }
    private String getPath(String text) {
        for (int i = 0; i < COMPONENTS.getLength(); i++) {
            if (COMPONENTS.get(i).equals(text))
                return COMPONENTS_PATH.get(i);
        }
        return "";
    }


    private boolean freeSpace() {
        for (int i = 0; i < components.getLength(); i++) {

            if (dropArea.intersects(components.get(i))) {
                JOptionPane.showMessageDialog(this, FREE_SPACE);
                return false;

            }

        }
        return true;
    }

    public void addListener(ActionListener pListener) {
        this.listener = pListener;
    }


    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param click
     */
    @Override
    public void mousePressed(MouseEvent click) {
        for (int i = 0; i < components.getLength(); i++) {

            if (components.get(i).contains(click.getPoint())) {
                dragging = true;
                startPoint = click.getPoint();
                draggedImage = components.get(i);
                break;
            }
        }
    }


    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged. Moves image if necessary.
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggedImage != null) {
            draggedImage.move(startPoint, e.getPoint());
            startPoint = e.getPoint();
        }
        repaint();
    }
    /**
     * Set environment to default
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        draggedImage = null;
    }
    /**
     * Do Nothing!
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * Do Nothing!
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Do Nothing!
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    /**
     * Do Nothing!
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}

