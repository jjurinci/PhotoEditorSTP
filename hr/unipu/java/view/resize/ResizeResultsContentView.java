package hr.unipu.java.view.resize;

import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;

public class ResizeResultsContentView {
    JPanel resizeResultsContent = new JPanel();
    HeaderView headerView;
    ResizeResultsView resizeResultsView;

    /**
     * All UI component settings.
     */
    public ResizeResultsContentView() {
        setResizeResultsContent();
        createResizeResultsContent();
        addResizeResultsContent();
    }

    public ResizeResultsView getResizeResults() {
        return resizeResultsView;
    }
    public JPanel getResizeResultsContent() {
        return resizeResultsContent;
    }
    public void setResizeResultsContent() {
        resizeResultsContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and resizeResults views.
     */
    public void createResizeResultsContent() {
        headerView = new HeaderView();
        resizeResultsView = new ResizeResultsView();
    }

    /**
     * Adds header and resizeResults components to the parent panel.
     */
    public void addResizeResultsContent() {
        resizeResultsContent.add(headerView.getHeader(), BorderLayout.NORTH);
        resizeResultsContent.add(resizeResultsView.getResizeResults(), BorderLayout.CENTER);
    }

}
