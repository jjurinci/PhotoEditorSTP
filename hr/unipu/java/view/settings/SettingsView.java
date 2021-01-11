package hr.unipu.java.view.settings;

import javax.swing.*;
import java.awt.*;
import java.nio.file.FileSystems;

public class SettingsView {
    JPanel settings = new JPanel();
    JButton backBtn;

    JLabel storageCompressLabel, storageResizeLabel, storageConvertLabel;
    JTextField storageCompressField, storageResizeField, storageConvertField;

    /**
     * All components' UI settings.
     */
    public SettingsView(){
        // Main panel settings
        settings.setLayout(new GridLayout(7,1,0,20));
        settings.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        settings.setBackground(Color.WHITE);

        // Other components settings
        storageCompressLabel = new JLabel("Default storage for compressed photos");
        storageCompressLabel.setFont(storageCompressLabel.getFont().deriveFont(Font.BOLD, 22.0F));
        storageCompressLabel.setForeground(new Color(0, 128, 128));

        storageResizeLabel = new JLabel("Default storage for resized photos");
        storageResizeLabel.setFont(storageResizeLabel.getFont().deriveFont(Font.BOLD, 22.0F));
        storageResizeLabel.setForeground(new Color(0, 128, 128));

        storageConvertLabel = new JLabel("Default storage for converted photos");
        storageConvertLabel.setFont(storageConvertLabel.getFont().deriveFont(Font.BOLD, 22.0F));
        storageConvertLabel.setForeground(new Color(0, 128, 128));

        storageCompressField = new JTextField(System.getProperty("user.home") + FileSystems.getDefault().getSeparator());
        storageCompressField.setFont(storageCompressLabel.getFont().deriveFont(Font.PLAIN, 18.0F));
        storageCompressField.setHorizontalAlignment(SwingConstants.CENTER);
        storageCompressField.setForeground(Color.DARK_GRAY);

        storageResizeField = new JTextField(System.getProperty("user.home") + FileSystems.getDefault().getSeparator());
        storageResizeField.setFont(storageResizeField.getFont().deriveFont(Font.PLAIN, 18.0F));
        storageResizeField.setHorizontalAlignment(SwingConstants.CENTER);
        storageResizeField.setForeground(Color.DARK_GRAY);

        storageConvertField = new JTextField(System.getProperty("user.home") + FileSystems.getDefault().getSeparator());
        storageConvertField.setFont(storageConvertField.getFont().deriveFont(Font.PLAIN, 18.0F));
        storageConvertField.setHorizontalAlignment(SwingConstants.CENTER);
        storageConvertField.setForeground(Color.DARK_GRAY);

        storageCompressField.setEditable(false);
        storageResizeField.setEditable(false);
        storageConvertField.setEditable(false);

        backBtn = new JButton("BACK");
        backBtn.setBackground(new Color(128, 128, 128));
        backBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        backBtn.setPreferredSize(new Dimension(100, 60));
        backBtn.setForeground(Color.WHITE);

        settings.add(storageCompressLabel);
        settings.add(storageCompressField);
        settings.add(storageResizeLabel);
        settings.add(storageResizeField);
        settings.add(storageConvertLabel);
        settings.add(storageConvertField);
        settings.add(backBtn);
    }

    public JPanel getSettings(){ return settings; }

    /**
     * Action listeners for SettingsView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel){
        backBtn.addActionListener(e -> layout.show(panel, "home"));
    }
}
