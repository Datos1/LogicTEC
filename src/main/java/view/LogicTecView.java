package view;

import main.Commons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Created by pablo on 15/10/14.
 */
public class LogicTecView extends JFrame implements Commons, ActionListener {
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
        for (int i = 0; i < COMPONENTS.getLength(); i++) { //creates add menu with components
            menuNew.add(createMenuItem(COMPONENTS.get(i)));
        }

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
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        for (int i = 0; i < COMPONENTS.getLength(); i++) {
            if (e.getActionCommand().equals(COMPONENTS.get(i)))
                panel.createComponent(COMPONENTS.get(i));
        }
    }

    /**
     * sets panel listener
     *
     * @param listener
     */
    public void addListener(ActionListener listener) {
        panel.addListener(listener);
    }
}
