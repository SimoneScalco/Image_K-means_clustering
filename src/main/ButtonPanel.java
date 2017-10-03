

package main;

import algorithms.clustering.KMeansImageClustering;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import utils.ConstValues;
import dialogs.MessageDialog;
import dialogs.ProgressBarDialog;
import java.awt.Dialog;
import javax.swing.JDialog;
import utils.ImageConverter;

/**
 *
 * @author Simone
 */
public class ButtonPanel extends JPanel implements ActionListener {
    
    ViewPanel viewPanel;
    
    ImageConverter imageConverter;
    
    JButton startButton;
    
    MainPanel mp;
    
    private Timer timer;
    
    KMeansImageClustering clusterer;
    
    JDialog computingDialog;
    ProgressBarDialog pbDialog;
    
    public ButtonPanel(MainPanel mp) {
        
        timer = new Timer(100, this);
        
        // Get references from MainPanel
        this.mp = mp;
        viewPanel = ViewPanel.getInstance();
        imageConverter = ImageConverter.getInstance();
        
        // Init buttons
        startButton = new JButton("Start");
        
        // Set layouts
        this.setLayout(new FlowLayout());
        
        // Add buttons
        this.add(startButton);
        
        // Add action listeners
        startButton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Capture commands
        if(e.getSource()==startButton) {
            
            // Check if image is present
            if(!viewPanel.isImageInPlace()) {
                MessageDialog.openMessageDialog("No image to compute! Please open an image first!", mp.frame);
                return;
            }
            
            // Start the timer task
            timer.start();
            
            // Gets the values of the sliders
            int numClusters = this.mp.sliderPanel.getNumClustersSliderValue();
            int maxIterations = this.mp.sliderPanel.getMaxIterationsSliderValue();
            int epsilon = this.mp.sliderPanel.getEpsilonSliderValue();
            
            // Creates a PlanarImage from the file on disk
            PlanarImage pi=JAI.create("fileload", mp.filename);
            clusterer = new KMeansImageClustering(pi, numClusters, maxIterations, epsilon);

            // Starts the clustering process
            clusterer.start();
            ConstValues.computationInProgress = true;
            
            // Progress bar dialog
            pbDialog = new ProgressBarDialog("Computing");
            computingDialog = new JDialog(mp.frame);
            computingDialog.setTitle("Wait please");
            computingDialog.setContentPane(pbDialog);
            computingDialog.setLocationRelativeTo(mp.frame);
            computingDialog.setSize(500, 100);
            computingDialog.setLocation(ConstValues.setPanelCenterPosition(computingDialog));
            computingDialog.setResizable(false);
            computingDialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
            computingDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            computingDialog.setVisible(true);

            
        }
        else if(e.getSource()==timer) {
            
            // Get percentage for the progress bar
            int percentage = (int)(100*clusterer.getPosition()/clusterer.getSize());
            this.pbDialog.progressBar.setValue(percentage);
            
            // Checks if clusterer has finished
            if(clusterer.isFinished()) {
                
                // Stop timer and update variables
                timer.stop();
                ConstValues.computationInProgress = false;
                
                // Get output image
                RenderedImage renderedImage = clusterer.getOutput();

                // Sets the image
                viewPanel.setImage(imageConverter.convertRenderedImage(renderedImage));
                viewPanel.revalidate();
                viewPanel.repaint();
                
                // Close the dialog
                computingDialog.dispose();
                
            }
            
        }
        
    }
    
}
