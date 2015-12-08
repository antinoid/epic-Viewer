package de.epicviewer.controller;

import de.epicviewer.model.EVModel;
import de.epicviewer.view.EVView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        
        if("new".equals(actionCommand)) {                   // new Command
            model.createRandomImage();
            view.setImage(model.getImage());
        } 
        else if ("open".equals(actionCommand))              // open Command
        {
            File file = view.getFile(true);
            if (file != null) {
                try {
                    model.loadImage(file);
                    view.setImage(model.getImage());
                } catch (Exception ex) { 
                    System.out.println(ex.getMessage());
                }
            }
        } 
        else if ("save".equals(actionCommand))              // save Command
        {
            File file = view.getFile(false);
            if (file != null) {
                try {
                    model.saveImage(file);
                } catch (Exception ex) {
                    System.out.println("couldn't save file: " + ex.getMessage());
                }
            }
        }
    }
}
