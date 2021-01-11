package hr.unipu.java.view.resize;

import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeContentView {
    JPanel resizeContent = new JPanel();
    HeaderView headerView;
    ResizeView resizeView;

    /**
     * All component UI settings
     */
    public ResizeContentView() {
        setResizeContent();
        createResizeContent();
        addResizeContent();

        resizeContent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                resizeView.getStoragePath().setText(System.getProperty("user.home"));
            }
        });
    }

    public ResizeView getResize() {
        return resizeView;
    }
    public JPanel getResizeContent() {
        return resizeContent;
    }
    public void setResizeContent() {
        resizeContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and resize views.
     */
    public void createResizeContent() {
        headerView = new HeaderView();
        resizeView = new ResizeView();
    }

    /**
     * Adds the header and resize views to the parent panel.
     */
    public void addResizeContent() {
        resizeContent.add(headerView.getHeader(), BorderLayout.NORTH);
        resizeContent.add(resizeView.getResize(), BorderLayout.CENTER);
    }
}
