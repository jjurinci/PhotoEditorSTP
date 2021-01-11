package hr.unipu.java.view.compress;

import hr.unipu.java.model.CommonStorage;
import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CompressContentView {
    JPanel compressContent = new JPanel();
    HeaderView headerView;
    CompressView compressView;

    /**
     * All component UI settings.
     */
    public CompressContentView() {
        setCompressContent();
        createCompressContent();
        addCompressContent();

        compressContent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                compressView.getStoragePath().setText(System.getProperty("user.home"));
            }
        });
    }

    public JPanel getCompressContent() {
        return compressContent;
    }
    public CompressView getCompress() {
        return compressView;
    }

    public void setCompressContent() {
        compressContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and compress views.
     */
    public void createCompressContent() {
        headerView = new HeaderView();
        compressView = new CompressView();
    }

    /**
     * Adds header and compress components to the parent panel.
     */
    public void addCompressContent() {
        compressContent.add(headerView.getHeader(), BorderLayout.NORTH);
        compressContent.add(compressView.getCompress(), BorderLayout.CENTER);
    }
}
