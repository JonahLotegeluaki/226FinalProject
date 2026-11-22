package gui;

import javax.swing.*;
import java.awt.*;

// This is a class that shows placeholder text when the box is empty
// Otherwise it's exactly the same as the normal JTextField
public class TextBox extends JTextField {
    private String placeholder;

    public TextBox(String placeholder) {
        this.placeholder = placeholder;

        // Add constraints to the size of the box
        setPreferredSize(new Dimension(400, 40));
        setMaximumSize(new Dimension(1000, 40));
    }

    // Override the paintComponent method to display the placeholder text when empty
    @Override
    protected void paintComponent(Graphics g) {
        // Do whatever is normally supposed to happen
        super.paintComponent(g);

        // But do something special if empty
        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            Insets insets = getInsets();
            g2.drawString(
                    placeholder,
                    insets.left + 2,
                    getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2);
            g2.dispose();
        }
    }
}
