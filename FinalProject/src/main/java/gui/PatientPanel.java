package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PatientPanel extends JPanel {

    private JPanel leftPanel;
    private JPanel rightPanel;

    // Left panel components
    private JLabel patientSearchLabel;
    private JLabel registrySizeLabel;
    private TextBox patientSearchBox;
    private JTextArea patientSearchResultArea;
    private JButton searchButton;

    // Right panel components
    private JLabel patientAddLabel;
    private TextBox patientIDBox;
    private TextBox firstNameBox;
    private TextBox lastNameBox;
    private TextBox triageLevelBox;
    private TextBox ageBox;
    private TextBox insuraceIDBox;
    private TextBox roomBox;
    private JButton addPatientButton;
    private JTextArea addPatientResultArea;

    public PatientPanel() {
        // This panel is laid out with two columns of components
        GridLayout layout = new GridLayout(1, 2);
        setLayout(layout);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Left panel components
        patientSearchLabel = new JLabel("Search Registry", JLabel.CENTER);
        registrySizeLabel = new JLabel("Registry: 0 patients", JLabel.LEFT);
        patientSearchBox = new TextBox("Enter Patient ID...");
        patientSearchResultArea = new JTextArea(20, 50);
        patientSearchResultArea.setEnabled(false);
        patientSearchResultArea.setText("Patient search results\nPlaceholder");
        searchButton = new JButton("Search");
        leftPanel.add(patientSearchLabel);
        leftPanel.add(registrySizeLabel);
        leftPanel.add(patientSearchBox);
        leftPanel.add(searchButton);
        leftPanel.add(patientSearchResultArea);

        // Right panel components
        patientAddLabel = new JLabel("Add Patient", JLabel.CENTER);
        patientIDBox = new TextBox("*Patient ID");
        firstNameBox = new TextBox("*First Name");
        lastNameBox = new TextBox("*Last Name");
        triageLevelBox = new TextBox("*Triage Level");
        ageBox = new TextBox("*Age");
        insuraceIDBox = new TextBox("Insurance ID");
        roomBox = new TextBox("Room");
        addPatientButton = new JButton("Add Patient");
        addPatientResultArea = new JTextArea(20, 50);
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

    // Methods for interacting with the search functionality
    public void setSearchButtonActionListener(ActionListener al) {
        // First remove the action listeners
        ActionListener[] als = searchButton.getActionListeners();
        if (als.length > 0)
            for (ActionListener a : als)
                searchButton.removeActionListener(a);
        searchButton.addActionListener(al);
    }

    public String getPatientSearchID() {
        return patientSearchBox.getText();
    }

    public void setSearchResultText(String text) {
        patientSearchResultArea.setText(text);
    }
}