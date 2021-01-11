package hr.unipu.java.view.home;

import hr.unipu.java.view.compress.CompressContentView;
import hr.unipu.java.view.compress.CompressResultsContentView;
import hr.unipu.java.view.convert.ConvertContentView;
import hr.unipu.java.view.convert.ConvertResultsContentView;
import hr.unipu.java.view.pleaseWait.PleaseWaitContentView;
import hr.unipu.java.view.resize.ResizeContentView;
import hr.unipu.java.view.resize.ResizeResultsContentView;
import hr.unipu.java.view.selectImages.SelectImagesContentView;
import hr.unipu.java.view.settings.SettingsContentView;

import java.awt.*;
import javax.swing.*;


public class Main {
    IndexContentView indexContentView;
    CompressContentView compressContentView;
    ResizeContentView resizeContentView;
    ConvertContentView convertContentView;
    SelectImagesContentView selectedImagesContent;
    PleaseWaitContentView pleaseWaitContentView;
    CompressResultsContentView compressResultsContentView;
    ResizeResultsContentView resizeResultsContentView;
    ConvertResultsContentView convertResultsContentView;
    SettingsContentView settingsContentView;

    JFrame frame = new JFrame("Photo Editor");
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();

    public Main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        indexContentView = new IndexContentView();
        indexContentView.getIndex().setActionListeners(layout, mainPanel);

        compressContentView = new CompressContentView();
        compressContentView.getCompress().setActionListeners(layout, mainPanel);

        resizeContentView = new ResizeContentView();
        resizeContentView.getResize().setActionListeners(layout, mainPanel);

        convertContentView = new ConvertContentView();
        convertContentView.getConvert().setActionListeners(layout, mainPanel);

        selectedImagesContent = new SelectImagesContentView();
        selectedImagesContent.getSelectImages().setActionListeners(layout, mainPanel);

        pleaseWaitContentView = new PleaseWaitContentView();
        pleaseWaitContentView.getPleaseWait().setActionListeners(layout, mainPanel);

        compressResultsContentView = new CompressResultsContentView();
        compressResultsContentView.getCompressResults().setActionListeners(layout, mainPanel);

        resizeResultsContentView = new ResizeResultsContentView();
        resizeResultsContentView.getResizeResults().setActionListeners(layout, mainPanel);

        convertResultsContentView = new ConvertResultsContentView();
        convertResultsContentView.getConvertResults().setActionListeners(layout, mainPanel);

        settingsContentView = new SettingsContentView();
        settingsContentView.getSettings().setActionListeners(layout, mainPanel);

        mainPanel.setLayout(layout);
        mainPanel.add(indexContentView.getIndexContent(), "home");
        mainPanel.add(compressContentView.getCompressContent(), "compress");
        mainPanel.add(resizeContentView.getResizeContent(), "resize");
        mainPanel.add(convertContentView.getConvertContent(), "convert");
        mainPanel.add(selectedImagesContent.getSelectImagesContent(), "selectImages");
        mainPanel.add(pleaseWaitContentView.getPleaseWaitContent(), "pleaseWait");
        mainPanel.add(compressResultsContentView.getCompressResultsContent(), "compressResults");
        mainPanel.add(resizeResultsContentView.getResizeResultsContent(), "resizeResults");
        mainPanel.add(convertResultsContentView.getConvertResultsContent(), "convertResults");
        mainPanel.add(settingsContentView.getSettingsContent(), "settings");
        layout.show(mainPanel, "home");

        frame.add(mainPanel);
        frame.setSize(700,800);
        //frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Main::new);
    }
}
