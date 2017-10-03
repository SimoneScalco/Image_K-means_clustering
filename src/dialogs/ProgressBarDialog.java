

package dialogs;

import java.awt.Dialog;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Simone
 */
public class ProgressBarDialog extends JPanel {
    
    
    JDialog dialog;
    
    public JProgressBar progressBar;
    
    public ProgressBarDialog(String message) {

        // Init components
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        
        // Add components
        this.add(progressBar);
        progressBar.setMaximum(100); // maximum value is 100 percent.
        progressBar.setPreferredSize(new Dimension (500, 50));
        
    }
    
    public void openProgressBarDialog(String message, JFrame dialogOwner) {
        
        dialog = new JDialog(dialogOwner);
        dialog.setTitle("Wait please");
        dialog.setContentPane(new ProgressBarDialog(message));
        dialog.setLocationRelativeTo(dialogOwner);
        dialog.setSize(500, 100);
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setVisible(true);
        
    }
    
}
