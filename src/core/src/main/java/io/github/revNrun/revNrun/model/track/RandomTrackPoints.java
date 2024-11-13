package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTrackPoints {
    private static final int MIN_NUM_POINTS = 50;
    private static final int MAX_NUM_POINTS = 200;
    private static final float MIN_RADIUS = 100;
    private static final float MAX_RADIUS = 300;
    private static final float SCALE = 0.5f;
    private static final float TWO_PI = (float) (2 * Math.PI);

    private final Random RANDOM = new Random();
    private final int numPoints;
    private final float radius;
    private final List<Vector2> points = new ArrayList<>();

    public RandomTrackPoints() {
        numPoints = MIN_NUM_POINTS + RANDOM.nextInt((MAX_NUM_POINTS - MIN_NUM_POINTS));
        radius = MIN_RADIUS + RANDOM.nextFloat((MAX_RADIUS - MIN_RADIUS));
        this.generatePoints();
    }

    public List<Vector2> getTrackPoints() {
        return points;
    }

    public Vector2 get(int position){
        return points.get(position);
    }

    private void generatePoints() {

        float rndSeed = RANDOM.nextFloat() * 1000;

        float angle, xBase, yBase, xNoise, yNoise, freq, amplitude, distorsionFactor, x, y;

        for (int i = 0; i < numPoints; i++) {
            angle = TWO_PI * i / numPoints;
            xBase = (float) (Math.cos(angle) * radius);
            yBase = (float) (Math.sin(angle) * radius);

            xNoise = 0;
            yNoise = 0;

            // Sum of 3 noise octaves
            for (int octave = 0; octave < 3; octave++) {
                freq = (float) Math.pow(2, octave);
                amplitude = (float) Math.pow(0.5, octave);

                xNoise += SimplexNoise.noise(
                    (xBase * SCALE + rndSeed) * freq,
                    (yBase * SCALE) * freq
                ) * amplitude;

                yNoise += SimplexNoise.noise(
                    (yBase * SCALE + rndSeed) * freq,
                    (xBase * SCALE) * freq
                ) * amplitude;
            }

            // Apply the noise with a variable distorsionFactor
            distorsionFactor = 0.15f; // Need to find the best value between 0 and 1
            x = xBase + xNoise * radius * distorsionFactor;
            y = yBase + yNoise * radius * distorsionFactor;

            points.add(new Vector2(x, y));
        }
    }

    // Test getters
    public int getNumPoints() {
        return numPoints;
    }
    public float getRadius() {
        return radius;
    }
    public static int getMaxNumPoints() {
        return MAX_NUM_POINTS;
    }
    public static int getMinNumPoints() {
        return MIN_NUM_POINTS;
    }
    public static float getMinRadius() {
        return MIN_RADIUS;
    }
    public static float getMaxRadius() {
        return MAX_RADIUS;
    }
}
