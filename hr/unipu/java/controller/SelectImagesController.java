package hr.unipu.java.controller;

import hr.unipu.java.controller.common.ImageFilter;
import hr.unipu.java.view.selectImages.SelectImagesView;
import hr.unipu.java.model.CommonStorage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SelectImagesController {
    SelectImagesView GUI;

    /**
     * All logic implemented through ActionListeners.
     * @param GUI SelectImagesView panel
     */
    public SelectImagesController(SelectImagesView GUI){
        this.GUI = GUI;
        GUI.getSelectImagesBtn().addActionListener(this::browse);
    }

    /**
     * Allows the user to browse his directory to select one or more images.
     * @param event
     */
    private void browse(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new ImageFilter());
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);

        JFrame frame = new JFrame("Browse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int option = fileChooser.showOpenDialog(frame);
        if(option == JFileChooser.APPROVE_OPTION){
            CommonStorage.selectedFiles = fileChooser.getSelectedFiles();

            GUI.getSelectImagesCount().setText("Selected photos (" + CommonStorage.selectedFiles.length + "):");
            String allNames = "";
            for(int i = 0; i < CommonStorage.selectedFiles.length; i++) {
                File file = CommonStorage.selectedFiles[i];
                if(i == CommonStorage.selectedFiles.length-1) allNames += file.getName();
                else allNames += file.getName() + ", ";
            }
            allNames += "";
            GUI.getFeedbackLabel().setText("");
            GUI.getSelectedImagesNames().setText(allNames);

        }
    }
}
