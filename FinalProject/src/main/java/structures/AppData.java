package structures;

import patient.PatientRegistry;
import staff.StaffRoster;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class AppData {
    public PatientRegistry registry;
    public StaffRoster roster;
    public HospitalGraph graph;

    public AppData() {
        registry = null;
        roster = null;
        graph = null;
    }

    // Expects a JSON file to contain data for an 'AppData'
    public static AppData loadFromFile(String filename) throws IOException {
        Gson gson = new Gson();
        try (FileReader r = new FileReader(filename)) {
            return gson.fromJson(r, AppData.class);
        }
    }

    // Saves AppData to a file
    public static boolean writeToFile(AppData data, String filename) throws IOException {
        Gson gson = new Gson();
        try (FileWriter w = new FileWriter(filename)) {
            gson.toJson(data, w);
        }
        return true;
    }
}
