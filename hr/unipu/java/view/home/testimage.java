package hr.unipu.java.view.home;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class testimage extends ImageIcon {
    BufferedImage i;
    public testimage() {
        BufferedImage i = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);


        Graphics2D g2d = (Graphics2D) i.getGraphics();
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawImage(getImage(), 0, 0, 32, 32, null);
        setImage(i);
    }

    public int getIconHeight() {
        return 32;
    }

    public int getIconWidth() {
        return 32;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawImage(getImage(), x, y, c);
    }
}
