package view;

import main.Commons;
import model.LogicTecModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Created by pablo on 15/10/14.
 */
public class LogicTecView extends JFrame implements Commons, ActionListener {
    private LogicTecModel model;
    JMenuBar menuBar = new JMenuBar();
    JPanel statusPanel = new JPanel();
    JLabel statusLabel = new JLabel(CHECK);
    WorkSpacePanel panel;
    int ANCHO = (int) getDefaultToolkit().getScreenSize().getWidth() * 2 / 3;
    int ALTO = (int) getDefaultToolkit().getScreenSize().getHeight() * 2 / 3;
    private ActionListener listener;

    public LogicTecView(LogicTecModel modelo) {
        super();
        setTitle(VENTANA_NOMBRE);
        setSize(ANCHO, ALTO + BORDER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        model=modelo;
    }

    private void init() {
        panel = new WorkSpacePanel(ANCHO, ALTO);
        this.add(panel);
        JMenu menuFile = new JMenu(MENU_FILE);
        menuBar.add(menuFile);
        menuFile.add(createMenuItem(SAVE));
        menuFile.add(createMenuItem(OPEN));
        menuFile.add(createMenuItem(CHECK));
        menuFile.add(createMenuItem(THEME));
        menuFile.add(createMenuItem(TABLA));
        menuFile.add(createMenuItem(ABOUT));
        JMenu menuNew = new JMenu(MENU_NEW);
        menuBar.add(menuNew);
        setJMenuBar(menuBar);
        for (int i = 0; i < COMPONENTS.getLength(); i++) { //creates add menu with components
            menuNew.add(createMenuItem(COMPONENTS.get(i)));
        }
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);


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
        if (e.getActionCommand().equals(CHECK))
            listener.actionPerformed(new ActionEvent(this, CHECKC, ""));
        else if(e.getActionCommand().equals(ABOUT))
            crearVentanaAcerca();
        else if(e.getActionCommand().equals(TABLA))
            generateTable();
        else if (e.getActionCommand().equals(THEME))
            panel.changeTheme();

    }

    public void generateTable(){
        model.crearTabla();
    }
    public void crearVentanaAcerca(){
        JFrame acercaFrame = new JFrame();
        JPanel acercaPanel = new JPanel();
        JLabel acercaLabel=new JLabel();
        acercaPanel.setLayout(new BoxLayout(acercaPanel, BoxLayout.Y_AXIS));
        acercaFrame.add(acercaPanel);
        acercaLabel.setText("LogicTec v1.0");
        acercaPanel.add(acercaLabel);
        acercaFrame.pack();
        acercaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        acercaFrame.setVisible(true);
    }
    /**
     * sets panel listener
     *
     * @param listener
     */
    public void addListener(ActionListener listener) {
        this.listener = listener;
        panel.addListener(listener);
    }

    public void setOut(String[] command) {
        panel.setOut(Integer.parseInt(command[0]), Boolean.parseBoolean(command[1]));
    }

    public void checkCircuit() {
        statusLabel.setText(CHECK);
    }

    public void rightCircuit() {
        statusLabel.setText(RIGHT);
    }
}
