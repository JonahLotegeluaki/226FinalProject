package controller;

import structures.AppData;
import gui.DashboardPanel;
import hospital.EmergencyWaitingRoom;

public class DashboardController {
    private DashboardPanel panel;
    private AppData ad;

    public DashboardController(DashboardPanel panel, AppData data) {
        if (panel == null)
            throw new IllegalArgumentException();
        this.panel = panel;
        this.ad = data;

        updateDashboard();
    }

    // Method to update the dashboard's values
    // Called when any code has assigned staff, moved a patient, etc.
    public void updateDashboard() {

        // If the app's waiting room is null, create a new one within this method, so
        // sizes return 0
        if (ad.waitingRoom == null)
            ad.waitingRoom = new EmergencyWaitingRoom();
        panel.getWaitingRoomLabel().setText(String.format("Waiting Room: %d", ad.waitingRoom.size()));
        String waitingRoomText = String.format(
                "Triage 1: %d waiting\nTriage 2: %d waiting\nTriage 3: %d waiting\nTriage 4: %d waiting",
                ad.waitingRoom.queueLevelSize(1),
                ad.waitingRoom.queueLevelSize(2),
                ad.waitingRoom.queueLevelSize(3),
                ad.waitingRoom.queueLevelSize(4));
        panel.getWaitingRoomArea().setText(waitingRoomText);
    }
}
