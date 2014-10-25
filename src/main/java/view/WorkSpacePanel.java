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
    private List<ComponentImage> components = new List<ComponentImage>();
    private Dimension size;
    private Rectangle dropArea;
    private LogicTecController controller;
    private ActionListener listener;


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
            ComponentImage component = components.get(i);
            g.drawImage(component.getImage(), (int) component.getRect().getX(), (int) component.getRect().getY(), null);
            // draws the image in the current position
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
            int id = components.getLength() + 1;
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
            components.append(new ComponentImage(id, dropArea, path, options.get(0), options.get(1)));

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

            if (dropArea.intersects(components.get(i).getRect())) {
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
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        for (int i = 0; i < components.getLength(); i++) {
            if (components.get(i).getRect().intersects(new Rectangle(e.getX(), getY(), 1, 1)))//Rectangle size 1
                System.out.println(12);
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("entered");
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exited");
    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p/>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragged");
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Moved");
    }
}

