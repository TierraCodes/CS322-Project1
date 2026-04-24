import javax.swing.*;

void main() {

    String input = JOptionPane.showInputDialog(null,
            "Enter [filename] [number of bars]:",
            "Sound Visualizer Input",
            JOptionPane.QUESTION_MESSAGE);

    if (input == null || input.trim().isEmpty()) {
        IO.println("No input provided. Exiting.");
        return;
    }

    String[] parts = input.trim().split("\\s+");

    if (parts.length < 2) {
        JOptionPane.showMessageDialog(null, "Error: You must provide both a filename and the number of bars.");
        return;
    }

    String filename = parts[0];
    int numBars;

    try {
        numBars = Integer.parseInt(parts[1]);

        if (numBars <= 0 || numBars > 1000) {
            throw new NumberFormatException("Invalid bar count.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: Number of bars must be a positive integer (1-1000).");
        return;
    }

    IO.println("Filename: " + filename);
    IO.println("Bars: " + numBars);

}
