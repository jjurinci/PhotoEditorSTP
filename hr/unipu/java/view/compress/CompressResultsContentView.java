package hr.unipu.java.view.compress;

import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;

public class CompressResultsContentView {
    JPanel compressResultsContent = new JPanel();
    HeaderView headerView;
    CompressResultsView compressResultsView;

    /**
     * All component UI settings.
     */
    public CompressResultsContentView() {
        setCompressResultsContent();
        createCompressResultsContent();
        addCompressResultsContent();
    }

    public JPanel getCompressResultsContent() {
        return compressResultsContent;
    }
    public CompressResultsView getCompressResults() {
        return compressResultsView;
    }
    public void setCompressResultsContent() {
        compressResultsContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and compress views.
     */
    public void createCompressResultsContent() {
        headerView = new HeaderView();
        compressResultsView = new CompressResultsView();
    }

    /**
     * Adds header and compress components to the parent panel.
     */
    public void addCompressResultsContent() {
        compressResultsContent.add(headerView.getHeader(), BorderLayout.NORTH);
        compressResultsContent.add(compressResultsView.getCompressResults(), BorderLayout.CENTER);
    }
}
