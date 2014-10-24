package view;

import data.List;
import main.Commons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pablo on 24/10/14.
 */
public class WorkSpacePanel extends JPanel implements Commons {
    private List<ComponentLabel> components = new List<ComponentLabel>();
    private Dimension size;
    private Rectangle dropArea;

    public WorkSpacePanel(int w, int h) {
        super();
        this.size = new Dimension(w, h);
        this.setSize(size);
        this.setBackground(new Color(218, 218, 218));
        dropArea = new Rectangle(0, size.height - IMAGESIZE, IMAGESIZE, IMAGESIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawRect((int) dropArea.getX(), (int) dropArea.getY(),
                (int) dropArea.getWidth(), (int) dropArea.getHeight());// draw drop area

    }

    /**
     * Creates compuerta or if possible
     */
    public void addXor() {

    }

    /**
     * Creates compuerta or if possible
     */
    public void addOr() {

    }

    /**
     * Creates compuerta and if possible
     */
    public void addAnd() {
        if (freeSpace()) ;

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

    public JLabel getCompuerta(String PATH) {
        return new JLabel(new ImageIcon(PATH));
    }
}
