void main() {
    System.out.println("Hello World!");
    double[] sound = StdAudio.read("./assets/cardinal_trim.wav");
    StdAudio.play(sound);
    StdAudio.drain();

    int start = 180000;
    int end = 330000;
    double[] short_sound = Arrays.copyOfRange(sound, start, end);

    StdAudio.play(short_sound);
    StdAudio.drain();

}
