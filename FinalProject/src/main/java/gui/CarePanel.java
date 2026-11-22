package gui;

import javax.swing.*;
import java.awt.*;

public class CarePanel extends JPanel {
    public CarePanel() {
        // This panel has a top panel with the patient selection,
        // and a bottom panel with the workflow
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // The top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        TextBox patientIDBox = new TextBox("Patient ID");
        JButton patientSelectButton = new JButton("Select");
        topPanel.add(patientIDBox);
        topPanel.add(patientSelectButton);
        add(topPanel);

        // The bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        // The bottom panel is made up of two different columns
        JPanel leftColumn = new JPanel();
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
        JLabel patientIDLabel = styleLabel(new JLabel("Patient ID: "));
        JLabel patientNameLabel = styleLabel(new JLabel("Name: "));
        JLabel patientStageLabel = styleLabel(new JLabel("Stage: "));
        JLabel assignedStaffLabel = styleLabel(new JLabel("Assigned Staff: "));
        JButton assignStaffButton = new JButton("Assign Staff");
        TextBox treatmentBox = new TextBox("Treatment Name");
        TextBox performedByBox = new TextBox("Performed By...");
        JButton addTreatmentButton = new JButton("Add Treatment");
        leftColumn.add(patientIDLabel);
        leftColumn.add(patientNameLabel);
        leftColumn.add(patientStageLabel);
        leftColumn.add(assignedStaffLabel);
        leftColumn.add(assignStaffButton);
        leftColumn.add(treatmentBox);
        leftColumn.add(performedByBox);
        leftColumn.add(addTreatmentButton);
    }

    private JLabel styleLabel(JLabel l) {
        l.setFont(l.getFont().deriveFont(18f));
        return l;
    }
}
