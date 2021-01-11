package hr.unipu.java.view.home;

import javax.swing.*;
import java.awt.*;

public class IndexContentView {
    JPanel indexContent = new JPanel();
    HeaderView headerView;
    IndexView indexView;

    /**
     * All component UI settings.
     */
    public IndexContentView() {
        setIndexContent();
        createindexContent();
        addindexContent();
    }

    public JPanel getIndexContent() {
        return indexContent;
    }
    public IndexView getIndex() {
        return indexView;
    }

    public void setIndexContent() {
        indexContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and index view.
     */
    public void createindexContent() {
        headerView = new HeaderView();
        indexView = new IndexView();
    }

    /**
     * Adds the header and index view to the parent panel.
     */
    public void addindexContent() {
        indexContent.add(headerView.getHeader(), BorderLayout.NORTH);
        indexContent.add(indexView.getIndex(), BorderLayout.CENTER);
    }
}
