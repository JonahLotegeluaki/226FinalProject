package gui;

import javax.swing.*;
import structures.AppData;

public class MainWindow extends JFrame {
    private DashboardPanel dashboardPanel;
    private PatientPanel patientPanel;
    private CarePanel carePanel;
    private NavigationPanel navigationPanel;

    private JMenuBar menuBar;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem exitMenuItem;

    public MainWindow() {
        setTitle("Hospital Management");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Use a consisten LAF accros platforms
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.printf("Error occured setting look and feel: %s\n", e.getMessage());
        }

        // The menu bar
        menuBar = new JMenuBar();
        // File
        JMenu fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save As");
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(exitMenuItem);

        // Add everything
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // The tabbed screens
        dashboardPanel = new DashboardPanel();
        patientPanel = new PatientPanel();
        carePanel = new CarePanel();
        navigationPanel = new NavigationPanel();
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Dashboard", dashboardPanel);
        tabs.addTab("Patient", patientPanel);
        tabs.addTab("Care", carePanel);
        tabs.addTab("Navigation", navigationPanel);

        add(tabs);
        setVisible(true);
    }

    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public JMenuItem getSaveAsMenuItem() {
        return saveAsMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    // This method updates the GUI members with new data.
    // It should be called by the main() function when new data is added or loaded.
    // Takes in an AppData struct so that GUI and logic are seperated.
    private void update(AppData data) {
        // do something eventually
    }
}
