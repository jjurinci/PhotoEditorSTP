package hr.unipu.java.controller;
import hr.unipu.java.controller.common.ImagePathManipulator;
import hr.unipu.java.model.ConvertFinalResultsModel;
import hr.unipu.java.view.convert.ConvertView;
import hr.unipu.java.view.convert.ConvertResultsView;
import hr.unipu.java.view.pleaseWait.PleaseWaitView;
import hr.unipu.java.model.CommonStorage;
import hr.unipu.java.model.ConvertOptionsModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

public class ConvertController {
    ConvertView GUI;

    /**
     * All logic implemented through ActionListeners.
     * @param GUI ConvertView panel
     */
    public ConvertController(ConvertView GUI){
        this.GUI = GUI;

        GUI.getNextBtn().addActionListener(this::convertBtnEvent);
        GUI.getStoragePathBtn().addActionListener(this::browseStoragePath);
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
     * that will start converting the selected images.
     * @param event
     */
    private void convertBtnEvent(ActionEvent event){
        String resultPhotoFormat = GUI.getResultPhotoFormat().getSelectedItem().toString().toLowerCase();
        boolean deleteOriginalPhotos = GUI.getDeleteOriginalPhotosCBox().isSelected();
        String storagePath = GUI.getStoragePath().getText();

        ConvertOptionsModel convertOptionsModel = new ConvertOptionsModel(resultPhotoFormat, deleteOriginalPhotos, storagePath);
        CommonStorage.convertOptionsModel = convertOptionsModel;

        try{
            startConverting();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that converts the selected images.
     * @throws IOException
     */
    private void startConverting() throws IOException{

        //Extracts all relevant information (paths, names, formats...)
        String resultPhotoFormat = CommonStorage.convertOptionsModel.resultPhotoFormat;
        boolean deleteOriginalPhotos = CommonStorage.convertOptionsModel.deleteOriginalPhotos;
        String storagePath = CommonStorage.convertOptionsModel.storagePath;

        CommonStorage.convertOptionsModel.currentImageCount = 0;
        CommonStorage.convertFinalResults = new ConvertFinalResultsModel();
        PleaseWaitView.pleaseWaitLabel.setVisible(true);
        PleaseWaitView.nextBtn.setVisible(false);
        ConvertResultsView.skippedImages.setText("");

        for(File originalImageFile : CommonStorage.selectedFiles) {
            try {
                CommonStorage.convertOptionsModel.currentImageCount++;

                PleaseWaitView.processingLabel.setText("Converting...");
                PleaseWaitView.currentImageLabel.setText("Current: " + CommonStorage.convertOptionsModel.currentImageCount +
                        "/" + CommonStorage.selectedFiles.length + " " + originalImageFile.getName());

                String originalImagePath = originalImageFile.getAbsolutePath();
                String convertedImageName = "converted" + ImagePathManipulator.getImageName(originalImagePath);
                String convertedImageDestination = storagePath + convertedImageName;
                convertedImageDestination = ImagePathManipulator.changeFormat(convertedImageDestination, resultPhotoFormat);

                //1. Converts and saves the converted image
                BufferedImage originalImageBuffered = ImageIO.read(originalImageFile);

                OutputStream osConverted = new FileOutputStream(convertedImageDestination);
                ImageIO.write(originalImageBuffered, resultPhotoFormat, osConverted);

                //2. If necessary, deletes original image
                if (deleteOriginalPhotos) {
                    originalImageFile.delete();
                }

                osConverted.close();
                CommonStorage.convertFinalResults.successImagesCount++;

            } catch(Exception e){
                CommonStorage.convertFinalResults.addSkippedImageName(originalImageFile.getName());
                e.printStackTrace();
            }
        }

        // Updates relevant GUI's based on the compression results
        int success = CommonStorage.convertFinalResults.successImagesCount;
        int skipped = CommonStorage.convertFinalResults.skippedImagesCount;
        ConvertResultsView.resultsDescription.setText(success + " photos converted, " + skipped + " photos skipped.");
        if(skipped > 0){
            String result = "";
            for(String name : CommonStorage.convertFinalResults.skippedImagesNames)
                result += name + "\n";
            ConvertResultsView.skippedImages.setText("Skipped images: \n" + result);
        }
        PleaseWaitView.pleaseWaitLabel.setVisible(false);
        PleaseWaitView.nextBtn.setVisible(true);
        PleaseWaitView.processingLabel.setText("Done.");
    }
}
