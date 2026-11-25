
package controller;

import gui.NavigationPanel;
import structures.HospitalGraph;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class NavigationController {

    private NavigationPanel panel;
    private HospitalGraph graph;

    public NavigationController(NavigationPanel panel,
                                HospitalGraph graph) {

        this.panel = panel;
        this.graph = graph;

        loadLocations();
        updateGraphCounts();

        panel.getCalculateRouteButton().addActionListener(e -> {
            calculateRoute();
        });
    }

    public void loadLocations() {

        panel.getStartRoomBox().removeAllItems();
        panel.getEndRoomBox().removeAllItems();

        List<String> locations =
                new ArrayList<>(graph.getLocations());

        Collections.sort(locations);

        for (String location : locations) {

            panel.getStartRoomBox().addItem(location);
            panel.getEndRoomBox().addItem(location);
        }
    }

    public void updateGraphCounts() {

        panel.getLocationCountLabel().setText(
                "Total Locations: " + graph.getLocationCount());

        panel.getConnectionCountLabel().setText(
                "Total Connections: " + graph.getConnectionCount());
    }

    public void calculateRoute() {

        String start = (String)
                panel.getStartRoomBox().getSelectedItem();

        String end = (String)
                panel.getEndRoomBox().getSelectedItem();

        if (start == null || end == null) {

            displayError("Select both locations.");
            return;
        }

        List<String> path = graph.findShortestPath(start, end);

        if (path.isEmpty()) {

            displayError("No route exists.");
            return;
        }

        double cost = graph.getRouteCost(path);

        panel.getRouteValidityLabel().setText(
                "Route Validity: Valid");

        panel.getRouteCostLabel().setText(
                String.format("Route Cost: %.2f", cost));

        panel.getRouteErrorLabel().setText(
                "Route Error: None");

        panel.getRouteArea().setText(
                String.join(" -> ", path));
    }

    private void displayError(String message) {

        panel.getRouteValidityLabel().setText(
                "Route Validity: Invalid");

        panel.getRouteCostLabel().setText(
                "Route Cost: N/A");

        panel.getRouteErrorLabel().setText(
                "Route Error: " + message);

        panel.getRouteArea().setText(
                "No route available.");
    }
}
