package hr.unipu.java.view.home;

import hr.unipu.java.controller.common.ImageBlobManipulator;
import hr.unipu.java.model.CommonStorage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IndexView {
    JPanel index = new JPanel();
    JPanel body = new JPanel();
    JPanel footer = new JPanel();
    JButton compressBtn, resizeBtn, convertBtn, resultsFolderBtn, buyCoffeeBtn, settingsBtn;

    public IndexView() {
        setIndex();
        createButtons();
        setButtonColors();
        setButtonFonts();
        setButtonSizes();
        addButtons();
    }

    public JPanel getIndex() {
        return index;
    }
    public JButton getCompressBtn() {
        return compressBtn;
    }
    public JButton getResizeBtn() {
        return resizeBtn;
    }
    public JButton getConvertBtn() { return convertBtn; }
    public JButton getBuyCoffeeBtn(){ return buyCoffeeBtn; }
    public JButton getSettingsBtn() { return settingsBtn; }

    /**
     * All panel settings.
     */
    public void setIndex() {
        index.setBackground(Color.WHITE);
        index.setLayout(new BorderLayout());
        body.setBackground(Color.WHITE);
        body.setLayout(new GridLayout(4, 1, 50, 20));
        body.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        footer.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        footer.setLayout(new GridLayout(1,2, 50,0));
        footer.setBackground(Color.WHITE);
    }

    /**
     * All button background colors.
     */
    public void setButtonColors() {
        compressBtn.setBackground(new Color(0, 128, 128));
        resizeBtn.setBackground(new Color(0, 128, 128));
        convertBtn.setBackground(new Color(0, 128, 128));
        resultsFolderBtn.setBackground(new Color(0, 128, 128));
    }

    /**
     * All button fonts.
     */
    public void setButtonFonts() {
        compressBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        resizeBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        convertBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        resultsFolderBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        buyCoffeeBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        settingsBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
    }

    /**
     * ALl button sizes.
     */
    public void setButtonSizes() {
        compressBtn.setPreferredSize(new Dimension(100, 60));
        resizeBtn.setPreferredSize(new Dimension(100, 60));
        convertBtn.setPreferredSize(new Dimension(100, 60));
        resultsFolderBtn.setPreferredSize(new Dimension(100, 60));
        buyCoffeeBtn.setPreferredSize(new Dimension(100, 60));
        settingsBtn.setPreferredSize(new Dimension(100, 60));
    }

    public void createButtons() {
        try{
            BufferedImage compressIcon = ImageIO.read(getClass().getResource("/resources/compress.png"));
            Image compressIconScaled = compressIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            compressBtn = new JButton("Compress photos", new ImageIcon(compressIconScaled));
            compressBtn.setHorizontalAlignment(SwingConstants.CENTER);
            compressBtn.setIconTextGap(30);
            compressBtn.setForeground(Color.WHITE);
        }catch(IOException e){
            compressBtn = new JButton("<html><span style='color: white;'>Compress photos (jpeg)</span></html>\"");
            e.printStackTrace();
        }
        try{
            BufferedImage resizeIcon = ImageIO.read(getClass().getResource("/resources/resize.png"));
            Image resizeIconScaled = resizeIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            resizeBtn = new JButton("Resize photos", new ImageIcon(resizeIconScaled));
            resizeBtn.setHorizontalAlignment(SwingConstants.CENTER);
            resizeBtn.setIconTextGap(30);
            resizeBtn.setForeground(Color.WHITE);
        }catch(IOException e){
            resizeBtn = new JButton("<html><span style='color: white;'>Resize photos</span></html>\"");
            e.printStackTrace();
        }
        try{
            BufferedImage convertIcon = ImageIO.read(getClass().getResource("/resources/convert.png"));
            Image convertIconScaled = convertIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            convertBtn = new JButton("Convert photos", new ImageIcon(convertIconScaled));
            convertBtn.setHorizontalAlignment(SwingConstants.CENTER);
            convertBtn.setIconTextGap(30);
            convertBtn.setForeground(Color.WHITE);
        }catch(IOException e){
            convertBtn = new JButton("<html><span style='color: white;'>Convert photos</span></html>\"");
            e.printStackTrace();
        }
        try{
            BufferedImage resultsIcon = ImageIO.read(getClass().getResource("/resources/result.png"));
            Image resultsIconScaled = resultsIcon.getScaledInstance(45, 45, BufferedImage.SCALE_SMOOTH);
            resultsFolderBtn = new JButton("Results folder", new ImageIcon(resultsIconScaled));
            resultsFolderBtn.setHorizontalAlignment(SwingConstants.CENTER);
            resultsFolderBtn.setIconTextGap(30);
            resultsFolderBtn.setForeground(Color.WHITE);
        }catch(IOException e){
            resultsFolderBtn = new JButton("<html><span style='color: white;'>Results folder</span></html>\"");
            e.printStackTrace();
        }
        try{
            BufferedImage coffeeIcon = ImageIO.read(getClass().getResource("/resources/coffee.png"));
            Image coffeeIconScaled = coffeeIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            buyCoffeeBtn = new JButton("Buy us a coffee", new ImageIcon(coffeeIconScaled));
            buyCoffeeBtn.setHorizontalAlignment(SwingConstants.CENTER);
            buyCoffeeBtn.setIconTextGap(30);
            buyCoffeeBtn.setForeground(Color.WHITE);
            buyCoffeeBtn.setBackground(new Color(169,169,169));
            buyCoffeeBtn.setBorder(BorderFactory.createEmptyBorder());
        }catch(IOException e){
            buyCoffeeBtn = new JButton("<html><span style='color: white;'>Buy as a coffee</span></html>\"");
            e.printStackTrace();
        }
        try{
            BufferedImage settingsIcon = ImageIO.read(getClass().getResource("/resources/settings.png"));
            Image settingsIconScaled = settingsIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            settingsBtn = new JButton("Settings", new ImageIcon(settingsIconScaled));
            settingsBtn.setHorizontalAlignment(SwingConstants.CENTER);
            settingsBtn.setIconTextGap(30);
            settingsBtn.setForeground(Color.WHITE);
            settingsBtn.setOpaque(true);
            settingsBtn.setBackground(new Color(128,128,128));
            settingsBtn.setBorder(BorderFactory.createEmptyBorder());
        }catch(IOException e){
            settingsBtn = new JButton("<html><span style='color: white;'>Settings</span></html>\"");
            e.printStackTrace();
        }
    }

    /**
     * Adds buttons to panels
     */
    public void addButtons() {
        body.add(compressBtn);
        body.add(resizeBtn);
        body.add(convertBtn);
        body.add(resultsFolderBtn);

        footer.add(buyCoffeeBtn, BorderLayout.CENTER);
        footer.add(settingsBtn, BorderLayout.LINE_END);

        index.add(body, BorderLayout.CENTER);
        index.add(footer, BorderLayout.PAGE_END);
    }

    /**
     * Action listeners for IndexView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel) {
        getCompressBtn().addActionListener(e -> {
            CommonStorage.ancestorName = "compress";
            layout.show(panel, "selectImages");
        });

        getResizeBtn().addActionListener(e -> {
            CommonStorage.ancestorName = "resize";
            layout.show(panel, "selectImages");
        });

        getConvertBtn().addActionListener(e -> {
            CommonStorage.ancestorName = "convert";
            layout.show(panel, "selectImages");
        });
        resultsFolderBtn.addActionListener(e -> {
            try{
                Desktop.getDesktop().open(new File(System.getProperty("user.home") + "/"));
            }catch(IOException io){
                io.printStackTrace();
            }
        });
        getBuyCoffeeBtn().addActionListener(e -> layout.show(panel, ""));
        getSettingsBtn().addActionListener(e -> layout.show(panel, "settings"));
    }
}
