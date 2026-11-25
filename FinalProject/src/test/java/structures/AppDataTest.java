package structures;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import patient.Patient;
import patient.PatientRegistry;
import staff.Seniority;
import staff.StaffMember;
import staff.StaffRoster;

public class AppDataTest {
    // Saves an AppData, and then loads it
    // Checks that the registry is the same
    @Test
    void storeLoadTest() throws IOException {
        AppData ad = new AppData();
        ad.registry = new PatientRegistry();
        ad.registry
                .addPatient(new Patient("Patient 0", "Michael", "Johnson", 21, "Tired", 2, "None", "R0", 3, "ID123"));
        AppData.writeToFile(ad, "test.json");
        AppData ad2 = AppData.loadFromFile("test.json");
        assertEquals(ad.registry.getPatientRegistry()[0].toString(), ad2.registry.getPatientRegistry()[0].toString());
    }

    // Writes the sample data to a file
    // Used for application demo
    @Test
    void writeSampleData() throws IOException {
        PatientRegistry reg = new PatientRegistry();
        for (Patient p : new Patient[] {
                new Patient("P00", "John", "Johnson", 32, "Back Pain", 3, "None", "None", 12, "ID128312"),
                new Patient("P01", "Michael", "Michaelson", 33, "Headache", 4, "None", "None", 12, "ID131"),
                new Patient("P02", "Jeremy", "Jeremyson", 34, "Heart Attack", 1, "None", "ER01", 12, "ID131"),
                new Patient("P03", "Bob", "Bobson", 35, "Nausea", 2, "None", "None", 12, "ID128"),
                new Patient("P04", "Ronald", "Ronaldson", 36, "Fractured Leg", 2, "None", "None", 12, "ID8312"),
                new Patient("P05", "Jim", "Jimson", 37, "Scrape", 4, "None", "None", 12, "ID1831"),
                new Patient("P06", "Jack", "Jackson", 38, "Bump", 4, "None", "None", 12, "ID1812"),
                new Patient("P07", "Luke", "Lukeson", 39, "Gunshot Wound", 1, "None", "ER02", 12, "ID1282"),
                new Patient("P08", "Nathan", "Nathanson", 40, "Coma", 2, "None", "None", 12, "ID1312"),
                new Patient("P09", "Ben", "Benson", 41, "Loves the Hospital", 4, "None", "Waiting Room", 12,
                        "ID12"),
        })
            reg.addPatient(p);

        StaffRoster ros = new StaffRoster();
        for (StaffMember s : new StaffMember[] {
                new StaffMember("S01", "Dr. Doctor", Seniority.ATTENDING),
                new StaffMember("S02", "Nurse Nancy", Seniority.NURSE),
                new StaffMember("S03", "Resident Renold", Seniority.RESIDENT),
                new StaffMember("S04", "Tech Tom", Seniority.TECH),
        })
            ros.addStaff(s);

        AppData ad = new AppData();
        ad.registry = reg;
        ad.roster = ros;
        AppData.writeToFile(ad, "sample_data.json");
    }
}
