import java.io.IOException;

import controller.PatientController;
import gui.MainWindow;
import gui.PatientPanel;
// The AppData class stores all the data for a session.
// It is a single object that can be easily serialized for storage.
import structures.AppData;

public class Main {

    private static MainWindow window;
    private static AppData ad;
    private static PatientController ctPatient;
    // If null, this will upon saving prompt the user to enter a save filename
    private static String sessionFilename;

    public static void main(String[] args) {
        sessionFilename = null;
        // Set up window and add event listeners for menu bar items
        // The controller classes will handle events for the panels
        window = new MainWindow();
        ad = new AppData();
        window.getOpenMenuItem().addActionListener(e -> {
            ad = openSession();
            reloadControllers();
        });
        window.getSaveMenuItem().addActionListener(e -> saveSession());
        window.getSaveAsMenuItem().addActionListener(e -> saveSessionAs());
        window.getExitMenuItem().addActionListener(e -> System.exit(0));

        ctPatient = new PatientController(window, (PatientPanel) window.getPanel(1), ad);
    }

    static AppData openSession() {
        String filename = window.getFilenameOpen("Open a session");
        try {
            AppData ad = AppData.loadFromFile(filename);
            // Set the session name to only the last part of the file path
            String[] steps = filename.split(System.getProperty("file.separator"));
            window.setSessionName(steps[steps.length - 1]);
            window.setSessionModified(false);
            return ad;
        } catch (IOException e) {
            window.errorDialog("File Load Error", "Failed to load file \"" + filename + "\": " + e.getMessage());
            return null;
        }
    }

    static void saveSession() {
        // If there is no filename already, then the user must provide one
        if (sessionFilename == null) {
            saveSessionAs();
            return;
        }

        // saveSessionAs() will point back to here if called
        try {
            AppData.writeToFile(ad, sessionFilename);
            window.setSessionModified(false);
        } catch (IOException e) {
            window.errorDialog("File Save Error", "Error saving file \"" + sessionFilename + "\": " + e.getMessage());
        }
    }

    static void saveSessionAs() {
        // Select a filename, and then call the save method
        sessionFilename = window.getFilenameSave("Save the Session");

        // Make sure it has the proper extension
        if (!sessionFilename.substring(sessionFilename.length() - 6, sessionFilename.length() - 1).equals(".json"))
            sessionFilename = sessionFilename + ".json";

        // Rename the window
        String[] steps = sessionFilename.split(System.getProperty("file.separator"));
        window.setSessionName(steps[steps.length - 1]);

        // Actually save
        saveSession();
    }

    // When new data is loaded (after opening a session),
    // the controllers need to be regenerated with references
    // to the new registry, roster, etc.
    static void reloadControllers() {
        ctPatient = new PatientController(window, (PatientPanel) window.getPanel(1), ad);
    }
}