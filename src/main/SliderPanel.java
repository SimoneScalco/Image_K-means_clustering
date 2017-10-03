

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Simone
 */
public class SliderPanel extends JPanel implements MouseMotionListener {
    
    
    // A JSlider for the number of clusters.
    private JSlider numClusters;
    
    // A JSlider for the maximum number of iterations.
    private JSlider maxIterationsSlider;
    
    // The possible values for the maximum number of iterations.
    private int[] maxIterationsValues = {2,5,10,20,50,100,200,500,1000};
    
    // A JSlider for the epsilon value.
    private JSlider epsilonSlider;
    
    // The possible values for the epsilon.
    private int[] epsilonValues = {1,5,10,50,100,200,500,1000};
    
    // A smaller font for the sliders.
    private Font labelsFont = new Font("Dialog",0,9);
    
    
    public SliderPanel(MainPanel mp) {
        
        // Set layout
        this.setLayout(new GridLayout(4, 1));
        
        // Init sliders
        // The slider for the number of clusters
        numClusters = new JSlider(2,50,8);
        Hashtable<Integer,JLabel> labels = new Hashtable<Integer,JLabel>();

        for(int label=2;label<=50;label+=8) {
            JLabel aLabel = new JLabel(""+label);
            aLabel.setFont(labelsFont);
            labels.put(new Integer(label),aLabel);
        }

        numClusters.setMajorTickSpacing(8);
        numClusters.setMinorTickSpacing(1);
        numClusters.setSnapToTicks(true);
        numClusters.setPaintTicks(true);
        numClusters.setLabelTable(labels);
        numClusters.setPaintLabels(true);
        numClusters.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Number of clusters"));
        
        // The slider for the maximum number of iterations. This slider will have
        // sort of a log scale. Real values will be from 2 to 1000.
        maxIterationsSlider = new JSlider(0,8,5);
        labels = new Hashtable<Integer,JLabel>();
        for(int label=0;label<9;label++) {
            JLabel aLabel = new JLabel(""+maxIterationsValues[label]);
            aLabel.setFont(labelsFont);
            labels.put(new Integer(label),aLabel);
        }
        
        maxIterationsSlider.setMajorTickSpacing(1);
        maxIterationsSlider.setSnapToTicks(true);
        maxIterationsSlider.setPaintTicks(true);
        maxIterationsSlider.setLabelTable(labels);
        maxIterationsSlider.setPaintLabels(true);
        maxIterationsSlider.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Max. iterations (approx.)"));
        maxIterationsSlider.addMouseMotionListener(this);
        maxIterationsSlider.setPaintLabels( true );
        maxIterationsSlider.setToolTipText("Hello");
        
        // The slider for the epsilon value. This JSlider will have
        // sort of a log scale. Real values will be from 1 to 1000.
        epsilonSlider = new JSlider(0,7,3);
        labels = new Hashtable<Integer,JLabel>();
        for(int label=0;label<8;label++) {
            JLabel aLabel = new JLabel(""+epsilonValues[label]);
            aLabel.setFont(labelsFont);
            labels.put(new Integer(label),aLabel);
        }
        
        epsilonSlider.setMajorTickSpacing(1);
        epsilonSlider.setSnapToTicks(true);
        epsilonSlider.setPaintTicks(true);
        epsilonSlider.setLabelTable(labels);
        epsilonSlider.setPaintLabels(true);
        epsilonSlider.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Epsilon"));
        
        // Adding the sliders to the interface
        this.add(numClusters);
        this.add(maxIterationsSlider);
        this.add(epsilonSlider);
        
    }
    
    
    public int getNumClustersSliderValue() {
        return this.numClusters.getValue();
    }
    
    public int getMaxIterationsSliderValue() {
        return this.maxIterationsSlider.getValue();
    }
    
    public int getEpsilonSliderValue() {
        return this.epsilonSlider.getValue();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        maxIterationsSlider.setToolTipText("" + maxIterationsSlider.getValue());
         
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        maxIterationsSlider.setToolTipText("" + maxIterationsSlider.getValue());
    }
    
}
