

package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.ConstValues;

/**
 *
 * @author Simone
 */
public class MainPanel extends JPanel {
    
    JFrame frame;
    JFrame settingsFrame;
    
    MenuBar mainPanelMenuBar;
    
    ButtonPanel buttonPanel;
    ViewPanel imagePanel;
    SliderPanel sliderPanel;
    
    JPanel southPanel;
    
    String filename;
    String absolutePath;
    
    
    public MainPanel(JFrame frame) {
        
        this.frame = frame;
        
        // Init panels
        imagePanel = ViewPanel.getInstance();
        buttonPanel = new ButtonPanel(this);
        sliderPanel = new SliderPanel(this);
        southPanel = new JPanel();
        
        // Menu bars
        mainPanelMenuBar = new MenuBar(this);
        
        // Set layouts
        this.setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());
        
        // Adds the panels to the layout
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(sliderPanel, BorderLayout.EAST);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(mainPanelMenuBar, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        
        
        
    }
    
    
    public void openImageDownloadFrame() {
        
        JFrame downloadFrame = new JFrame();
        downloadFrame.setContentPane(new ImageDownloadPanel(this, downloadFrame));
        downloadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        downloadFrame.setSize(600, 100);
        downloadFrame.setResizable(false);
        downloadFrame.setTitle("Download image");
        //downloadFrame.setLocationRelativeTo(null);
        downloadFrame.setLocation((int)ConstValues.screenWidth/2 - 300, (int)ConstValues.screenHeight/2 - 100);
        downloadFrame.setVisible(true);
        
    }
    
    public void openSettingsFrame() {
        
        settingsFrame = new JFrame();
        settingsFrame.setContentPane(new SettingsPanel(this));
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(600, 100);
        settingsFrame.setResizable(false);
        settingsFrame.setTitle("Settings");
        //settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setLocation((int)ConstValues.screenWidth/2 , (int)ConstValues.screenHeight/2 -  100);
        settingsFrame.setVisible(true);
        
    }
    
    
    
}
