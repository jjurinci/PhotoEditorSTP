package hr.unipu.java.view.convert;

import hr.unipu.java.controller.ConvertController;

import javax.swing.*;
import java.awt.*;
import java.nio.file.FileSystems;

public class ConvertView {
    // Main panel
    JPanel convert = new JPanel();

    // All child panels
    JPanel convertToPanel = new JPanel();
    JPanel storagePathPanel = new JPanel();
    JPanel deleteOriginalPanel = new JPanel();
    JPanel nextBackPanel = new JPanel();

    // Headers
    JLabel convertHeader, fileOptionsHeader;

    // Convert to panel components
    JLabel convertToLabel;
    JComboBox resultPhotoFormat;

    // Storage path panel components
    JLabel storagePathLabel;
    JButton browseStoragePath;
    JTextField storagePath;

    // Delete original photos panel component
    JCheckBox deleteOriginalPhotosCBox;

    // Next back panel components
    JButton nextBtn, backBtn;

    // Number of selected photos
    public static JLabel numPhotosSelectedLabel = new JLabel();

    /**
     * All UI settings.
     */
    public ConvertView() {
        setConvert();
        createAndSetProps();
        addProps();
        ConvertController convertLogicController = new ConvertController(ConvertView.this);
    }

    /**
     * All panel settings.
     */
    public void setConvert() {
        // Main panel settings
        convert.setBackground(Color.WHITE);
        convert.setLayout(new GridLayout(7, 1, 10, 10));
        convert.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // All sub panel settings
        convertToPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20, 0));
        storagePathPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        deleteOriginalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        nextBackPanel.setLayout(new GridLayout(1,2,70,0));

        convertToPanel.setBackground(Color.WHITE);
        storagePathPanel.setBackground(Color.WHITE);
        deleteOriginalPanel.setBackground(Color.WHITE);
        nextBackPanel.setBackground(Color.WHITE);
    }

    /**
     * All panel components settings.
     */
    public void createAndSetProps() {
        convertHeader = new JLabel("Convert");
        convertHeader.setFont(convertHeader.getFont().deriveFont(Font.BOLD, 25.0F));
        convertHeader.setForeground(new Color(0, 128, 128));

        // Convert to panel components settings
        convertToLabel = new JLabel("Convert to:");
        convertToLabel.setFont(convertToLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        resultPhotoFormat = new JComboBox();
        resultPhotoFormat.addItem("JPG");
        resultPhotoFormat.addItem("PNG");
        resultPhotoFormat.addItem("TIFF");
        resultPhotoFormat.addItem("BMP");
        resultPhotoFormat.setFont(resultPhotoFormat.getFont().deriveFont(Font.PLAIN, 16.0F));
        convertToPanel.add(convertToLabel);
        convertToPanel.add(resultPhotoFormat);

        fileOptionsHeader = new JLabel("File options");
        fileOptionsHeader.setFont(fileOptionsHeader.getFont().deriveFont(Font.BOLD, 25.0F));
        fileOptionsHeader.setForeground(new Color(0, 128, 128));

        // Storage path panel component settings
        storagePathLabel = new JLabel("Storage Path:");
        storagePathLabel.setFont(storagePathLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        browseStoragePath = new JButton("Browse");
        browseStoragePath.setBackground(new Color(0, 128, 128));
        browseStoragePath.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        browseStoragePath.setPreferredSize(new Dimension(150, 30));
        browseStoragePath.setForeground(Color.WHITE);
        storagePath = new JTextField(System.getProperty("user.home") + FileSystems.getDefault().getSeparator(), 10);
        storagePath.setFont(storagePath.getFont().deriveFont(Font.PLAIN, 18.0F));
        storagePath.setEditable(false);
        storagePathPanel.add(storagePathLabel);
        storagePathPanel.add(browseStoragePath);
        storagePathPanel.add(storagePath);

        // Delete original photos panel component settings
        deleteOriginalPhotosCBox = new JCheckBox("Delete Original Photos:");
        deleteOriginalPhotosCBox.setFont(deleteOriginalPhotosCBox.getFont().deriveFont(Font.PLAIN, 20.0F));
        deleteOriginalPhotosCBox.setHorizontalTextPosition(SwingConstants.LEFT);
        deleteOriginalPhotosCBox.setBackground(Color.WHITE);
        deleteOriginalPanel.add(deleteOriginalPhotosCBox);

        // Next back panel component settings
        backBtn = new JButton("BACK");
        backBtn.setBackground(new Color(128, 128, 128));
        backBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        backBtn.setPreferredSize(new Dimension(100, 60));
        backBtn.setForeground(Color.WHITE);

        nextBtn = new JButton("Convert");
        nextBtn.setBackground(new Color(0, 100, 100));
        nextBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        nextBtn.setPreferredSize(new Dimension(100, 60));
        nextBtn.setForeground(Color.WHITE);
        nextBackPanel.add(backBtn);
        nextBackPanel.add(nextBtn);

        numPhotosSelectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numPhotosSelectedLabel.setFont(numPhotosSelectedLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        numPhotosSelectedLabel.setForeground(Color.GRAY);
    }

    /**
     * Adds all components to the main panel
     */
    public void addProps() {
        convert.add(convertHeader);
        convert.add(convertToPanel);
        convert.add(fileOptionsHeader);
        convert.add(storagePathPanel);
        convert.add(deleteOriginalPanel);
        convert.add(nextBackPanel);
        convert.add(numPhotosSelectedLabel);
    }

    public JPanel getConvert() {
        return convert;
    }
    public JButton getBackBtn() {
        return backBtn;
    }
    public JButton getNextBtn() {
        return nextBtn;
    }
    public JTextField getStoragePath() { return storagePath; }
    public JButton getStoragePathBtn() { return browseStoragePath; }
    public JComboBox getResultPhotoFormat() { return resultPhotoFormat; }
    public JCheckBox getDeleteOriginalPhotosCBox() { return deleteOriginalPhotosCBox; }

    /**
     * Action listeners for ConvertView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel) {
        getBackBtn().addActionListener(e -> layout.show(panel, "selectImages"));

        getNextBtn().addActionListener(e -> {
            layout.show(panel, "pleaseWait");
        });
    }
}
