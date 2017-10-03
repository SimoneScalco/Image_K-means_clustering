

package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Simone
 */
public class ImageSaver {
    
    
    public static void saveImage(BufferedImage imageToSave, String filename, String absolutePath) {
        
        try {
            
            File fileToSave = new File(absolutePath);
            ImageIO.write(imageToSave, getExtension(filename), fileToSave);
            
            
        } catch (IOException ex) {
            System.out.println("Error while saving image");
        }
        
    }
    
    private static String getExtension(String filename) {
        
        return filename.substring(filename.lastIndexOf(".")+1, filename.length());
        
    }
    
}
