package main;

import controller.LogicTecController;
import model.LogicTecModel;
import view.LogicTecView;


public class mainApp {

    public static void main(String[] args) {
        LogicTecModel theModel = new LogicTecModel();
        LogicTecView theView = new LogicTecView(theModel);
        LogicTecController controller = new LogicTecController(theModel, theView);
        //theView.generateTable();

    }
}