

package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import utils.ConstValues;

/**
 *
 * @author Simone
 */
public class SetupEnvironment {
    
    JFrame frame;
    
    public SetupEnvironment(String[] args) {
        
        this.getScreenDimension();
        
        frame = new JFrame();
        frame.getContentPane().add(new MainPanel(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setTitle("KMeans clusterer");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    private void getScreenDimension() {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ConstValues.screenWidth = screenSize.getWidth();
        ConstValues.screenHeight = screenSize.getHeight();
        
    }
    
    public static void main(String[] args) throws Exception {
        
        new SetupEnvironment(args);
        
    }
    
}
