package de.epicviewer.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author da
 */
public class EVModel {

    private static final Random RNG = new Random();
    
    private int width;
    private int height;
    
    private int[] pixels;    
    private BufferedImage image;
    
    public EVModel() {
        width = 400;
        height = 300;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }
    
    public ImageIcon getImage() {
        return new ImageIcon(image);
    }
    
    public void createRandomImage() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = RNG.nextInt(0xFFFFFF);
            }
        }
    }
}
