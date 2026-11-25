package gui;

import java.awt.Component;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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

    private JLabel clockLabel;
    private Timer timer;

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

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Clock setup
        clockLabel = new JLabel();
        clockLabel.setFont(clockLabel.getFont().deriveFont(20f));
        add(clockLabel);

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

        // Start the clock
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy  HH:mm:ss");
        clockLabel.setText(LocalDateTime.now().format(formatter));
        timer = new Timer(1000, e -> {
            clockLabel.setText(LocalDateTime.now().format(formatter));
        });
        timer.start();
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

    // Return the requested panel if in range
    // Convenience so there's not 4 getPanel methods
    public JPanel getPanel(int index) {
        return index >= 0 && index < 4
                ? new JPanel[] { dashboardPanel, patientPanel, carePanel, navigationPanel }[index]
                : null;
    }

    // Called when Main wants a filename to open
    // Exists here to seperate GUI components
    public String getFilenameOpen(String dialogTitle, Component parent) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(dialogTitle);
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JSON files (*.json)", "json"));
        fc.setCurrentDirectory(new File(System.getProperty("user.dir"))); // Open in current directory
        int res = fc.showOpenDialog(parent);
        if (res == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile().getAbsolutePath();
        else
            return null;
    }

    public void errorDialog(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    // This method updates the GUI members with new data.
    // It should be called by the main() function when new data is added or loaded.
    // Takes in an AppData struct so that GUI and logic are seperated.
    private void update(AppData data) {
        // do something eventually
    }
}
