

package main;

/**
 *
 * @author Simone
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ViewPanel extends JPanel {

    public static ViewPanel viewPanelInstance = null;
    
    // The two images
    private Image image;
    private BufferedImage bufImage;
    
    // Used to know if the image is in place or not
    private boolean imageOpened;
    
    
    private ViewPanel() {
        
    }
    
    
    public static ViewPanel getInstance() {
        if(viewPanelInstance==null) {
            viewPanelInstance = new ViewPanel();
        }
        return viewPanelInstance;
    }
    
    
    public void reloadImage() {
        
        this.revalidate();
        this.repaint();
        
    }
    
    public void setImage(BufferedImage bufImage) {
        
        // Copy the input image into the two local objects
        this.bufImage = bufImage;
        this.image = bufImage;
        
        // Reload the interface
        this.imageOpened = true;
        this.reloadImage();
    }
    
    public BufferedImage getImage() {
        return bufImage;
    }
    
    public boolean isImageInPlace() {
        if(imageOpened) {
            return true;
        }
        return false;
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(image!=null) {
            Dimension d = this.getSize();
            g.drawImage(image, 0, 0, d.width, d.height, this);
            
        }
    }
    
    
    
    
    
}