package view;

import main.Commons;

import javax.swing.*;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Created by pablo on 15/10/14.
 */
public class LogicTecView extends JFrame implements Commons {
    JMenuBar menuBar = new JMenuBar();

    public LogicTecView() {
        super();
        setTitle(VENTANA_NOMBRE);
        setSize((int) getDefaultToolkit().getScreenSize().getWidth() * 2 / 3
                , (int) getDefaultToolkit().getScreenSize().getHeight() * 2 / 3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        JMenu newMenu = new JMenu(MENU_NEW);
        menuBar.add(newMenu);
        setJMenuBar(menuBar);
        JMenuItem and = new JMenuItem(AND);
        newMenu.add(and);
        JMenuItem nand = new JMenuItem(NAND);
        newMenu.add(nand);
        JMenuItem or = new JMenuItem(OR);
        newMenu.add(or);
        JMenuItem nor = new JMenuItem(NOR);
        newMenu.add(nor);
        JMenuItem not = new JMenuItem(NOT);
        newMenu.add(not);
        JMenuItem xor = new JMenuItem(XOR);
        newMenu.add(xor);
        JMenuItem xnor = new JMenuItem(XNOR);
        newMenu.add(xnor);
        JMenuItem xml = new JMenuItem(XML);
        newMenu.add(xml);


        this.setVisible(true);
    }
}
