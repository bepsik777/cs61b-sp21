package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;


public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        GuitarString[] arrOfString = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < arrOfString.length; i += 1) {
            double frequency = 440 * Math.pow(2.0, ((i - 24.0) / 12.0));
            arrOfString[i] = new GuitarString(frequency);
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int indexOfKey = KEYBOARD.indexOf(key);
                if (indexOfKey >= 0) {
                    arrOfString[indexOfKey].pluck();
                }
            }

            /* Compute the superposition of samples */
            double sample = 0;
            for (GuitarString guitarString : arrOfString) {
                sample += guitarString.sample();
                guitarString.tic();
            }
            StdAudio.play(sample);
        }
    }

}
