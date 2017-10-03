

package utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

/**
 *
 * @author Simone
 */
public class ImageConverter {
    
    
    public static ImageConverter imageConverterInstance = null;
    
    
    
    public ImageConverter() {
        
    }
    
    
    public static ImageConverter getInstance() {
        if(imageConverterInstance==null) {
            imageConverterInstance = new ImageConverter();
        }
        return imageConverterInstance;
    }
    
    
    public BufferedImage convertRenderedImage(RenderedImage img) {
        
        // Check if it's already an instance of BufferedImage
        if (img instanceof BufferedImage) {
            return (BufferedImage)img;  
        }
        
        // Gets the color model and the dimensions
        ColorModel cm = img.getColorModel();
        int width = img.getWidth();
        int height = img.getHeight();
        
        // Create a raster for the new image
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        
        // Inserts the keys in the collection
        Hashtable properties = new Hashtable();
        String[] keys = img.getPropertyNames();
        
        if (keys!=null) {
            for (int i = 0; i < keys.length; i++) {
                properties.put(keys[i], img.getProperty(keys[i]));
            }
        }
        
        // Create a BufferedImage using the previous data
        BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
        img.copyData(raster);
        
        return result;
    }
    
    
}
