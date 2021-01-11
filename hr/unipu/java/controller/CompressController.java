package hr.unipu.java.controller;

import hr.unipu.java.controller.common.ImageBlobManipulator;
import hr.unipu.java.controller.common.ImagePathManipulator;
import hr.unipu.java.view.compress.CompressView;
import hr.unipu.java.view.compress.CompressResultsView;
import hr.unipu.java.view.pleaseWait.PleaseWaitView;
import hr.unipu.java.model.CommonStorage;
import hr.unipu.java.model.CompressFinalResultsModel;
import hr.unipu.java.model.CompressOptionsModel;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.Iterator;

public class CompressController {
    CompressView GUI;

    /**
     * All logic implemented through ActionListeners.
     * @param GUI CompressView panel
     */
    public CompressController(CompressView GUI){
        this.GUI = GUI;

        GUI.getMaintainResolutionCBox().addActionListener(this::maintainResolutionEvent);
        GUI.getCompressBtn().addActionListener(this::compressBtnEvent);
        GUI.getBrowseStoragePath().addActionListener(this::browseStoragePath);
    }

    /**
     * Makes width and height textfields visible based on the
     * maintain resolution checkbox value.
     * @param event
     */
    private void maintainResolutionEvent(ActionEvent event){
        if(!GUI.getMaintainResolutionCBox().isSelected()) GUI.getHeightWidthPanel().setVisible(true);
        else GUI.getHeightWidthPanel().setVisible(false);
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
     * that will start compressing the selected images.
     * @param event
     */
    private void compressBtnEvent(ActionEvent event){
        String qualityStr = GUI.getQualityField().getText();
        String storagePath = GUI.getStoragePath().getText();
        boolean maintainResolution = GUI.getMaintainResolutionCBox().isSelected();
        boolean deleteOriginalPhotosCBox = GUI.getDeleteOriginalPhotosCBox().isSelected();
        float quality = stringPercentageToFloat(qualityStr);
        int newWidth = 0, newHeight = 0;

        if(!maintainResolution) {
            newWidth = Integer.parseInt(GUI.getWidthField().getText());
            newHeight = Integer.parseInt(GUI.getHeightField().getText());
        }

        CommonStorage.compressOptions = new CompressOptionsModel(quality, storagePath,
                                                            maintainResolution, deleteOriginalPhotosCBox,
                                                            newWidth,newHeight);
        try {
            startCompressing();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that compresses the selected images.
     * @throws IOException
     */
    public void startCompressing() throws IOException {

        // More compact names
        String storagePath = CommonStorage.compressOptions.storagePath;
        boolean maintainResolution = CommonStorage.compressOptions.maintainResolution;
        boolean deleteOriginalPhotos = CommonStorage.compressOptions.deleteOriginalPhotos;
        float quality = CommonStorage.compressOptions.quality;
        int newWidth = CommonStorage.compressOptions.newWidth;
        int newHeight = CommonStorage.compressOptions.newHeight;

        CommonStorage.compressOptions.currentImageCount = 0;
        CommonStorage.compressFinalResultsModel = new CompressFinalResultsModel();
        PleaseWaitView.pleaseWaitLabel.setVisible(true);
        PleaseWaitView.nextBtn.setVisible(false);
        CompressResultsView.skippedImages.setText("");
        for(File originalImageFile : CommonStorage.selectedFiles){
            try {
                CommonStorage.compressOptions.currentImageCount++;
                PleaseWaitView.processingLabel.setText("Compressing...");
                PleaseWaitView.currentImageLabel.setText("Current: " + CommonStorage.compressOptions.currentImageCount +
                        "/" + CommonStorage.selectedFiles.length + " " + originalImageFile.getName());

                //1. Extracts all relevant information (paths, names, formats...)
                String originalImagePath = originalImageFile.getAbsolutePath();
                String originalImageFormat = ImagePathManipulator.getImageFormat(originalImagePath);
                BufferedImage originalImageBuffered = ImageIO.read(originalImageFile);

                String compressedImageName = "compressed" + ImagePathManipulator.getImageName(originalImagePath);
                String compressedImageDestination = storagePath + compressedImageName;

                //2. Prepares compression objects
                File compressedImageFile = new File(compressedImageDestination);
                OutputStream os = new FileOutputStream(compressedImageFile);
                Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(originalImageFormat);
                ImageWriter writer = (ImageWriter) writers.next();
                ImageOutputStream ios = ImageIO.createImageOutputStream(os);
                writer.setOutput(ios);

                //3. Sets up compression options
                ImageWriteParam param = writer.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);

                //4. Saves compressed image. If necessary image is first resized.
                if (!maintainResolution) {
                    Image newResizedImage = originalImageBuffered.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    BufferedImage newResizedImageBuffered = ImageBlobManipulator.convertToBufferedImage(newResizedImage);
                    writer.write(null, new IIOImage(newResizedImageBuffered, null, null), param);
                } else writer.write(null, new IIOImage(originalImageBuffered, null, null), param);

                //5. If necessary, deletes original image.
                if (deleteOriginalPhotos) originalImageFile.delete();

                os.close();
                ios.close();
                writer.dispose();

                CommonStorage.compressFinalResultsModel.successImagesCount++;

            }catch(Exception e){
                CommonStorage.compressFinalResultsModel.addSkippedImageName(originalImageFile.getName());
                e.printStackTrace();
            }
        }

        // Updates relevant GUI's based on the compression results
        int success = CommonStorage.compressFinalResultsModel.successImagesCount;
        int skipped = CommonStorage.compressFinalResultsModel.skippedImagesCount;
        CompressResultsView.resultsDescription.setText(success + " photos compressed, " + skipped + " photos skipped.");
        if(skipped > 0){
            String result = "";
            for(String name : CommonStorage.compressFinalResultsModel.skippedImagesNames)
                result += name + "\n";
            CompressResultsView.skippedImages.setText("Skipped images: \n" + result);
        }
        PleaseWaitView.pleaseWaitLabel.setVisible(false);
        PleaseWaitView.nextBtn.setVisible(true);
        PleaseWaitView.processingLabel.setText("Done.");
    }

    private boolean needsFormatConversion(String originalImageFormat, String selectedImageFormat){
        return !selectedImageFormat.equalsIgnoreCase("origin") &&
                !originalImageFormat.equalsIgnoreCase(selectedImageFormat);
    }

    private float stringPercentageToFloat(String percentage){
        int percentInt = Integer.valueOf(percentage.substring(0, percentage.length()-1));
        return (float) percentInt/100;
    }
}
