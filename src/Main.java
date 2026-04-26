import javax.swing.*;

void main() {

    String input = JOptionPane.showInputDialog(null,
            "Enter [filename] [number of bars]:",
            "Sound Visualizer Input",
            JOptionPane.QUESTION_MESSAGE);

    if (input == null || input.trim().isEmpty()) {
        IO.println("No input provided.");
        return;
    }

    String[] parts = input.trim().split("\\s+");

    if (parts.length < 2) {
        JOptionPane.showMessageDialog(null, "You must provide both a filename and the number of bars.");
        return;
    }

    String filename = parts[0];
    int numBars;

    if (filename.contains("..")){
        JOptionPane.showMessageDialog(null, "Security Error: Directory traversal or absolute paths are not allowed.");
        return;
    }

    if (!filename.toLowerCase().endsWith(".wav")) {
        JOptionPane.showMessageDialog(null, "Error: File must be a .wav format.");
        return;
    }

    try {
        numBars = Integer.parseInt(parts[1]);

        if (numBars <= 0 || numBars > 1000) {
            throw new NumberFormatException("Invalid bar count.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Number of bars must be a positive integer (1-1000).");
        return;
    }

    IO.println("Filename: " + filename);
    IO.println("Bars: " + numBars);

    double[] sound = StdAudio.read(filename);

    StdDraw.setCanvasSize(1000, 300);
    StdDraw.setXscale(0, numBars);
    StdDraw.setYscale(-1, 1);
    StdDraw.enableDoubleBuffering();

    int groupSize = sound.length / numBars;

    for (int i = 0; i < numBars; i++) {
        int start = i * groupSize;
        int end = Math.min(start + groupSize, sound.length);

        double[] chunk = java.util.Arrays.copyOfRange(sound, start, end);
        double max = 0;

        for (double sample : chunk) {
            double currentSample = Math.abs(sample);
            if (currentSample > max) {
                max = currentSample;
            }
        }
        double x = i + 0.5;
        double y = 0;
        double halfWidth = 0.4;
        double halfHeight = max;

        StdDraw.filledRectangle(x, y, halfWidth, halfHeight);

        StdDraw.show();
        StdAudio.play(chunk);
    }

    StdAudio.drain();
}
