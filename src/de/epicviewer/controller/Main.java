package de.epicviewer.controller;

import de.epicviewer.model.EVModel;
import de.epicviewer.view.EVView;
import java.awt.EventQueue;

/**
 *
 * @author da
 */
public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            //EVModel model = new EVModel();
            //EVView view = new EVView();
            EVController controller = new EVController();
            controller.showView();
        });        
    }
}
