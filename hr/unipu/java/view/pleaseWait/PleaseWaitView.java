package hr.unipu.java.view.pleaseWait;

import hr.unipu.java.model.CommonStorage;

import javax.swing.*;
import java.awt.*;

public class PleaseWaitView {
    JPanel pleaseWait = new JPanel();
    public static JLabel pleaseWaitLabel;
    public static JButton nextBtn;
    public static JLabel processingLabel;
    public static JLabel currentImageLabel = new JLabel();

    /**
     * All component UI settings.
     */
    public PleaseWaitView(){
        // Main panel settings
        pleaseWait.setLayout(new GridLayout(4, 1, 0, 0));
        pleaseWait.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        pleaseWait.setBackground(Color.WHITE);

        // Other components settings
        pleaseWaitLabel = new JLabel("Please wait");
        pleaseWaitLabel.setFont(pleaseWaitLabel.getFont().deriveFont(Font.PLAIN, 75.0F));
        pleaseWaitLabel.setForeground(new Color(0, 128, 128));
        pleaseWaitLabel.setHorizontalAlignment(SwingConstants.CENTER);

        processingLabel = new JLabel();
        processingLabel.setFont(pleaseWaitLabel.getFont().deriveFont(Font.BOLD, 55.0F));
        processingLabel.setForeground(new Color(0, 128, 128));
        processingLabel.setHorizontalAlignment(SwingConstants.CENTER);


        currentImageLabel.setFont(currentImageLabel.getFont().deriveFont(Font.PLAIN, 18.0F));
        currentImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nextBtn = new JButton("NEXT");
        nextBtn.setBackground(new Color(0, 100, 100));
        nextBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        nextBtn.setPreferredSize(new Dimension(100, 60));
        nextBtn.setForeground(Color.WHITE);

        pleaseWait.add(pleaseWaitLabel);
        pleaseWait.add(processingLabel);
        pleaseWait.add(currentImageLabel);
        pleaseWait.add(nextBtn);
    }

    public JPanel getPleaseWait(){
        return pleaseWait;
    }

    /**
     * Action listeners for PleaseWaitView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel){
        nextBtn.addActionListener(e -> {
            if(CommonStorage.ancestorName.equals("compress")) layout.show(panel, "compressResults");
            else if(CommonStorage.ancestorName.equals("resize")) layout.show(panel, "resizeResults");
            else if(CommonStorage.ancestorName.equals("convert")) layout.show(panel, "convertResults");
        });

    }
}
