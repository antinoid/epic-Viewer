package de.epicviewer.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
        imageLabel.setIcon(image);
        repaint();
    }
    
    public File getFile(boolean forOpen) {
        JFileChooser fc = new JFileChooser();
        
        if (forOpen) {
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                return fc.getSelectedFile();
            }
        } else {
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                return fc.getSelectedFile();
            }
        }
        return null;
    }
    
    private void initGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        createMenubar();
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 300));
        
        imageLabel = new JLabel();
        panel.add(imageLabel);
        
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
        
        JMenuItem loadItem = new JMenuItem("open");
        loadItem.addActionListener(listener);
        fileMenu.add(loadItem);
        
        JMenuItem saveItem = new JMenuItem("save");
        saveItem.addActionListener(listener);
        fileMenu.add(saveItem);
        
        menubar.add(fileMenu);
        setJMenuBar(menubar);
    }
}
