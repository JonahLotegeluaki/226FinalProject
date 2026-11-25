
package gui;

import javax.swing.*;
import java.awt.*;

public class NavigationPanel extends JPanel {

    JComboBox<String> startRoomBox;
    JComboBox<String> endRoomBox;

    JButton calculateRouteButton;

    JLabel locationCountLabel;
    JLabel connectionCountLabel;

    JLabel routeValidityLabel;
    JLabel routeCostLabel;
    JLabel routeErrorLabel;

    JTextArea routeArea;

    JPanel mapPanel;

    public NavigationPanel() {

        setLayout(new BorderLayout(10, 10));

        // Create the upper section
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 10, 10));

        // Left panel (room selection)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(
                new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        startRoomBox = new JComboBox<>();
        endRoomBox = new JComboBox<>();

        calculateRouteButton = new JButton("Calculate Route");

        locationCountLabel = new JLabel("Total Locations: 0");
        connectionCountLabel = new JLabel("Total Connections: 0");

        leftPanel.add(new JLabel("Start Room"));
        leftPanel.add(startRoomBox);

        leftPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(new JLabel("End Room"));
        leftPanel.add(endRoomBox);

        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(calculateRouteButton);

        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(locationCountLabel);
        leftPanel.add(connectionCountLabel);

        // Right panel (route information)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(
                new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        rightPanel.setBorder(
                BorderFactory.createTitledBorder("Route Information"));

        routeValidityLabel = new JLabel("Route Validity: N/A");
        routeCostLabel = new JLabel("Route Cost: N/A");
        routeErrorLabel = new JLabel("Route Error: None");

        routeArea = new JTextArea(5, 20);
        routeArea.setEditable(false);
        routeArea.setLineWrap(true);
        routeArea.setWrapStyleWord(true);
        routeArea.setText("No route selected.");

        rightPanel.add(routeValidityLabel);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(routeCostLabel);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(routeErrorLabel);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(new JLabel("Shortest Path:"));
        rightPanel.add(new JScrollPane(routeArea));

        // Add both panels to the upper section
        topPanel.add(leftPanel);
        topPanel.add(rightPanel);

        // Placeholder for the hospital map
        mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout());

        mapPanel.setBorder(
                BorderFactory.createTitledBorder("Hospital Map"));

        JLabel mapLabel = new JLabel(
                "Hospital Map (Coming Soon)",
                SwingConstants.CENTER);

        mapPanel.add(mapLabel, BorderLayout.CENTER);

        // Add sections to the navigation panel
        add(topPanel, BorderLayout.NORTH);
        add(mapPanel, BorderLayout.CENTER);
    }

    public JComboBox<String> getStartRoomBox() {
        return startRoomBox;
    }

    public JComboBox<String> getEndRoomBox() {
        return endRoomBox;
    }

    public JButton getCalculateRouteButton() {
        return calculateRouteButton;
    }

    public JLabel getLocationCountLabel() {
        return locationCountLabel;
    }

    public JLabel getConnectionCountLabel() {
        return connectionCountLabel;
    }

    public JLabel getRouteValidityLabel() {
        return routeValidityLabel;
    }

    public JLabel getRouteCostLabel() {
        return routeCostLabel;
    }

    public JLabel getRouteErrorLabel() {
        return routeErrorLabel;
    }

    public JTextArea getRouteArea() {
        return routeArea;
    }

    public JPanel getMapPanel() {
        return mapPanel;
    }
}
