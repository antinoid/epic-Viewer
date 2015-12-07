package de.epicviewer.controller;

import de.epicviewer.model.EVModel;
import de.epicviewer.view.EVView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author da
 */
public class EVController implements ActionListener {

    private final EVModel model;
    private final EVView view;
    
    public EVController() {
        model = new EVModel();
        model.createRandomImage();
        
        view = new EVView(this);
        view.setImage(model.getImage());
    }
    
    public void showView() {
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(actionCommand == "new") {
            model.createRandomImage();
            view.setImage(model.getImage());
        }
    }
}
