

package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.ImageSaver;
import dialogs.MessageDialog;

/**
 *
 * @author Simone
 */
public class MenuBar extends JMenuBar implements ActionListener {
    
    MainPanel mp;
    ViewPanel viewPanel;
    
    JMenu fileMenu;
    JMenu editMenu;
    
    JMenuItem exitMenuItem;
    JMenuItem downloadMenuItem;
    JMenuItem openMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem saveAsMenuItem;
    JMenuItem settingsMenuItem;
    
    ImageIcon exitIcon;
    ImageIcon downloadIcon;
    ImageIcon openIcon;
    ImageIcon saveIcon;
    ImageIcon saveAsIcon;
    ImageIcon settingsIcon;
    
    JFrame frame;
    
    public MenuBar(MainPanel mp) {
        
        this.mp = mp;
        viewPanel = ViewPanel.getInstance();
        
        // Init menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_F);
        
        // Add buttons in the submenu
        addOpenButton();
        addDownloadButton();
        addSaveButton();
        addSaveAsButton();
        addExitButton();
        addSettingsButton();
        
        this.add(fileMenu);
        this.add(editMenu);
    }
    
    private void addExitButton() {

        // "Exit" menu item
        exitIcon = new ImageIcon("exit.png");
        this.exitMenuItem = new JMenuItem("Exit", exitIcon);
        this.exitMenuItem.setMnemonic(KeyEvent.VK_E);
        this.exitMenuItem.setToolTipText("Exit application");
        this.exitMenuItem.addActionListener(this);

        fileMenu.add(this.exitMenuItem);
        
    }
    
    
    private void addDownloadButton() {
        
        // "Download" menu item
        downloadIcon = new ImageIcon("download.png");
        this.downloadMenuItem = new JMenuItem("Download image", downloadIcon);
        this.downloadMenuItem.setMnemonic(KeyEvent.VK_E);
        this.downloadMenuItem.setToolTipText("Download an image from the web");
        this.downloadMenuItem.addActionListener(this);

        fileMenu.add(this.downloadMenuItem);
        
    }
    
    private void addOpenButton() {
        
        // "Download" menu item
        openIcon = new ImageIcon("open.png");
        this.openMenuItem = new JMenuItem("Open image", openIcon);
        this.openMenuItem.setMnemonic(KeyEvent.VK_E);
        this.openMenuItem.setToolTipText("Load an image from disk");
        this.openMenuItem.addActionListener(this);

        fileMenu.add(this.openMenuItem);
        
    }
    
    private void addSaveButton() {
        
        // "Save" menu item
        saveIcon = new ImageIcon("save.png");
        this.saveMenuItem = new JMenuItem("Save", saveIcon);
        this.saveMenuItem.setMnemonic(KeyEvent.VK_E);
        this.saveMenuItem.setToolTipText("Save an image in the disk");
        this.saveMenuItem.addActionListener(this);

        fileMenu.add(this.saveMenuItem);
        
    }
    
    private void addSaveAsButton() {
        
        // "Save" menu item
        saveAsIcon = new ImageIcon("saveas.png");
        this.saveAsMenuItem = new JMenuItem("Save as", saveAsIcon);
        this.saveAsMenuItem.setMnemonic(KeyEvent.VK_E);
        this.saveAsMenuItem.setToolTipText("Save an image in the disk");
        this.saveAsMenuItem.addActionListener(this);

        fileMenu.add(this.saveAsMenuItem);
        
    }
    
    private void addSettingsButton() {
        
        // "Settings" menu item
        settingsIcon = new ImageIcon("settings.png");
        this.settingsMenuItem = new JMenuItem("Settings", settingsIcon);
        this.settingsMenuItem.setMnemonic(KeyEvent.VK_E);
        this.settingsMenuItem.setToolTipText("Open settings panel");
        this.settingsMenuItem.addActionListener(this);

        editMenu.add(this.settingsMenuItem);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==exitMenuItem) {
            System.exit(0);
        }
        
        else if(e.getActionCommand().equals("Download image")) {
            mp.openImageDownloadFrame();
        }
        else if(e.getSource()==openMenuItem) {
            
            // Creates a FileFilter (only images)
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            
            // Choose a file from disk
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(filter);
            
            // Catches open button
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {

                try {

                    // Opens a file from disk
                    File fileToOpen = chooser.getSelectedFile();

                    // Get file information
                    mp.filename = fileToOpen.getAbsolutePath();
                    mp.absolutePath = fileToOpen.getAbsolutePath();
                    System.out.println("Opening file... " + mp.absolutePath);

                    // Reads the file and sticks it to the viewer
                    mp.imagePanel.setImage(ImageIO.read(fileToOpen));
                    mp.imagePanel.reloadImage();
                    
                } catch (IOException ex) {
                    
                    System.out.println("Error");
                    
                    // Show an error
                    MessageDialog.openErrorDialog("An error occured while opening the image", mp.frame);
                }
            }
            
        }
        else if(e.getSource()==saveMenuItem) {
            
            if(!viewPanel.isImageInPlace()) {
                MessageDialog.openMessageDialog("No image to save!", mp.frame);
                return;
            }
            
            // Saves the image
            ImageSaver.saveImage(this.mp.imagePanel.getImage(), this.mp.filename, this.mp.absolutePath);
            
        }
        else if(e.getSource()==saveAsMenuItem) {
            
            if(!viewPanel.isImageInPlace()) {
                MessageDialog.openMessageDialog("No image to save!", mp.frame);
                return;
            }
            
            // Opens a file chooser for saving the file
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
            int approve = chooser.showSaveDialog(frame);
            
            // Check if the approve button was clicked
            if (approve == JFileChooser.APPROVE_OPTION) {
                
                // Get directory and name of file
                File outputDir = chooser.getCurrentDirectory();
                File fileToSave = chooser.getSelectedFile();
                
                ImageSaver.saveImage(this.mp.imagePanel.getImage(), fileToSave.getName(), outputDir.getAbsolutePath() + "\\" + fileToSave.getName());
                
                this.mp.filename = fileToSave.getName();
                this.mp.absolutePath = outputDir.getAbsolutePath() + "\\" + fileToSave.getName();
            }
            
        }
        else if(e.getSource()==settingsMenuItem) {
            
            mp.openSettingsFrame();
            
        }
        
    }
    
}
