package view;

import main.Commons;
import model.VFTable;

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
    JMenuBar menuBar = new JMenuBar();
    JPanel statusPanel = new JPanel();
    JLabel statusLabel = new JLabel(CHECK);
    WorkSpacePanel panel;
    int ANCHO = (int) getDefaultToolkit().getScreenSize().getWidth() * 2 / 3;
    int ALTO = (int) getDefaultToolkit().getScreenSize().getHeight() * 2 / 3;
    private ActionListener listener;

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
        //else if (e.getActionCommand().equals(SAVE))
        }

    public void PantallaTabla(VFTable tabla){
        JFrame TablaFrame = new JFrame();
        JPanel TablaPanel = new JPanel();
        JLabel TablaLabel=new JLabel();
        Dimension dimension=new Dimension(220,150);
        TablaFrame.setMinimumSize(dimension);
        TablaPanel.setLayout(null);
        TablaPanel.setBounds(0, 0, 100, 100);
        TablaFrame.add(TablaPanel);
        TablaLabel.setText("Tabla de verdad");
        TablaLabel.setBounds(0,0,100,15);
        TablaPanel.add(TablaLabel);
        TablaFrame.pack();
        TablaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        TablaFrame.setVisible(true);
        int x=0;
        while(x<tabla.getNumeroEntradas()){
            JLabel Entrada=new JLabel();
            Entrada.setBounds(52*x+5,20,50,15);
            Entrada.setText("input " + String.valueOf(x+1));
            TablaPanel.add(Entrada);

            for(int j=0;j<tabla.getListaEntradas().get(0).getLength();j++){
                JLabel Bool=new JLabel();
                Bool.setBounds(52*x+5,(j+1)*15+20,50,15);
                Bool.setText(String.valueOf(tabla.getListaEntradas().get(x).get(j)));
                TablaPanel.add(Bool);
            }
            x++;
        }
        int i=0;
        while(i<tabla.getNumeroSalidas()){
            JLabel Salida=new JLabel();
            Salida.setBounds(52*x+5,20,50,15);
            Salida.setText("output " + String.valueOf(i+1));
            TablaPanel.add(Salida);
            for(int j=0;j<tabla.getListaEntradas().get(0).getLength();j++){
                JLabel Bool=new JLabel();
                Bool.setBounds(52*x+5,(j+1)*15+20,50,15);
                Bool.setText(String.valueOf(tabla.getListaSalidas().get(i).get(j)));
                TablaPanel.add(Bool);
            }
            x++;
            i++;
        }

    }
    public void generateTable(){
        listener.actionPerformed(new ActionEvent(this, TABLAID, ""));
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
