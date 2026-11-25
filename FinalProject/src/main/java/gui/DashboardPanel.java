package gui;

import javax.swing.*;

public class DashboardPanel extends JPanel {
    JLabel waitingRoomLabel;
    JTextArea waitingRoomArea;
    JLabel staffLabel;
    JTextArea staffArea;

    public DashboardPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Left panel (waiting room)
        waitingRoomLabel = new JLabel("Waiting room: 0");
        waitingRoomArea = new JTextArea(30, 20);
        waitingRoomArea.setEnabled(false);
        waitingRoomArea.setText("Triage 1: 0\nTriage 2: 0\nTriage 3: 0\nTriage 4: 0");
        leftPanel.add(waitingRoomLabel);
        leftPanel.add(waitingRoomArea);

        // Right panel (staff overview)
        staffLabel = new JLabel("Total Staff: 0");
        staffArea = new JTextArea(30, 20);
        staffArea.setEnabled(false);
        staffArea.setText("Lowest load: \"\", 0\nHighest load: \"\", 0\nIdle: 0");
        rightPanel.add(staffLabel);
        rightPanel.add(staffArea);

        add(leftPanel);
        add(rightPanel);
    }
}
