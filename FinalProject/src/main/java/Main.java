import controller.NavigationController;
import gui.NavigationPanel;
import structures.HospitalGraph;

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
    private static HospitalGraph graph;
    private static NavigationController ctNavigation;

    public static void main(String[] args) {
        // Set up window and add event listeners for menu bar items
        // The controller classes will handle events for the panels
        window = new MainWindow();
        ad = new AppData();
        graph = new HospitalGraph();

        System.out.println(System.getProperty("user.dir"));


        try {
            graph.loadGraph("FinalProject/src/main/java/resources/GraphData.txt");
        } catch (IOException e) {
            window.errorDialog("Graph Load Error", e.getMessage());
        }
        window.getOpenMenuItem().addActionListener(e -> {
            ad = openSession();
            reloadControllers();
        });
        window.getSaveMenuItem().addActionListener(e -> saveSession());
        window.getSaveAsMenuItem().addActionListener(e -> saveSessionAs());
        window.getExitMenuItem().addActionListener(e -> System.exit(0));

        ctPatient = new PatientController(window, (PatientPanel) window.getPanel(1), ad);
        ctNavigation = new NavigationController(
        (NavigationPanel) window.getPanel(3), graph);
    }

    static AppData openSession() {
        String filename = window.getFilenameOpen("Open a session", window);
        try {
            AppData ad = AppData.loadFromFile(filename);
            return ad;
        } catch (IOException e) {
            window.errorDialog("File Load Error", "Failed to load file \"" + filename + "\": " + e.getMessage());
            return null;
        }
    }

    static void saveSession() {

    }

    static void saveSessionAs() {

    }

    // When new data is loaded (after opening a session),
    // the controllers need to be regenerated with references
    // to the new registry, roster, etc.
    static void reloadControllers() {
        ctPatient = new PatientController(window, (PatientPanel) window.getPanel(1), ad);
    }
}