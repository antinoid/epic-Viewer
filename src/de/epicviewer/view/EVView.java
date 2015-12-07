package de.epicviewer.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author da
 */
public class EVView extends JFrame {

    private JPanel panel;
    private JLabel imageLabel;
    private ActionListener listener;
    
    public EVView(ActionListener listener) {
        super("epic Viewer");
        this.listener = listener;
        initGUI();
    }
    
    public void setImage(ImageIcon image) {
        imageLabel = new JLabel(image);
        panel.add(imageLabel);
        repaint();
    }
    
    private void initGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        createMenubar();
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 300));
        
        add(panel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void createMenubar() {
        
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("FILE");
        
        JMenuItem newItem = new JMenuItem("new");
        newItem.addActionListener(listener);
        fileMenu.add(newItem);
        
        JMenuItem loadItem = new JMenuItem("load");
        loadItem.addActionListener(listener);
        fileMenu.add(loadItem);
        
        JMenuItem saveItem = new JMenuItem("save");
        saveItem.addActionListener(listener);
        fileMenu.add(saveItem);
        
        menubar.add(fileMenu);
        setJMenuBar(menubar);
    }
}
