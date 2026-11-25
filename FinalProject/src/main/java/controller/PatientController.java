package controller;

import java.awt.event.*;
import java.time.LocalTime;

import gui.MainWindow;
import gui.PatientPanel;
import patient.*;
import structures.AppData;

public class PatientController {
    MainWindow window;

    // Assigns proper ActionListeners to the buttons
    public PatientController(MainWindow window, PatientPanel panel, AppData ad) {
        // Store a reference to the main window
        this.window = window;

        // Set up the patient search
        panel.setSearchButtonActionListener(searchActionListener(panel, ad));

        // Set up patient adding
        panel.setAddPatientButtonActionListener(addActionListener(panel, ad));
    }

    // Creates the action listener for the search button
    private ActionListener searchActionListener(PatientPanel panel, AppData ad) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = panel.getPatientSearchID().trim();
                if (ad.registry == null) {
                    panel.setSearchResultText("Cannot find patient \"" + id + "\": Registry is empty");
                    return;
                }
                Patient p = ad.registry.getPatientByID(id);
                if (p == null)
                    panel.setSearchResultText("Cannot find patient \"" + id + "\": Could not find in registry");
                else {
                    String text = String.format(
                            "  Patient ID: %s\n        Name: %s %s\n         Age: %d\n   Complaint: %s\nTriage Level: %d\n       Stage: %s\n        Room: %s\nArrival Hour: %d\nInsurance ID: %s",
                            p.getPatientID(),
                            p.getFirstName(),
                            p.getLastName(),
                            p.getAge(),
                            p.getChiefComplaint(),
                            p.getTriageLevel(),
                            p.getCurrentStage(),
                            p.getAssignedRoom(),
                            p.getArrivalHour(),
                            p.getInsuranceID());
                    panel.setSearchResultText(text);
                }
            }
        };
    }

    private ActionListener addActionListener(PatientPanel panel, AppData ad) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the fields
                String patientID = panel.getPatientIDBox().getText().trim();
                String firstName = panel.getFirstNameBox().getText().trim();
                String lastName = panel.getLastNameBox().getText().trim();
                String triageLevel = panel.getTriageLevelBox().getText().trim();
                String age = panel.getAgeBox().getText().trim();
                String complaint = panel.getComplaintBox().getText().trim();
                String insuranceID = panel.getInsuranceIDBox().getText().trim();
                String room = panel.getRoomBox().getText().trim();
                String stage = panel.getStageBox().getText().trim();

                // Check for empty required fields
                if (patientID.isEmpty() ||
                        firstName.isEmpty() ||
                        lastName.isEmpty() ||
                        age.isEmpty() ||
                        complaint.isEmpty() ||
                        triageLevel.isEmpty()) {
                    window.errorDialog(
                            "Missing Information",
                            "Please fill out all required (*) fields.");
                    return;
                }

                // Validate numeric fields
                try {
                    int triage = Integer.parseInt(triageLevel);
                    int patientAge = Integer.parseInt(age);

                    // Good to go, create the patient
                    Patient p = new Patient(patientID, firstName, lastName, patientAge, complaint, triage, stage, room,
                            LocalTime.now().getHour(), insuranceID);
                    // If registry is null, make a new one
                    if (ad.registry == null)
                        ad.registry = new PatientRegistry();
                    // Reject a duplicate patient entry
                    if (ad.registry.getPatientByID(patientID) != null) {
                        window.errorDialog("Duplicate Patient", "The specified patient ID already exists.");
                        return;
                    }
                    // If all checks pass, add the patient
                    ad.registry.addPatient(p);

                    // The session data was modified.
                    window.setSessionModified(true);
                } catch (NumberFormatException ex) {
                    window.errorDialog(
                            "Invalid Input",
                            "Triage level and age must be valid numbers.");
                    return;
                }
            }
        };
    }
}
