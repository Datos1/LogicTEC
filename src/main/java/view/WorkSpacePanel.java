package view;

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
    private ActionListener listener;
    private boolean dragging = false;
    private boolean inside = false;
    private Componente draggedComponente;
    private Nodo draggedNodo;
    private Point startPoint;
    private Point lastPoint;


    public WorkSpacePanel(int w, int h) {
        super();
        this.size = new Dimension(w, h);
        this.setSize(size);
        this.setBackground(new Color(218, 218, 218));
        dropArea = new Rectangle(0, size.height - IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
        addMouseListener(this);
        addMouseMotionListener(this);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.black);
        g2d.drawLine(0, 0, 100, 100);
        paintDraggedLine(g2d);
        paintDropArea(g2d);
        paintComponentsAndMore(g2d);
        g2d.dispose();
    }

    /**
     * Paints Components and Nodes
     *
     * @param g2d graphics
     */
    private void paintComponentsAndMore(Graphics2D g2d) {
        for (
                int i = 0;
                i < components.getLength(); i++)

        {
            Componente component = components.get(i);
            g2d.drawImage(component.getImage(), component.x, component.y, null);
            // draws the image in the current position
            List<Nodo> inpts = component.getRectEntradas();
            for (int j = 0; j < inpts.getLength(); j++) {
                g2d.setColor(Color.white);
                Rectangle rect = inpts.get(j);
                g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
            List<Nodo> outpts = component.getRectSalidas();
            for (int j = 0; j < outpts.getLength(); j++) {
                g2d.setColor(Color.white);
                Rectangle rect = outpts.get(j);
                g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }


    /**
     * Paints Drop Area
     *
     * @param g2d graphics
     */
    private void paintDropArea(Graphics2D g2d) {
        float dash[] = {10.0f};
        g2d.setStroke(new BasicStroke(2.5f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, dash, 0.0f));
        g2d.drawRoundRect((int) dropArea.getX(), (int) dropArea.getY(),
                (int) dropArea.getWidth(), (int) dropArea.getHeight(), 5, 5);// draw drop area
    }

    /**
     * Paints temporal line
     *
     * @param g2d graphics
     */
    private void paintDraggedLine(Graphics2D g2d) {
        if (draggedNodo != null) {
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(startPoint.x, startPoint.y, lastPoint.x, lastPoint.y);
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
            int inputs = 0;
            int outputs = 0;
            if (text.equals(CUSTOM)) { // if file is a Json
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    listener.actionPerformed(new ActionEvent(this, id,
                            text + "#" + selectedFile.getPath()));

                }
            } else {

                inputs = askNumber("Entradas", text, MAX_ENTRADAS);
                outputs = 1;
                listener.actionPerformed(new ActionEvent(this, id,
                        text + "#" + inputs + "#" + outputs));
            }
            String path = getPath(text);
            components.append(new Componente(id, dropArea, path, inputs, outputs));

            repaint();
        }
    }

    private int askNumber(String type, String text, int max) {
        try {
            String txt = JOptionPane.showInputDialog("Inserte numero de " + type + " para " + text + " :");
            int num = Integer.parseInt(txt);
            if (num > 10 || num < 0)
                throw new RuntimeException();
            return num;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, ERROR_INT);
            return askNumber(type, text, max);
        }
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
            Componente component = components.get(i);
            for (int j = 0; j < component.getOutputs(); j++) {
                if (component.getRectSalidas().get(j).contains(click.getPoint())) {
                    dragging = true;
                    startPoint = click.getPoint();
                    lastPoint = click.getPoint();
                    draggedNodo = component.getRectSalidas().get(j);
                    return;
                }
            }

            if (component.contains(click.getPoint())) {
                dragging = true;
                lastPoint = click.getPoint();
                draggedComponente = components.get(i);
                return;
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
        if (inside)
            if (draggedComponente != null) {
                draggedComponente.move(lastPoint, e.getPoint());
                lastPoint = e.getPoint();
            } else if (draggedNodo != null) {
                if (startPoint == null) {
                    startPoint = e.getPoint();
                }
                lastPoint = e.getPoint();
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
        draggedComponente = null;
        draggedNodo = null;
        startPoint = null;
        lastPoint = null;
        repaint();
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
        inside = true;
    }
    /**
     * Do Nothing!
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        inside = false;
    }
}

