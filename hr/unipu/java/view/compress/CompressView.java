package hr.unipu.java.view.compress;

import hr.unipu.java.controller.CompressController;
import hr.unipu.java.model.CommonStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.FileSystems;

public class CompressView {
    // Main panel
    JPanel compress = new JPanel();

    // All child panels
    JPanel qualityPanel = new JPanel();
    JPanel maintainResolutionPanel = new JPanel();
    JPanel heightWidthPanel = new JPanel();
    JPanel storagePathPanel = new JPanel();
    JPanel deleteOriginalPanel = new JPanel();
    JPanel nextBackPanel = new JPanel();

    // Quality panel components
    JLabel compressHeader;
    JLabel qualityLabel;
    JTextField qualityField;
    JSlider qualitySlider;

    // Maintain resolution panel components
    JCheckBox maintainResolutionCBox;
    JLabel widthLabel, heightLabel;
    JTextField widthField, heightField;

    // Storage path panel components
    JLabel fileOptionsHeader;
    JLabel storagePathLabel;
    JButton browseStoragePath;
    JTextField storagePath;

    // Delete original photos panel components
    JCheckBox deleteOriginalPhotosCBox;

    // Next back panel components
    JButton nextBtn, backBtn;

    //Number of selected photos label
    public static JLabel numPhotosSelectedLabel = new JLabel();


    public CompressView() {
        setCompress();
        createAndSetProps();
        addProps();
        CompressController compressLogicController = new CompressController(CompressView.this);
    }

