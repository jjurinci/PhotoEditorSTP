package hr.unipu.java.view.convert;

import hr.unipu.java.model.CommonStorage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConvertResultsView {
    // Main panel
    JPanel convertResults = new JPanel();

    // Next back panel
    JPanel nextBackPanel = new JPanel();

    JLabel resultsHeader;
    JButton resultsFolderBtn, homeBtn;
    public static JLabel resultsDescription, skippedImages;

    /**
     * All UI settings
     */
    public ConvertResultsView(){
        // Main panel settings
        convertResults.setLayout(new GridLayout(5,1,0,0));
        convertResults.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        convertResults.setBackground(Color.WHITE);

        // Next back panel settings
        nextBackPanel.setLayout(new GridLayout(1,2,25,0));
        nextBackPanel.setBackground(Color.WHITE);

        // Other component settings
        resultsHeader = new JLabel("RESULTS");
        resultsHeader.setFont(resultsHeader.getFont().deriveFont(Font.BOLD, 65.0F));
        resultsHeader.setForeground(new Color(0, 128, 128));
        resultsHeader.setHorizontalAlignment(SwingConstants.CENTER);

        resultsDescription = new JLabel();
        resultsDescription.setFont(resultsDescription.getFont().deriveFont(Font.PLAIN, 25.0F));
        resultsDescription.setHorizontalAlignment(SwingConstants.CENTER);

        skippedImages = new JLabel();
        skippedImages.setFont(skippedImages.getFont().deriveFont(Font.BOLD, 20.0F));
        skippedImages.setHorizontalAlignment(SwingConstants.CENTER);

        try{
            BufferedImage resultsIcon = ImageIO.read(getClass().getResource("/resources/result.png"));
            Image resultsIconScaled = resultsIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            resultsFolderBtn = new JButton("RESULTS FOLDER", new ImageIcon(resultsIconScaled));
            resultsFolderBtn.setIconTextGap(10);
        }catch(IOException e){
            resultsFolderBtn = new JButton("<html><span style='color: white;'>RESULTS FOLDER</span></html>\"");
            e.printStackTrace();
        }

        resultsFolderBtn.setHorizontalAlignment(SwingConstants.CENTER);
        resultsFolderBtn.setBackground(new Color(0, 128, 128));
        resultsFolderBtn.setFont(resultsFolderBtn.getFont().deriveFont(Font.BOLD, 16.0F));
        resultsFolderBtn.setForeground(Color.WHITE);

        homeBtn = new JButton("HOME");
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        homeBtn.setBackground(new Color(0, 128, 128));
        homeBtn.setFont(homeBtn.getFont().deriveFont(Font.BOLD, 25.0F));
        homeBtn.setForeground(Color.WHITE);

        nextBackPanel.add(resultsFolderBtn);
        nextBackPanel.add(homeBtn);

        convertResults.add(resultsHeader);
        convertResults.add(resultsDescription);
        convertResults.add(skippedImages);
        convertResults.add(nextBackPanel);
    }

    public JPanel getConvertResults(){
        return convertResults;
    }

    /**
     * Action listeners for ConvertResultsView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel){
        homeBtn.addActionListener(e -> layout.show(panel, "home"));
        resultsFolderBtn.addActionListener(e -> {
            try{
                Desktop.getDesktop().open(new File(CommonStorage.convertOptionsModel.storagePath));
            }catch(IOException io){
                io.printStackTrace();
            }
        });
    }
}
