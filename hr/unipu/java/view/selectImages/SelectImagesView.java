package hr.unipu.java.view.selectImages;

import hr.unipu.java.model.CommonStorage;
import hr.unipu.java.controller.SelectImagesController;
import hr.unipu.java.view.compress.CompressView;
import hr.unipu.java.view.convert.ConvertView;
import hr.unipu.java.view.resize.ResizeView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SelectImagesView {
    JPanel selectedImages = new JPanel();

    JPanel nextBackPanel = new JPanel();

    JButton selectImagesBtn = new JButton();
    JButton backBtn = new JButton();
    JButton nextBtn = new JButton();
    JLabel selectImagesDescription = new JLabel();

    JLabel selectedImagesCount = new JLabel();
    JLabel selectedImagesNames = new JLabel();
    JLabel feedbackLabel = new JLabel();

    /**
     * All component UI settings
     */
    public SelectImagesView(){
        SelectImagesController selectImagesLogic = new SelectImagesController(SelectImagesView.this);

        // Main panel settings
        selectedImages.setLayout(new GridLayout(7,1,0,10));
        selectedImages.setBorder(BorderFactory.createEmptyBorder(40,100,40,100));
        selectedImages.setBackground(Color.WHITE);

        // Other component settings
        selectImagesBtn.setText("Select images");
        selectImagesBtn.setBackground(new Color(0, 128, 128));
        selectImagesBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        selectImagesBtn.setPreferredSize(new Dimension(100, 60));
        selectImagesBtn.setForeground(Color.WHITE);
        try{
            BufferedImage cameraIcon = ImageIO.read(getClass().getResource("/resources/camera.png"));
            Image cameraIconScaled = cameraIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(cameraIconScaled);
            selectImagesBtn.setIcon(icon);
            selectImagesBtn.setHorizontalAlignment(SwingConstants.CENTER);
            selectImagesBtn.setIconTextGap(30);
            selectImagesBtn.setForeground(Color.WHITE);
        }catch(IOException e){
            selectImagesBtn = new JButton("Select images");
            e.printStackTrace();
        }

        selectImagesDescription.setText("<html><p style='text-align:center'>Select photos that you wish to compress.<br/>They will be shown below.</p></html>");
        selectImagesDescription.setFont(selectImagesDescription.getFont().deriveFont(Font.PLAIN, 16.0F));
        selectImagesDescription.setHorizontalAlignment(SwingConstants.CENTER);

        selectedImagesCount.setFont(selectedImagesCount.getFont().deriveFont(Font.BOLD, 25.0F));
        selectedImagesCount.setHorizontalAlignment(SwingConstants.CENTER);
        selectedImagesCount.setBorder(BorderFactory.createEmptyBorder(-15,0,0,0));

        selectedImagesNames.setFont(selectedImagesNames.getFont().deriveFont(Font.BOLD, 16.0F));
        selectedImagesNames.setHorizontalAlignment(SwingConstants.CENTER);
        selectedImagesNames.setBorder(BorderFactory.createEmptyBorder(0,5,40,0));


        nextBackPanel.setLayout(new GridLayout(1,2,70,0));
        nextBackPanel.setBackground(Color.WHITE);
        nextBackPanel.add(backBtn);
        nextBackPanel.add(nextBtn);

        backBtn.setText("BACK");
        backBtn.setBackground(new Color(128, 128, 128));
        backBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        backBtn.setPreferredSize(new Dimension(100, 60));
        backBtn.setForeground(Color.WHITE);

        nextBtn.setText("NEXT");
        nextBtn.setBackground(new Color(0, 100, 100));
        nextBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        nextBtn.setPreferredSize(new Dimension(100, 60));
        nextBtn.setForeground(Color.WHITE);

        feedbackLabel.setFont(feedbackLabel.getFont().deriveFont(Font.PLAIN, 22.0F));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setForeground(Color.RED);

        selectedImages.add(selectImagesBtn);
        selectedImages.add(selectImagesDescription);
        selectedImages.add(selectedImagesCount);
        selectedImages.add(selectedImagesNames);
        selectedImages.add(nextBackPanel);
        selectedImages.add(feedbackLabel);
    }

    public JPanel getSelectedImages(){ return selectedImages; }
    public JButton getSelectImagesBtn() { return selectImagesBtn; }
    public JLabel getSelectImagesCount() { return selectedImagesCount; }
    public JLabel getSelectedImagesNames() { return selectedImagesNames; }
    public JLabel getFeedbackLabel() { return feedbackLabel; }

    /**
     * Resets the shown variables to default (empty).
     */
    public void resetDefault(){
        CommonStorage.selectedFiles = new File[0];
        selectedImagesNames.setText("");
        selectedImagesCount.setText("");
        feedbackLabel.setText("");
    }

    /**
     * Action listeners for SelectImagesView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel){
        nextBtn.addActionListener(e -> {
            if(CommonStorage.ancestorName.equals("compress")) {
                if(CommonStorage.selectedFiles.length == 0){
                    feedbackLabel.setText("You haven't selected any images.");
                    return;
                }
                CompressView.numPhotosSelectedLabel.setText(CommonStorage.selectedFiles.length + " photos selected");
                layout.show(panel, "compress");
            }
            else if(CommonStorage.ancestorName.equals("resize")) {
                if(CommonStorage.selectedFiles.length == 0){
                    feedbackLabel.setText("You haven't selected any images.");
                    return;
                }
                ResizeView.numPhotosSelectedLabel.setText(CommonStorage.selectedFiles.length + " photos selected");
                layout.show(panel, "resize");
            }
            else if(CommonStorage.ancestorName.equals("convert")) {
                if(CommonStorage.selectedFiles.length == 0){
                    feedbackLabel.setText("You haven't selected any images.");
                    return;
                }
                ConvertView.numPhotosSelectedLabel.setText(CommonStorage.selectedFiles.length + " photos selected");
                layout.show(panel, "convert");
            }
        });
        backBtn.addActionListener(e -> layout.show(panel, "home"));
    }

}
