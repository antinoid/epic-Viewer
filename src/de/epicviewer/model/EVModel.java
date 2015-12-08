package de.epicviewer.model;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
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
    
    public void loadImage(File file) throws IOException {
        
        String extension = "";
        int i = file.getPath().lastIndexOf('.');
        if (i > 0) {
            extension = file.getPath().substring(i+1);
        }
        
        if ("epic".equals(extension)) {
            List<String> encodedImageStringList = Files.readAllLines(file.toPath());
            image = getDecodedImage(encodedImageStringList);
        } else {
            BufferedImage tempImage = ImageIO.read(file);
            if (tempImage.getType() != BufferedImage.TYPE_INT_RGB) {
                image = new BufferedImage(tempImage.getWidth(), tempImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                ColorConvertOp convertOp = new ColorConvertOp(null);
                convertOp.filter(tempImage, image);
            }
            System.out.println(Integer.toHexString(image.getRGB(0, 0)));
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        }
    }
    
    public void saveImage(File file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        String compressedImage = getEncodedImage();
        try {            
            Files.write(file.toPath(), compressedImage.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
    
    private String getEncodedImage() {
        int n = 1;                      // Anzahl gleicher Werte
        int value = -1;                 // aktueller Wert
        int previusValue = pixels[0];   // vorheriger Wert
        
        StringBuilder compressedImage = new StringBuilder();
        // Höhe und Breite zum Header hinzufügen
        compressedImage.append(Integer.toString(width)).append('\n');
        compressedImage.append(Integer.toString(height)).append('\n');
        
        for (int i = 1; i < width * height; i++) {
            value = pixels[i];
            // Folge unterbrochen?
            if (value != previusValue) {
                compressedImage.append(n).append('x').append(previusValue).append('\n');
                // Folge zurücksetzen
                n = 0;
            }
            // vorherigen Wert updaten
            previusValue = value;
            // Folge erhöhen
            ++n;
        }
        
        return compressedImage.toString();
    }
    
    private BufferedImage getDecodedImage(List<String> encodedImageStringList) {
        
        width = Integer.parseInt(encodedImageStringList.get(0));
        height = Integer.parseInt(encodedImageStringList.get(1));
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        
        return null;
    }
}
