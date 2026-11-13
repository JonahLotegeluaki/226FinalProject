package gui;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Placeholder");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Dashboard", new DashboardPanel());
        tabs.addTab("Patient", new PatientPanel());
        tabs.addTab("Care", new CarePanel());
        tabs.addTab("Navigation", new NavigationPanel());

        System.out.println("test");

        add(tabs);
        setVisible(true);
    }
}
