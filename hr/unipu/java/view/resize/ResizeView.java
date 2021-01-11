package hr.unipu.java.view.resize;

import hr.unipu.java.controller.ResizeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.FileSystems;

public class ResizeView {
    // Main panel
    JPanel resize = new JPanel();

    // ALl child panels
    JPanel resizeModePanel = new JPanel();
    JPanel pixelPanel = new JPanel();
    JPanel percentagePanel = new JPanel();
    JPanel storagePathPanel = new JPanel();
    JPanel deleteOriginalPanel = new JPanel();
    JPanel nextBackPanel = new JPanel();

    //Headers
    JLabel resizeHeader, fileOptionsHeader;

    // Resize mode panel components
    JLabel resizeModeLabel;
    JButton pixelBtn, percentageBtn;

    // Pixel panel components
    JComboBox<String> sizePresets;
    JLabel sizePresetsLabel, widthLabel, heightLabel;
    JTextField widthField, heightField;

    // Percentage panel components
    JLabel percentageLabel;
    JTextField percentageField;
    JSlider percentageSlider;

    // Storage path panel components
    JLabel storagePathLabel;
    JButton browseStoragePath;
    JTextField storagePath;

    // Delete original photos panel components
    JCheckBox deleteOriginalPhotosCBox;

    // Next back panel components
    JButton nextBtn, backBtn;

    // Number of selected photos
    public static JLabel numPhotosSelectedLabel = new JLabel();

    /**
     * All UI component settings.
     */
    public ResizeView() {
        setResize();
        createAndSetProps();
        addProps();
        ResizeController resizeLogic = new ResizeController(ResizeView.this);
    }

