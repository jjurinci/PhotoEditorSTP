package hr.unipu.java.view.selectImages;

import hr.unipu.java.model.CommonStorage;
import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SelectImagesContentView {
    JPanel selectImagesContent = new JPanel();
    HeaderView headerView;
    SelectImagesView selectImagesView;

    /**
     * All component UI settings.
     */
    public SelectImagesContentView(){
        setSelectedImagesContent();
        createSelectedImagesContent();
        addSelectedImagesContent();

        /**
         * Triggers every time selectImagesContent panel is shown.
         * It updates the SelectImages panel's description based on if user previously
         * clicked Compress, Resize or Convert button.
         */
        selectImagesContent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                selectImagesView.resetDefault();
                if(CommonStorage.ancestorName.equals("compress"))
                    selectImagesView.selectImagesDescription.setText("<html><p style='text-align:center'>Select photos that you wish to compress.<br/>They will be shown below.</p></html>");
                else if(CommonStorage.ancestorName.equals("resize"))
                    selectImagesView.selectImagesDescription.setText("<html><p style='text-align:center'>Select photos that you wish to resize.<br/>They will be shown below.</p></html>");
                else if(CommonStorage.ancestorName.equals("convert"))
                    selectImagesView.selectImagesDescription.setText("<html><p style='text-align:center'>Select photos that you wish to convert.<br/>They will be shown below.</p></html>");
            }
        });
    }

    public SelectImagesView getSelectImages(){ return selectImagesView; }
    public JPanel getSelectImagesContent(){ return selectImagesContent; }
    public void setSelectedImagesContent(){
        selectImagesContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and select images view.
     */
    public void createSelectedImagesContent(){
        headerView = new HeaderView();
        selectImagesView = new SelectImagesView();
    }

    /**
     * Adds the header and select images view to the parent panel.
     */
    public void addSelectedImagesContent(){
        selectImagesContent.add(headerView.getHeader(), BorderLayout.NORTH);
        selectImagesContent.add(selectImagesView.getSelectedImages(), BorderLayout.CENTER);
    }
}
