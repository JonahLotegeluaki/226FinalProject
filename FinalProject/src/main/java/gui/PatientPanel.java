package gui;

import java.awt.GridLayout;

import javax.swing.*;

public class PatientPanel extends JPanel {
    public PatientPanel() {
        // This panel is laid out with two columns of components
        GridLayout layout = new GridLayout(1, 2);
        setLayout(layout);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Left panel components
        JLabel patientSearchLabel = new JLabel("Search Registry", JLabel.CENTER);
        JLabel registrySizeLabel = new JLabel("Registry: 0 patients", JLabel.LEFT);
        TextBox patientSearchBox = new TextBox("Enter Patient ID...");
        JTextArea patientSearchResultArea = new JTextArea(20, 50);
        patientSearchResultArea.setEnabled(false);
        patientSearchResultArea.setText("Patient search results\nPlaceholder");
        JButton searchButton = new JButton("Search");
        leftPanel.add(patientSearchLabel);
        leftPanel.add(registrySizeLabel);
        leftPanel.add(patientSearchBox);
        leftPanel.add(searchButton);
        leftPanel.add(patientSearchResultArea);

        // Right panel components
        JLabel patientAddLabel = new JLabel("Add Patient", JLabel.CENTER);
        TextBox patientIDBox = new TextBox("*Patient ID");
        TextBox firstNameBox = new TextBox("*First Name");
        TextBox lastNameBox = new TextBox("*Last Name");
        TextBox triageLevelBox = new TextBox("*Triage Level");
        TextBox ageBox = new TextBox("*Age");
        TextBox insuraceIDBox = new TextBox("Insurance ID");
        TextBox roomBox = new TextBox("Room");
        JButton addPatientButton = new JButton("Add Patient");
        JTextArea addPatientResultArea = new JTextArea(20, 50);
        addPatientResultArea.setEnabled(false);
        addPatientResultArea.setText("Patient add results\nPlaceholder");
        rightPanel.add(patientAddLabel);
        rightPanel.add(patientIDBox);
        rightPanel.add(firstNameBox);
        rightPanel.add(lastNameBox);
        rightPanel.add(triageLevelBox);
        rightPanel.add(ageBox);
        rightPanel.add(insuraceIDBox);
        rightPanel.add(roomBox);
        rightPanel.add(addPatientButton);
        rightPanel.add(addPatientResultArea);

        add(leftPanel);
        add(rightPanel);
    }
}
