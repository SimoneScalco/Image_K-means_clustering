

package main;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.ImageDownloader;

/**
 *
 * @author Simone
 */
public class ImageDownloadPanel extends JPanel implements ActionListener {
    
    MainPanel mp;
    JFrame downloadFrame;
    
    JPanel linkPanel;
    JPanel settingsPanel;
    
    //JTextField linkField;
    PlaceholderTextField linkField;
    
    JButton downloadButton;
    
    JCheckBox scaledImageCheckBox;
    
    
    public ImageDownloadPanel(MainPanel mp, JFrame downloadFrame) {
        
        linkField = new PlaceholderTextField("");
        linkField.setColumns(35);
        linkField.setPlaceholder("Paste the image URL here...");
        final Font f = linkField.getFont();
        linkField.setFont(new Font(f.getName(), f.getStyle(), 16));
        
        this.mp = mp;
        this.downloadFrame = downloadFrame;
        
        // Init components
        linkPanel = new JPanel();
        settingsPanel = new JPanel();
        downloadButton = new JButton("Download");
        scaledImageCheckBox = new JCheckBox("Get scaled image");
        
        // Set layouts
        this.setLayout(new GridLayout(2,1));
        linkPanel.setLayout(new FlowLayout());
        settingsPanel.setLayout(new FlowLayout());
        
        // Add components to panel
        this.add(linkPanel);
        this.add(settingsPanel);
        //linkPanel.add(linkField);
        linkPanel.add(linkField);
        linkPanel.add(downloadButton);
        settingsPanel.add(scaledImageCheckBox);
        
        // Action listener
        downloadButton.addActionListener(this);
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Download")) {
            
            String imageName = linkField.getText().substring(linkField.getText().lastIndexOf("/")+1, linkField.getText().length());
            System.out.println("Image name: " + imageName);
            
            // Loads the image in the panel
            this.mp.imagePanel.setImage(ImageDownloader.getImage(linkField.getText(), imageName));
            this.mp.imagePanel.reloadImage();
            
            // Saves the filename and the path
            this.mp.filename = imageName;
            this.mp.absolutePath = imageName;
            
            // Closes the download frame and returns to the main panel
            this.downloadFrame.dispose();
            
        }
        
    }
    
}
