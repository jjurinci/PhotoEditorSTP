package hr.unipu.java.controller;

import hr.unipu.java.controller.common.ImageBlobManipulator;
import hr.unipu.java.controller.common.ImagePathManipulator;
import hr.unipu.java.view.pleaseWait.PleaseWaitView;
import hr.unipu.java.view.resize.ResizeView;
import hr.unipu.java.view.resize.ResizeResultsView;
import hr.unipu.java.model.CommonStorage;
import hr.unipu.java.model.ResizeFinalResultsModel;
import hr.unipu.java.model.ResizeOptionsModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class ResizeController {
    ResizeView GUI;
    public static final String PRESET_CUSTOM = "custom";
    public static final String PRESET_1920x1080 = "1920x1080";
    public static final String PRESET_1600x900 = "1600x900";
    public static final String PRESET_1280x720 = "1280x720";
    public static final String PRESET_800x600 = "800x600";

    /**
     * All logic implemented through ActionListeners.
     * @param GUI_ ResizeView panel
     */
    public ResizeController(ResizeView GUI_){
        this.GUI = GUI_;
        GUI.getBrowseStoragePath().addActionListener(this::browseStoragePath);
        GUI.getNextBtn().addActionListener(this::resizeBtnEvent);
    }

    /**
     * Allows the user to browse his directory to select one or more images.
     * @param event
     */
    private void browseStoragePath(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Browse storage path");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        JFrame frame = new JFrame("Browse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            GUI.getStoragePath().setText(fileChooser.getSelectedFile() + FileSystems.getDefault().getSeparator());
        }
    }

    /**
     * Extracts all the relevant data from components and calls a method
     * that will start resizing the selected images.
     * @param event
     */
    private void resizeBtnEvent(ActionEvent event){
        boolean isPixelPanelActive = GUI.getPixelPanel().isVisible();
        String storagePath = GUI.getStoragePath().getText();
        boolean deleteOriginalPhotos = GUI.getDeleteOriginalPhotosCBox().isSelected();
        String sizePreset;
        float percentage = 0f;
        int width = 0, height = 0;

        if(isPixelPanelActive){
            sizePreset = GUI.getSizePresets().getSelectedItem().toString().toLowerCase();
            if(sizePreset.equals(ResizeController.PRESET_CUSTOM)){
                width = Integer.parseInt(GUI.getWidthField().getText());
                height = Integer.parseInt(GUI.getHeightField().getText());
            }
            else if(sizePreset.equals(ResizeController.PRESET_1920x1080)) {width = 1920; height = 1080;}
            else if(sizePreset.equals(ResizeController.PRESET_1600x900)) {width = 1600; height = 900;}
            else if(sizePreset.equals(ResizeController.PRESET_1280x720)) {width = 1280; height = 720;}
            else if(sizePreset.equals(ResizeController.PRESET_800x600)) {width = 800; height = 600;}
        }
        else percentage = stringPercentageToFloat(GUI.getPercentageField().getText());


        ResizeOptionsModel resizeOptionsModel = new ResizeOptionsModel(isPixelPanelActive, width, height,
                                                        deleteOriginalPhotos, storagePath, percentage);
        CommonStorage.resizeOptionsModel = resizeOptionsModel;

        try {
            startResizing();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that resizes the selected images.
     * @throws IOException
     */
    private void startResizing() throws IOException{
        boolean isPixelPanelActive = CommonStorage.resizeOptionsModel.isPixelPanelActive;
        int newWidth = CommonStorage.resizeOptionsModel.width;
        int newHeight = CommonStorage.resizeOptionsModel.height;
        float percentage = CommonStorage.resizeOptionsModel.percentage;
        boolean deleteOriginalPhotos = CommonStorage.resizeOptionsModel.deleteOriginalPhotos;
        String storagePath = CommonStorage.resizeOptionsModel.storagePath;

        CommonStorage.resizeOptionsModel.currentImageCount = 0;
        CommonStorage.resizeFinalResultsModel = new ResizeFinalResultsModel();
        PleaseWaitView.pleaseWaitLabel.setVisible(true);
        PleaseWaitView.nextBtn.setVisible(false);
        ResizeResultsView.skippedImages.setText("");
        for(File originalImageFile : CommonStorage.selectedFiles) {
            try {
                CommonStorage.resizeOptionsModel.currentImageCount++;
                PleaseWaitView.processingLabel.setText("Resizing...");
                PleaseWaitView.currentImageLabel.setText("Current: " + CommonStorage.resizeOptionsModel.currentImageCount +
                        "/" + CommonStorage.selectedFiles.length + " " + originalImageFile.getName());


                String originalImagePath = originalImageFile.getAbsolutePath();
                String originalImageFormat = ImagePathManipulator.getImageFormat(originalImagePath);
                String resizedImageName = "resized" + ImagePathManipulator.getImageName(originalImagePath);
                String resizedImageDestination = storagePath + resizedImageName;

                BufferedImage originalImageBuffered = ImageIO.read(originalImageFile);

                if(!isPixelPanelActive){
                    newWidth = (int) (originalImageBuffered.getWidth() * percentage);
                    newHeight = (int) (originalImageBuffered.getHeight() * percentage);
                }

                //1. Resizes and saves the image
                Image newResizedImage = originalImageBuffered.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                BufferedImage newResizedImageBuffered = ImageBlobManipulator.convertToBufferedImage(newResizedImage);
                OutputStream os = new FileOutputStream(resizedImageDestination);
                ImageIO.write(newResizedImageBuffered, originalImageFormat, os);

                //2. If necessary, deletes original image.
                if (deleteOriginalPhotos) {
                    originalImageFile.delete();
                }

                os.close();
                CommonStorage.resizeFinalResultsModel.successImagesCount++;
            }catch(Exception e){
                CommonStorage.resizeFinalResultsModel.addSkippedImageName(originalImageFile.getName());
                e.printStackTrace();
            }
        }

        int success = CommonStorage.resizeFinalResultsModel.successImagesCount;
        int skipped = CommonStorage.resizeFinalResultsModel.skippedImagesCount;
        ResizeResultsView.resultsDescription.setText(success + " photos resized, " + skipped + " photos skipped.");
        if(skipped > 0){
            String result = "";
            for(String name : CommonStorage.resizeFinalResultsModel.skippedImagesNames)
                result += name + "\n";
            ResizeResultsView.skippedImages.setText("Skipped images: \n" + result);
        }
        PleaseWaitView.pleaseWaitLabel.setVisible(false);
        PleaseWaitView.nextBtn.setVisible(true);
        PleaseWaitView.processingLabel.setText("Done.");
    }

    private float stringPercentageToFloat(String percentage){
        int percentInt = Integer.parseInt(percentage.substring(0, percentage.length()-1));
        return (float) percentInt/100;
    }
}
