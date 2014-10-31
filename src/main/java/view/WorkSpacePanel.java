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
    private int id;
    private Rectangle dropArea;
    private ActionListener listener;
    private boolean dragging = false;
    private boolean theme = false;
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
        dropArea = new Rectangle(0, 0, IMAGE_SIZE, IMAGE_SIZE);
        addMouseListener(this);
        addMouseMotionListener(this);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
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
        for (int i = 0; i < components.getLength(); i++)

        {
            Componente component = components.get(i);
            g2d.drawImage(component.getImage(theme), component.x, component.y, null);
            // draws the image in the current position
            List<Nodo> inpts = component.getRectEntradas();
            for (int j = 0; j < inpts.getLength(); j++) {
                g2d.setColor(Color.white);
                Nodo entrada = inpts.get(j);
                g2d.fillRect(entrada.x, entrada.y, entrada.width, entrada.height);
                if (entrada.hasInLink())
                    drawLine(g2d, entrada.x + NODO_SIZE / 2,
                            entrada.y + NODO_SIZE / 2,
                            entrada.getInLink().x + NODO_SIZE / 2,
                            entrada.getInLink().y + NODO_SIZE / 2);
            }
            List<Nodo> outpts = component.getRectSalidas();
            for (int j = 0; j < outpts.getLength(); j++) {
                g2d.setColor(Color.white);
                Nodo salida = outpts.get(j);
                g2d.fillRect(salida.x, salida.y, salida.width, salida.height);
            }
        }
    }


    /**
     * Paints Drop Area
     *
     * @param g2d graphics
     */
    private void paintDropArea(Graphics2D g2d) {
        g2d.setColor(Color.black);
        float dash[] = {10.0f};
        g2d.setStroke(new BasicStroke(2.5f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, dash, 0.0f));
        g2d.drawRoundRect((int) dropArea.getX(), (int) dropArea.getY(),
                (int) dropArea.getWidth(), (int) dropArea.getHeight(), 5, 5);// draw drop area
    }

    /**
     * Draws a nice line
     */
    private void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        if (x1 > x2)//swap elements
        {
            int xTemp = x1;
            int yTemp = y1;
            x1 = x2;
            y1 = y2;
            x2 = xTemp;
            y2 = yTemp;

        }
        int distance = x2 - x1;
        int inter = distance * 1 / 2;
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(x1, y1, x1 + inter, y1);
        g2d.drawLine(x1 + inter, y1, x1 + inter, y2);
        g2d.drawLine(x1 + inter, y2, x2, y2);
    }

    /**
     * Paints temporal line
     *
     * @param g2d graphics
     */
    private void paintDraggedLine(Graphics2D g2d) {
        if (draggedNodo != null) {

            drawLine(g2d, startPoint.x, startPoint.y, lastPoint.x, lastPoint.y);
        }
    }
    private void deleteComponent(int id) {
        components.remove(id);
        listener.actionPerformed(new ActionEvent(this, REMOVE, id + ""));
        repaint();
    }
    /**
     * Notifies logic to create something
     * @param text
     */
    public void createComponent(String text) {
        if (freeSpace()) {
            int inputs = 0;
            int outputs = 0;
            if (text.equals(CUSTOM)) { // if file is a Json
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    listener.actionPerformed(new ActionEvent(this, ADD,
                            id + "#" + text + "#" + selectedFile.getPath()));

                }
            } else {
                if (text.equals(IN)) {
                    inputs = 0;
                    outputs = 1;
                } else if (text.equals(OUT)) {
                    inputs = 1;
                    outputs = 0;
                } else if (text.equals(NOT)) {
                    inputs = 1;
                    outputs = 1;
                } else {
                    inputs = askNumber("Entradas", text, MAX_ENTRADAS);
                    outputs = 1;
                }
                listener.actionPerformed(new ActionEvent(this, ADD,
                        id + "#" + text + "#" + inputs));
            }

            String path = getPath(text, 0);
            String path2 = getPath(text, 1);
            components.append(new Componente(id++, dropArea, path, path2, inputs, outputs));
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

    private String getPath(String text, int id) {
        if (text.equals(IN) || text.equals(OUT))
            if (id == 0)
                return LOW_PATH;
            else
                return HIGH_PATH;
        for (int i = 0; i < COMPONENTS.getLength(); i++) {
            if (COMPONENTS.get(i).equals(text))
                if (id == 0)
                    return COMPONENTS_PATH.get(i);
                else
                    return COMPONENTS2_PATH.get(i);
        }
        return "";
    }

    public void setOut(int i, boolean aBoolean) {
        for (int j = 0; j < components.getLength(); j++) {
            Componente component = components.get(j);
            if (component.equals(i)) {
                component.changeDigitalValue(aBoolean);
                break;
            }

        }
        repaint();
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
     * @param click
     */
    @Override
    public void mouseReleased(MouseEvent click) {
        dragging = false;
        draggedComponente = null;
        if (draggedNodo != null) {
            for (int i = 0; i < components.getLength(); i++) {
                Componente component = components.get(i);
                List<Nodo> inpts = component.getRectEntradas();
                for (int j = 0; j < component.getInputs(); j++) {
                    Nodo entrada = inpts.get(j);
                    if (entrada.contains(click.getPoint())) {
                        addLink(entrada, draggedNodo);
                    }
                }

            }
            draggedNodo = null;
        }
        startPoint = null;
        lastPoint = null;
        repaint();
    }

    /**
     * Makes the connection between in and out
     * @param entrada
     * @param salida
     */
    public void addLink(Nodo entrada, Nodo salida) {
        if (entrada.hasInLink())
            return;
        entrada.setInLink(salida);
        listener.actionPerformed(new ActionEvent(this, SET,
                entrada.getParent().getReference() + "#" + entrada.getId() + "#"
                        + salida.getParent().getReference() + "#" + salida.getId()));

    }

    /**
     * Whuen user clicks
     * @param click
     */
    @Override
    public void mouseClicked(MouseEvent click) {
        for (int j = 0; j < components.getLength(); j++) {
            Componente component = components.get(j);

            if (component.contains(click.getPoint())) {
                if (SwingUtilities.isLeftMouseButton(click)) {
                    if (component.isIn()) {
                        boolean value = component.changeDigitalValue();
                        listener.actionPerformed(new ActionEvent(this, SET_INOUT, component.getReference() + "#" + value));
                    }
                }
                if (SwingUtilities.isRightMouseButton(click)) {
                    deleteComponent(j);


                }


            }

        }
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


    public void changeTheme() {
        theme = !theme;
        repaint();
    }
}



