

package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author Simone
 */
public class SettingsPanel extends JPanel implements ActionListener {
    
    MainPanel mp;
    JCheckBox clusterPositions;
    
    
    public SettingsPanel(MainPanel mp) {
        
        this.mp = mp;
        
        // Init components
        clusterPositions = new JCheckBox("View clusters positions");
        
        // Set layout
        this.setLayout(new FlowLayout());
        
        // Add components
        this.add(clusterPositions);
        
        // Add action listeners
        clusterPositions.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
    }
    
}
