

package dialogs;

import java.awt.Dialog;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import utils.ConstValues;

/**
 *
 * @author Simone
 */
public class MessageDialog extends JPanel {
    
    JLabel messageLabel;
    
    public MessageDialog(String message) {
        
        messageLabel = new JLabel(message);
        this.add(messageLabel);
        
    }
    
    
    public static void openMessageDialog(String message, JFrame dialogOwner) {
        
        JDialog dialog = new JDialog(dialogOwner);
        dialog.setTitle("Message dialog");
        dialog.setContentPane(new MessageDialog(message));
        dialog.setLocationRelativeTo(dialogOwner);
        dialog.setSize(500, 100);
        dialog.setLocation(ConstValues.setPanelCenterPosition(dialog));
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setVisible(true);
        
    }
    
    public static void openErrorDialog(String error, JFrame dialogOwner) {
        
        JDialog dialog = new JDialog(dialogOwner);
        dialog.setTitle("Error dialog");
        dialog.setContentPane(new MessageDialog(error));
        dialog.setLocationRelativeTo(dialogOwner);
        dialog.setSize(500, 100);
        dialog.setLocation(ConstValues.setPanelCenterPosition(dialog));
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setVisible(true);
        
    }
}
