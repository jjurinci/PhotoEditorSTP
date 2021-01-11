package hr.unipu.java.view.convert;

import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConvertContentView {
    JPanel convertContent = new JPanel();
    HeaderView headerView;
    ConvertView convertView;

    /**
     * All UI settings.
     */
    public ConvertContentView() {
        setConvertContent();
        createConvertContent();
        addConvertContent();

        convertContent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                convertView.getStoragePath().setText(System.getProperty("user.home"));
            }
        });
    }

    public void setConvertContent() {
        convertContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and convert settings view.
     */
    public void createConvertContent() {
        headerView = new HeaderView();
        convertView = new ConvertView();
    }

    /**
     * Adds the header and convert settings view to the parent panel.
     */
    public void addConvertContent() {
        convertContent.add(headerView.getHeader(), BorderLayout.NORTH);
        convertContent.add(convertView.getConvert(), BorderLayout.CENTER);
    }

    public ConvertView getConvert() {
        return convertView;
    }

    public JPanel getConvertContent() {
        return convertContent;
    }
}
