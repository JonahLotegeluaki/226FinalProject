package controller;

import java.awt.event.*;
import gui.PatientPanel;
import patient.*;
import structures.AppData;

public class PatientController {
    public PatientController(PatientPanel panel, AppData ad) {
        // Set up the patient search
        panel.setSearchButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = panel.getPatientSearchID();
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
        });
    }
}
