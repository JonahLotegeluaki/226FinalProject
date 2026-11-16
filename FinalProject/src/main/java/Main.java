import gui.MainWindow;
// The AppData class stores all the data for a session.
// It is a single object that can be easily serialized for storage.
import structures.AppData;

public class Main {
    public static void main(String[] args) {
        // Set up window and add event listeners for menu bar items
        // The controller classes will handle events for the panels
        MainWindow window = new MainWindow();
        window.getOpenMenuItem().addActionListener(e -> openSession());
        window.getSaveMenuItem().addActionListener(e -> saveSession());
        window.getSaveAsMenuItem().addActionListener(e -> saveSessionAs());
        window.getExitMenuItem().addActionListener(e -> System.exit(0));
    }

    static void openSession() {

    }

    static void saveSession() {

    }

    static void saveSessionAs() {

    }
}