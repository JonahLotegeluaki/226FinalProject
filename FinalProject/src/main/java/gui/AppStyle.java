package gui;

import java.awt.Font;
import javax.swing.*;

/// This class provides helper functions for creating a uniform style across the app.
/// It also includes scaling methods
public class AppStyle {

    // Makes a label have a default size
    public static JLabel styleLabel(JLabel l) {
        l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        return l;
    }
}
