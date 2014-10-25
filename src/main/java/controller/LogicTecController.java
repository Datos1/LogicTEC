package controller;

import model.LogicTecModel;
import view.LogicTecView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pablo on 24/10/14.
 */
public class LogicTecController {
    private LogicTecModel theModel;
    private LogicTecView theView;

    public LogicTecController(LogicTecModel pModel, LogicTecView pView) {
        theModel = pModel;
        theView = pView;
        theView.addListener(new ViewListener());
    }

    class ViewListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
