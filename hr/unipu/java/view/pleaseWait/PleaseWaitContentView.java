package hr.unipu.java.view.pleaseWait;
import hr.unipu.java.view.home.HeaderView;
import javax.swing.*;
import java.awt.*;

public class PleaseWaitContentView {
    JPanel pleaseWaitContent = new JPanel();
    HeaderView headerView;
    PleaseWaitView pleaseWaitView;

    public PleaseWaitContentView() {
        setPleaseWaitContent();
        createPleaseWaitContent();
        addPleaseWaitContent();
    }

    public PleaseWaitView getPleaseWait() {
        return pleaseWaitView;
    }
    public JPanel getPleaseWaitContent() {
        return pleaseWaitContent;
    }
    public void setPleaseWaitContent() {
        pleaseWaitContent.setLayout(new BorderLayout());
    }

    /**
     * Creates the header and please wait view.
     */
    public void createPleaseWaitContent() {
        headerView = new HeaderView();
        pleaseWaitView = new PleaseWaitView();
    }

    /**
     * Adds the header and please wait view to the parent panel.
     */
    public void addPleaseWaitContent() {
        pleaseWaitContent.add(headerView.getHeader(), BorderLayout.NORTH);
        pleaseWaitContent.add(pleaseWaitView.getPleaseWait(), BorderLayout.CENTER);
    }
}
