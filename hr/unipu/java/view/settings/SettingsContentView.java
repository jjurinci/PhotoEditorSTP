package hr.unipu.java.view.settings;

import hr.unipu.java.view.home.HeaderView;

import javax.swing.*;
import java.awt.*;

public class SettingsContentView {
    JPanel settingsContent = new JPanel();
    HeaderView headerView;
    SettingsView settingsView;

    /**
     * All components' UI settings.
     */
    public SettingsContentView(){
        setSettingsContent();
        createSettingsContent();
        addSettingsContent();
    }

    public JPanel getSettingsContent(){
        return settingsContent;
    }
    public void setSettingsContent(){
        settingsContent.setLayout(new BorderLayout());
    }
    public SettingsView getSettings(){
        return settingsView;
    }

    /**
     * Creates the header and settings view.
     */
    public void createSettingsContent(){
        headerView = new HeaderView();
        settingsView = new SettingsView();
    }

    /**
     * Adds the header and settings views to the parent panel.
     */
    public void addSettingsContent(){
        settingsContent.add(headerView.getHeader(), BorderLayout.NORTH);
        settingsContent.add(settingsView.getSettings(), BorderLayout.CENTER);
    }
}
