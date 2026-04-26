import javax.swing.*;

void main() {

    //Feature A

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

    //Feature B
    double[] sound = StdAudio.read(filename);
    StdAudio.play(sound);
    StdAudio.drain();

    //Feature C
    StdDraw.setCanvasSize(1000, 300);
    StdDraw.setXscale(0, numBars);
    StdDraw.setYscale(-1, 1);

    int groupSize = sound.length / numBars;

    for (int i = 0; i < numBars; i++) {
        double max = 0;

        for (int j = 0; j < groupSize; j++) {
            int currentIndex = i * groupSize + j;
            double currentSample = Math.abs(sound[currentIndex]);

            if (currentSample > max) {
                max = currentSample;
            }
        }
        double x = i + 0.5;
        double y = 0;
        double halfWidth = 0.4;
        double halfHeight = max;

        StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
    }

    StdAudio.play(sound);
    StdAudio.drain();

}
