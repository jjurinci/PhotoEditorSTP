package hr.unipu.java.view.home;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeaderView {
    JPanel header = new JPanel();
    JLabel title, slogan, logo;
    BufferedImage logoBuffered;

    /**
     * All component UI settings.
     */
    public HeaderView() {
        setHeader();
        createLabels();
        setLabelFonts();
        addLabels();
    }

    public JPanel getHeader() {
        return header;
    }

    /**
     * Sets header settings.
     */
    public void setHeader() {
        header.setBackground(new Color(0,139,139));
        header.setLayout(new GridLayout(3, 1, 0, 0));
        header.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
    }

    /**
     * Sets label font settings.
     */
    public void setLabelFonts() {
        title.setFont(header.getFont().deriveFont(Font.BOLD, 45.0F));
        slogan.setFont(slogan.getFont().deriveFont(Font.PLAIN, 22.0F));
    }

    /**
     * Loads the logo image and sets header components' border settings.
     */
    public void createLabels(){
        title = new JLabel("<html><span style='color: white;'>PhotoEditor</span></html>", SwingConstants.CENTER);
        slogan = new JLabel("<html><span style='color: white;'>Free, simple & easy to use editor</span></html>", SwingConstants.CENTER);
        try{
            logoBuffered = ImageIO.read(getClass().getResource("/resources/logo.png"));
            Image logoImage = logoBuffered.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(logoImage));

            logo.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
            title.setBorder(BorderFactory.createEmptyBorder(0,0,-20,0));
            slogan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds components to the parent panel.
     */
    public void addLabels() {
        header.add(logo);
        header.add(title);
        header.add(slogan);
    }
}