    /**
     * All panels' settings.
     */
    public void setResize() {
        // Main panel settings
        resize.setBackground(Color.WHITE);
        resize.setLayout(new GridLayout(9, 1, 10, 10));
        resize.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 100));

        // All child panels' settings
        resizeModePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        pixelPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
        percentagePanel.setLayout(new FlowLayout(FlowLayout.LEFT,20, 0));
        storagePathPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        deleteOriginalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        nextBackPanel.setLayout(new GridLayout(1,2,70,0));

        resizeModePanel.setBackground(Color.WHITE);
        storagePathPanel.setBackground(Color.WHITE);
        deleteOriginalPanel.setBackground(Color.WHITE);
        nextBackPanel.setBackground(Color.WHITE);
        pixelPanel.setBackground(Color.WHITE);
        percentagePanel.setBackground(Color.WHITE);

        percentagePanel.setVisible(false);
    }

    /**
     * All panel components' settings.
     */
    public void createAndSetProps() {
        resizeHeader = new JLabel("Resize");
        resizeHeader.setFont(resizeHeader.getFont().deriveFont(Font.BOLD, 25.0F));
        resizeHeader.setForeground(new Color(0, 128, 128));

        // Resize mode panel components' settings
        resizeModeLabel = new JLabel("Resize By:");
        resizeModeLabel.setFont(resizeModeLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        pixelBtn = new JButton("PIXEL");
        pixelBtn.setBackground(new Color(0, 128, 128));
        pixelBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        pixelBtn.setPreferredSize(new Dimension(120, 30));
        pixelBtn.setForeground(Color.WHITE);
        percentageBtn = new JButton("PERCENTAGE");
        percentageBtn.setBackground(Color.WHITE);
        percentageBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        percentageBtn.setPreferredSize(new Dimension(150, 30));
        percentageBtn.setForeground(new Color(0, 128, 128));
        resizeModePanel.add(resizeModeLabel);
        resizeModePanel.add(pixelBtn);
        resizeModePanel.add(percentageBtn);

        // Pixel panel components' settings
        sizePresetsLabel = new JLabel("Size Presets:");
        sizePresetsLabel.setFont(resizeModeLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        sizePresets = new JComboBox<>();
        sizePresets.addItem("CUSTOM");
        sizePresets.addItem("1920x1080");
        sizePresets.addItem("1600x900");
        sizePresets.addItem("1280x720");
        sizePresets.addItem("800x600");
        sizePresets.setFont(sizePresets.getFont().deriveFont(Font.PLAIN, 13.0F));
        widthLabel = new JLabel("Width:");
        widthField = new JTextField("800",4);
        heightLabel = new JLabel("Height:");
        heightField = new JTextField("600",4);
        widthLabel.setFont(widthLabel.getFont().deriveFont(Font.PLAIN, 14.0F));
        heightLabel.setFont(heightLabel.getFont().deriveFont(Font.PLAIN, 14.0F));
        widthField.setFont(widthField.getFont().deriveFont(Font.PLAIN, 14.0F));
        heightField.setFont(heightField.getFont().deriveFont(Font.PLAIN, 14.0F));
        pixelPanel.add(sizePresetsLabel);
        pixelPanel.add(sizePresets);
        pixelPanel.add(widthLabel);
        pixelPanel.add(widthField);
        pixelPanel.add(heightLabel);
        pixelPanel.add(heightField);

        // Percentage panel components' settings
        percentageLabel = new JLabel("Select Size:");
        percentageLabel.setFont(percentageLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        percentageField = new JTextField("70%", 3);
        percentageField.setFont(percentageField.getFont().deriveFont(Font.PLAIN, 20.0F));
        percentageSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 70);
        percentageSlider.setPreferredSize(new Dimension(200, 30));
        percentageSlider.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        percentageSlider.setBackground(Color.WHITE);
        percentagePanel.add(percentageLabel);
        percentagePanel.add(percentageField);
        percentagePanel.add(percentageSlider);

        fileOptionsHeader = new JLabel("File options");
        fileOptionsHeader.setFont(fileOptionsHeader.getFont().deriveFont(Font.BOLD, 25.0F));
        fileOptionsHeader.setForeground(new Color(0, 128, 128));

        // Storage panel components' settings
        storagePathLabel = new JLabel("Storage Path:");
        storagePathLabel.setFont(storagePathLabel.getFont().deriveFont(Font.PLAIN, 20.0F));
        browseStoragePath = new JButton("Browse");
        browseStoragePath.setBackground(new Color(0, 128, 128));
        browseStoragePath.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        browseStoragePath.setPreferredSize(new Dimension(150, 35));
        browseStoragePath.setForeground(Color.WHITE);
        storagePath = new JTextField(System.getProperty("user.home") + FileSystems.getDefault().getSeparator(), 10);
        storagePath.setFont(storagePath.getFont().deriveFont(Font.PLAIN, 18.0F));
        storagePath.setEditable(false);
        storagePathPanel.add(storagePathLabel);
        storagePathPanel.add(browseStoragePath);
        storagePathPanel.add(storagePath);

        // Delete original photos panel components' settings
        deleteOriginalPhotosCBox = new JCheckBox("Delete Original Photos:");
        deleteOriginalPhotosCBox.setFont(deleteOriginalPhotosCBox.getFont().deriveFont(Font.PLAIN, 20.0F));
        deleteOriginalPhotosCBox.setHorizontalTextPosition(SwingConstants.LEFT);
        deleteOriginalPhotosCBox.setBackground(Color.WHITE);
        deleteOriginalPanel.add(deleteOriginalPhotosCBox);


        // Next back panel components' settings
        backBtn = new JButton("BACK");
        backBtn.setBackground(new Color(128, 128, 128));
        backBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        backBtn.setPreferredSize(new Dimension(100, 60));
        backBtn.setForeground(Color.WHITE);
        nextBtn = new JButton("Resize");
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
     * Adding all panels and components to the main panel.
     */
    public void addProps() {
        resize.add(resizeHeader);
        resize.add(resizeModePanel);
        resize.add(pixelPanel);
        resize.add(percentagePanel);
        resize.add(fileOptionsHeader);
        resize.add(storagePathPanel);
        resize.add(deleteOriginalPanel);
        resize.add(nextBackPanel);
        resize.add(numPhotosSelectedLabel);
    }

    public JPanel getResize() {
        return resize;
    }
    public JButton getBackBtn() {
        return backBtn;
    }
    public JButton getNextBtn() {
        return nextBtn;
    }
    public JCheckBox getDeleteOriginalPhotosCBox() { return deleteOriginalPhotosCBox; }
    public JTextField getPercentageField() { return percentageField; }
    public JSlider getPercentageSlider() { return percentageSlider; }
    public JTextField getWidthField() { return widthField; }
    public JTextField getHeightField() { return heightField; }
    public JComboBox<String> getSizePresets() { return sizePresets; }
    public JButton getBrowseStoragePath() { return browseStoragePath; }
    public JTextField getStoragePath() { return storagePath; }
    public JButton getPixelBtn() { return pixelBtn; }
    public JButton getPercentageBtn() { return percentageBtn; }
    public JPanel getPixelPanel() { return pixelPanel; }
    public JPanel getPercentagePanel() { return percentagePanel; }

    /**
     * Action listeners for PleaseWaitView.
     * @param layout main layout
     * @param panel main panel
     */
    public void setActionListeners(CardLayout layout, JPanel panel) {

        /**
         * Triggers on every percentage slider movement.
         * When quality slider moves, updates the percentage textfield value.
         */
        getPercentageSlider().addChangeListener(e -> {
            int sliderValue = getPercentageSlider().getValue();
            getPercentageField().setText(sliderValue + "%");
        });

        /**
         * Triggers on every percentage textfield key press.
         * Prevents non-valid characters to be inputted into percentage textfield.
         */
        getPercentageField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                super.keyReleased(ke);
                String percentageStr = getPercentageField().getText();
                if(percentageStr.length() == 0) return;

                if(!isInteger(percentageStr)
                    && (percentageStr.charAt(percentageStr.length()-1) != '%')) {
                    getPercentageField().setText("70%");
                    getPercentageSlider().setValue(70);
                }
            }
        });

        /**
         * Triggers when user clicks away from the percentage textfield.
         * 1. Appends '%' to quality textfield if necessary.
         * 2. Checks if textfield has a valid value. If not it resets it to default (80%).
         */
        getPercentageField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String percentageStr = getPercentageField().getText();

                if(isInteger(percentageStr))
                    getPercentageField().setText(percentageStr + "%");
                else if(!percentageStr.isEmpty() && percentageStr.charAt(percentageStr.length()-1) != '%') {
                    getPercentageField().setText("70%");
                    getPercentageSlider().setValue(70);
                    return;
                }
                int fieldValue;
                if(percentageStr.charAt(percentageStr.length()-1) == '%')
                    fieldValue = Integer.parseInt(percentageStr.substring(0, percentageStr.length()-1));
                else
                    fieldValue = Integer.parseInt(percentageStr);
                if(fieldValue < 1 || fieldValue > 100){
                    getPercentageField().setText("70%");
                    getPercentageSlider().setValue(70);
                }
                getPercentageSlider().setValue(fieldValue);
            }
        });

        /**
         * Triggers on every width textfield key press.
         * Checks if the inputted key was a valid chraracter.
         */
        getWidthField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                getSizePresets().setSelectedIndex(0);
                JTextField tf = getWidthField();
                String value = tf.getText();
                if(value.isEmpty()) return;
                if (ke.getKeyChar() < '0' || ke.getKeyChar() > '9') {
                    tf.setEditable(false);
                    tf.setText("800");
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
                getSizePresets().setSelectedIndex(0);
                JTextField tf = getHeightField();
                String value = tf.getText();
                if(value.isEmpty()) return;
                if (ke.getKeyChar() < '0' || ke.getKeyChar() > '9') {
                    tf.setEditable(false);
                    tf.setText("600");
                    tf.setEditable(true);
                }
            }
        });

        /**
         * Triggers when user clicks away from the width textfield.
         * If textfield is empty, it sets it to default value (800).
         */
        getWidthField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String value = getWidthField().getText();
                if(value.isEmpty()) getWidthField().setText("800");
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
                if(value.isEmpty()) getHeightField().setText("600");
            }
        });

        /**
         * Triggers when user chooses a size preset.
         * It updates the width and height textfields to the selected size preset.
         */
        getSizePresets().addActionListener(e -> {
            String value = getSizePresets().getSelectedItem().toString();
            if(value.equals("1920x1080")) { widthField.setText("1920"); heightField.setText("1080"); }
            else if(value.equals("1600x900")) { widthField.setText("1600"); heightField.setText("900"); }
            else if(value.equals("1280x720")) { widthField.setText("1280"); heightField.setText("720"); }
            else if(value.equals("800x600")) { widthField.setText("800"); heightField.setText("600"); }
        });

        /**
         * Triggers when user clicks on the pixel button.
         * Swaps color templates of the pixel and percentage buttons.
         */
        getPixelBtn().addActionListener(e -> {
            getPixelPanel().setVisible(true);
            getPercentagePanel().setVisible(false);
            getPixelBtn().setBackground(new Color(0, 128, 128));
            getPixelBtn().setForeground(Color.WHITE);
            getPercentageBtn().setBackground(Color.WHITE);
            getPercentageBtn().setForeground(new Color(0, 128, 128));
        });

        /**
         * Triggers when user clicks on the percentage button.
         * Swaps color templates of the pixel and percentage buttons.
         */
        getPercentageBtn().addActionListener(e -> {
            getPixelPanel().setVisible(false);
            getPercentagePanel().setVisible(true);
            getPercentageBtn().setBackground(new Color(0, 128, 128));
            getPercentageBtn().setForeground(Color.WHITE);
            getPixelBtn().setBackground(Color.WHITE);
            getPixelBtn().setForeground(new Color(0, 128, 128));
        });

        getBackBtn().addActionListener(e -> layout.show(panel, "selectImages"));
        getNextBtn().addActionListener(e -> {
            layout.show(panel, "pleaseWait");
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
