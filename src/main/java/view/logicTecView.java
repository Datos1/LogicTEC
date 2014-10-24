package view;

import main.Commons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Created by pablo on 15/10/14.
 */
public class LogicTecView extends JFrame implements Commons, MouseListener, ActionListener {
    JMenuBar menuBar = new JMenuBar();
    WorkSpacePanel panel;
    int ANCHO = (int) getDefaultToolkit().getScreenSize().getWidth() * 2 / 3;
    int ALTO = (int) getDefaultToolkit().getScreenSize().getHeight() * 2 / 3;

    public LogicTecView() {
        super();
        setTitle(VENTANA_NOMBRE);
        setSize(ANCHO, ALTO + BORDER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        panel = new WorkSpacePanel(ANCHO, ALTO);
        this.add(panel);
        JMenu menuFile = new JMenu(MENU_FILE);
        menuBar.add(menuFile);
        menuFile.add(createMenuItem(SAVE));
        menuFile.add(createMenuItem(OPEN));
        menuFile.add(createMenuItem(CHECK));
        menuFile.add(createMenuItem(ABOUT));
        JMenu menuNew = new JMenu(MENU_NEW);
        menuBar.add(menuNew);
        setJMenuBar(menuBar);
        menuNew.add(createMenuItem(AND));
        menuNew.add(createMenuItem(NAND));
        menuNew.add(createMenuItem(NOR));
        menuNew.add(createMenuItem(OR));
        menuNew.add(createMenuItem(XOR));
        menuNew.add(createMenuItem(XNOR));
        menuNew.add(createMenuItem(NOT));
        menuNew.add(createMenuItem(CUSTOM));
        addMouseListener(this);

        this.setVisible(true);
    }

    /**
     * Creates an item an assigns listener
     *
     * @param text
     * @return menuitem made
     */
    private JMenuItem createMenuItem(String text) {
        JMenuItem tmp = new JMenuItem(text);
        tmp.addActionListener(this);
        return tmp;
    }


    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(AND)) panel.addAnd();
        else if (cmd.equals(OR)) panel.addOr();
        else if (cmd.equals(XOR)) panel.addXor();
    }

}
