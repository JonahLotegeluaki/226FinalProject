package controller;

import java.awt.event.*;
import gui.PatientPanel;
import patient.*;
import structures.AppData;

public class PatientController {
    public PatientController(PatientPanel panel, AppData ad) {
        panel.setSearchButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = panel.getPatientSearchID();
                System.out.printf("Searching for ID: %s\n", id); // DEBUG
                if (ad.registry == null) {
                    panel.setSearchResultText("Cannot find patient \"" + id + "\": Registry is empty");
                    return;
                }
                Patient p = ad.registry.getPatientByID(id);
                if (p == null)
                    panel.setSearchResultText("Cannot find patient \"" + id + "\": Registry is empty");
                else
                    panel.setSearchResultText(p.toString());
            }
        });
    }
}