    /**
     * Adds settings to main and sub panels.
     */
    public void setCompress() {
        // Main panel settings
        compress.setBackground(Color.WHITE);
        compress.setLayout(new GridLayout(8, 1, 10, 10));
        compress.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 100));

        // All sub panel layouts
        qualityPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20, 0));
        maintainResolutionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        heightWidthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        storagePathPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        deleteOriginalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        nextBackPanel.setLayout(new GridLayout(1,2,70,0));

        // All sub panel background colors
        qualityPanel.setBackground(Color.WHITE);
        maintainResolutionPanel.setBackground(Color.WHITE);
        heightWidthPanel.setBackground(Color.WHITE);
        storagePathPanel.setBackground(Color.WHITE);
        deleteOriginalPanel.setBackground(Color.WHITE);
        nextBackPanel.setBackground(Color.WHITE);
    }

    /**
     * Adds settings to components.
     */
    public void createAndSetProps() {
        // Compress header
        compressHeader = new JLabel("Compress (jpg/jpeg only)");
        compressHeader.setFont(compressHeader.getFont().deriveFont(Font.BOLD, 25.0F));
        compressHeader.setForeground(new Color(0, 128, 128));

        // Quality panel components settings
        qualityLabel = new JLabel("Select Quality:");
        qualityLabel.setFont(qualityLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        qualityField = new JTextField("80%", 3);
        qualityField.setFont(qualityLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        qualitySlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 80);
        qualitySlider.setPreferredSize(new Dimension(150, 45));
        qualitySlider.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        qualitySlider.setBackground(Color.WHITE);
        qualityPanel.add(qualityLabel);
        qualityPanel.add(qualityField);
        qualityPanel.add(qualitySlider);

        // Maintain resolution panel components settings
        maintainResolutionCBox = new JCheckBox("Maintain Resolution: ", true);
        maintainResolutionCBox.setFont(maintainResolutionCBox.getFont().deriveFont(Font.PLAIN, 20.0F));
        maintainResolutionCBox.setHorizontalTextPosition(SwingConstants.LEFT);
        maintainResolutionCBox.setBackground(Color.WHITE);
        maintainResolutionPanel.add(maintainResolutionCBox);
        widthField = new JTextField("1920", 4);
        heightField = new JTextField("1080", 4);
        widthLabel = new JLabel("Width:");
        heightLabel = new JLabel("Height:");
        widthLabel.setFont(widthLabel.getFont().deriveFont(Font.PLAIN, 12F));
        heightLabel.setFont(heightLabel.getFont().deriveFont(Font.PLAIN, 12F));
        widthField.setFont(widthField.getFont().deriveFont(Font.PLAIN, 12F));
        heightField.setFont(heightField.getFont().deriveFont(Font.PLAIN, 12F));
        heightWidthPanel.add(widthLabel);
        heightWidthPanel.add(widthField);
        heightWidthPanel.add(heightLabel);
        heightWidthPanel.add(heightField);
        heightWidthPanel.setVisible(false);
        maintainResolutionPanel.add(heightWidthPanel);

        // File options header
        fileOptionsHeader = new JLabel("File options");
        fileOptionsHeader.setFont(fileOptionsHeader.getFont().deriveFont(Font.BOLD, 25.0F));
        fileOptionsHeader.setForeground(new Color(0, 128, 128));

        // Storage path panel components settings
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

        // Delete original photos panel settings
        deleteOriginalPhotosCBox = new JCheckBox("Delete Original Photos:");
        deleteOriginalPhotosCBox.setFont(deleteOriginalPhotosCBox.getFont().deriveFont(Font.PLAIN, 20.0F));
        deleteOriginalPhotosCBox.setHorizontalTextPosition(SwingConstants.LEFT);
        deleteOriginalPhotosCBox.setBackground(Color.WHITE);
        deleteOriginalPanel.add(deleteOriginalPhotosCBox);

        // Next back panel settings
        backBtn = new JButton("BACK");
        backBtn.setBackground(new Color(128, 128, 128));
        backBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        backBtn.setPreferredSize(new Dimension(100, 60));
        backBtn.setForeground(Color.WHITE);
        nextBtn = new JButton("Compress");
        nextBtn.setBackground(new Color(0, 100, 100));
        nextBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        nextBtn.setPreferredSize(new Dimension(100, 60));
        nextBtn.setForeground(Color.WHITE);
        nextBackPanel.add(backBtn);
        nextBackPanel.add(nextBtn);

        // Number of selected photos settings
        numPhotosSelectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numPhotosSelectedLabel.setFont(numPhotosSelectedLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        numPhotosSelectedLabel.setForeground(Color.GRAY);
    }

    /**
     * Adds components to the main panel.
     */
    public void addProps() {
        compress.add(compressHeader);
        compress.add(qualityPanel);
        compress.add(maintainResolutionPanel);
        compress.add(fileOptionsHeader);
        compress.add(storagePathPanel);
        compress.add(deleteOriginalPanel);
        compress.add(nextBackPanel);
        compress.add(numPhotosSelectedLabel);
    }

    public JPanel getCompress() { return compress; }
    public JTextField getQualityField(){return qualityField;}
    public JSlider getQualitySlider() { return qualitySlider; }
    public JCheckBox getDeleteOriginalPhotosCBox() { return deleteOriginalPhotosCBox; }
    public JPanel getHeightWidthPanel() { return heightWidthPanel; }
    public JCheckBox getMaintainResolutionCBox(){return maintainResolutionCBox;}
    public JTextField getWidthField() { return widthField; }
    public JTextField getHeightField() { return heightField; }
    public JTextField getStoragePath(){return storagePath;}
    public JButton getBrowseStoragePath() { return browseStoragePath; }
    public JButton getBackBtn() { return backBtn; }
    public JButton getCompressBtn() { return nextBtn; }

    /**
     * Action listeners for CompressView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel) {
        getBackBtn().addActionListener(e -> layout.show(panel, "selectImages"));

        getCompressBtn().addActionListener(e -> {
            layout.show(panel, "pleaseWait");
        });

        /**
         * Triggers on every quality slider movement.
         * When quality slider moves, updates the quality textfield value.
         */
        getQualitySlider().addChangeListener(e -> {
            int sliderValue = getQualitySlider().getValue();
            getQualityField().setText(sliderValue + "%");
        });

        /**
         * Triggers on every quality textfield key press.
         * Prevents non-valid characters to be inputted into quality textfield.
         */
        getQualityField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                super.keyReleased(ke);
                String percentageStr = getQualityField().getText();
                if(percentageStr.length() == 0) return;

                if(!isInteger(percentageStr)
                        && (percentageStr.charAt(percentageStr.length()-1) != '%')) {
                    getQualityField().setText("80%");
                    getQualitySlider().setValue(80);
                }
            }
        });

        /**
         * Triggers when user clicks away from the quality textfield.
         * 1. Appends '%' to quality textfield if necessary.
         * 2. Checks if textfield has a valid value. If not it resets it to default (80%).
         */
        getQualityField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String percentageStr = getQualityField().getText();

                if(isInteger(percentageStr))
                    getQualityField().setText(percentageStr + "%");
                else if(!percentageStr.isEmpty() && percentageStr.charAt(percentageStr.length()-1) != '%') {
                    getQualityField().setText("80%");
                    getQualitySlider().setValue(80);
                    return;
                }
                int fieldValue;
                if(percentageStr.charAt(percentageStr.length()-1) == '%')
                    fieldValue = Integer.parseInt(percentageStr.substring(0, percentageStr.length()-1));
                else
                    fieldValue = Integer.parseInt(percentageStr);

                if(fieldValue < 1 || fieldValue > 100){
                    getQualityField().setText("80%");
                    getQualitySlider().setValue(80);
                }
                getQualitySlider().setValue(fieldValue);
            }
        });

        /**
         * Triggers on every width textfield key press.
         * Checks if the inputted key was a valid chraracter.
         */
        getWidthField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                JTextField tf = getWidthField();
                String value = tf.getText();
                if(value.isEmpty()) return;
                if (ke.getKeyChar() < '0' || ke.getKeyChar() > '9') {
                    tf.setEditable(false);
                    tf.setText("1920");
                    tf.setEditable(true);
                }
            }
        });

        /**
         * Triggers on every height textfield key press.
         * Checks if the inputted key was a valid chraracter.
         */
        getHeightField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                JTextField tf = getHeightField();
                String value = tf.getText();
                if(value.isEmpty()) return;
                if (ke.getKeyChar() < '0' || ke.getKeyChar() > '9') {
                    tf.setEditable(false);
                    tf.setText("1080");
                    tf.setEditable(true);
                }
            }
        });

        /**
         * Triggers when user clicks away from the width textfield.
         * If textfield is empty, it sets it to default value (1920).
         */
        getWidthField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String value = getWidthField().getText();
                if(value.isEmpty()) getWidthField().setText("1920");
            }
        });


        /**
         * Triggers when user clicks away from the height textfield.
         * If textfield is empty, it sets it to default value (1080).
         */
        getHeightField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String value = getHeightField().getText();
                if(value.isEmpty()) getHeightField().setText("1080");
            }
        });
    }

    /**
     * Checks if string is a integer.
     * @param str input string
     * @return true if str is integer, else false
     */
    private boolean isInteger(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
