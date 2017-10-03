

package utils;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Simone
 */
public class ConstValues {
    
    public static double screenWidth;
    public static double screenHeight;
    
    public static boolean computationInProgress;
    
    public static Point setPanelCenterPosition(JDialog dialog) {
        
        Dimension screenSize = new Dimension((int)screenWidth, (int)screenHeight);
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (dialog.getWidth() / 2), middle.y - (dialog.getHeight() / 2));
        
        return newLocation;
    }
    
}
