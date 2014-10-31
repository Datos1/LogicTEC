package controller;

import main.Commons;
import model.LogicTecModel;
import view.LogicTecView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pablo on 24/10/14.
 */
public class LogicTecController implements Commons {
    private LogicTecModel theModel;
    private LogicTecView theView;

    public LogicTecController(LogicTecModel pModel, LogicTecView pView) {
        theModel = pModel;
        theView = pView;
        theView.addListener(new ViewListener());
        theModel.addListener(new ModelListener());
    }

    class ViewListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = e.getID();
            String[] command = e.getActionCommand().split("#");
            switch (id) {
                case ADD:
                    theModel.add(command);
                    break;
                case REMOVE:
                    theModel.remove(command);
                    break;
                case SET:
                    theModel.set(command);
                    break;
                case SET_INOUT:
                    theModel.setInOut(command);
                    break;
                case TABLAID:
                    theModel.crearTabla();
                    theView.PantallaTabla(theModel.getTablaVerdad());
                    break;

            }


        }
    }

    class ModelListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = e.getID();
            String[] command = e.getActionCommand().split("#");
            switch (id) {
                case SET_INOUT:
                    theView.setOut(command);
                    theView.rightCircuit();
                    break;
                case CHECKC:
                    theView.checkCircuit();
                    break;

            }

        }

    }
}
