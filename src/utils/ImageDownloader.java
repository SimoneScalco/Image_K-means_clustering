

package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Simone
 */
public class ImageDownloader {
    
    
    public ImageDownloader() {
        
    }
    
    public static BufferedImage getImage(String imageURL, String outputFileName) {
        
        try{

            // Image paths
            String fileName = outputFileName;
            String website = imageURL;

            System.out.println("Downloading File From: " + website);
            
            // Get an input stream and open a buffer
            URL url = new URL(website);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(fileName);
            byte[] buffer = new byte[2048];

            int length = 0;
            
            // Writes the image from the outputstream to the buffer
            while ((length = inputStream.read(buffer)) != -1) {
               System.out.println("Buffer Read of length: " + length);
               outputStream.write(buffer, 0, length);
            }
            
            // Closing input and output streams
            inputStream.close();
            outputStream.close();
            
            // Opens file and reads it
            File file = new File(fileName);
            BufferedImage image = ImageIO.read(file);
            
            return image;
            
        } catch(Exception e){
           System.out.println("Exception: " + e.getMessage());
        }
        
        return null;
    }
    
    
    
    
}
