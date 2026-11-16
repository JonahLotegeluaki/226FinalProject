package controller;

import structures.AppData;
import gui.DashboardPanel;

public class DashboardController {
    private DashboardPanel panel;
    private AppData data;

    public DashboardController(DashboardPanel panel, AppData data) {
        if (panel == null)
            throw new IllegalArgumentException();
        this.panel = panel;
        this.data = data;
    }
}
