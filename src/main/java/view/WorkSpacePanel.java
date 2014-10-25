package view;

import controller.LogicTecController;
import data.List;
import main.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by pablo on 24/10/14.
 */
public class WorkSpacePanel extends JPanel implements Commons, MouseListener {
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
            listener.actionPerformed(new ActionEvent(this, id, text));
            String path = getPath(text);
            components.append(new ComponentImage(path, id, dropArea));
            repaint();
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

    }
}

