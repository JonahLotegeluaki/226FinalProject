package gui;

import javax.swing.*;
import structures.AppData;

public class MainWindow extends JFrame {
    private DashboardPanel dashboardPanel;
    private PatientPanel patientPanel;
    private CarePanel carePanel;
    private NavigationPanel navigationPanel;

    public MainWindow() {
        setTitle("Hospital Management");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Use a consisten LAF accros platforms
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception e) {
            System.err.printf("Error occured setting look and feel: %s\n", e.getMessage());
        }

        //FlowLayout layout = new FlowLayout();
        //setLayout(layout);

        // The menu bar
        JMenuBar menuBar = new JMenuBar();
        // File
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
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

    // This method updates the GUI members with new data.
    // It should be called by the main() function when new data is added or loaded.
    // Takes in an AppData struct so that GUI and logic are seperated.
    private void update(AppData data) {
        // do something eventually
    }
}
