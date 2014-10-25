package view;

import javax.swing.*;

/**
 * Created by pablo on 24/10/14.
 */
public class Options extends JFrame {
    public Options() {
        JSpinner inputs;
        JSpinner outputs;
        JButton aceptar;
        JFrame options = new JFrame();
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.add(new JLabel("  " + "  "));
        optionsPanel.add(new JSeparator());
        optionsPanel.add(new JLabel("Inputs:"));
        optionsPanel.add(inputs = new JSpinner());
        optionsPanel.add(new JLabel("Outputs:"));
        optionsPanel.add(outputs = new JSpinner());
        optionsPanel.add(aceptar = new JButton("Aceptar"));
        options.add(optionsPanel);
        options.pack();
        options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        options.setVisible(true);
    }
}
