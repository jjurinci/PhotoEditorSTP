package hr.unipu.java.view.convert;

import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;

public class ConvertResultsContentView {
    JPanel convertResultsContent = new JPanel();
    HeaderView headerView;
    ConvertResultsView convertResultsView;

    /**
     * All UI settings.
     */
    public ConvertResultsContentView() {
        setConvertResultsContent();
        createConvertResultsContent();
        addConvertResultsContent();
    }

    public void setConvertResultsContent() {
        convertResultsContent.setLayout(new BorderLayout());
    }

    /**
     * Creates header and convert results window view.
     */
    public void createConvertResultsContent() {
        headerView = new HeaderView();
        convertResultsView = new ConvertResultsView();
    }

    /**
     * Adds header and convert results window view to the parent panel.
     */
    public void addConvertResultsContent() {
        convertResultsContent.add(headerView.getHeader(), BorderLayout.NORTH);
        convertResultsContent.add(convertResultsView.getConvertResults(), BorderLayout.CENTER);
    }

    public ConvertResultsView getConvertResults() {
        return convertResultsView;
    }

    public JPanel getConvertResultsContent() {
        return convertResultsContent;
    }
}
