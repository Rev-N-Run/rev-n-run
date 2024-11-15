package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//TODO javadocs comments, executar TrackSmoothing passant-li el primer node com a també últim, afegir getters per BasicPoints, RandomPoints, i FinalPoints
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
    private final List<Vector2> initialPoints = new ArrayList<>();
    private final List<Vector2> basePoints = new ArrayList<>();
    private final List<Vector2> points = new ArrayList<>();

    public RandomTrackPoints() {
        numPoints = MIN_NUM_POINTS + RANDOM.nextInt((MAX_NUM_POINTS - MIN_NUM_POINTS));
        radius = MIN_RADIUS + RANDOM.nextFloat((MAX_RADIUS - MIN_RADIUS));

        // Generate the initial points (basics) and the base points (randomized by SimplexNoise)
        this.generateInitialPoints();

        // Add the first initialPoint as the last too
        initialPoints.add(initialPoints.get(0));
        // Add the first basePoint as the last too, we cannot recalculate it or the end would probably become a different point than the start one
        this.generateBasePoint(basePoints.get(0));

        // Generate the points (final ones, smoothed) from the base points
        this.generatePoints();
    }

    public List<Vector2> getInitialPoints() {
        return initialPoints;
    }

    public List<Vector2> getBasePoints() {
        return basePoints;
    }

    public List<Vector2> getPoints() {
        return points;
    }

    private void generateInitialPoints() {
        float angle, xBase, yBase;
        Vector2 initialPoint;

        for (int i = 0; i < numPoints; i++) {
            angle = TWO_PI * i / numPoints;
            xBase = (float) (Math.cos(angle) * radius);
            yBase = (float) (Math.sin(angle) * radius);

            initialPoint = new Vector2(xBase, yBase);

            initialPoints.add(initialPoint);
            this.generateBasePoint(initialPoint);
        }
    }

    private void generateBasePoint(Vector2 initialPoint) {
        float xBase = initialPoint.getX(), yBase = initialPoint.getY();
        float xNoise = 0, yNoise = 0;
        float freq, amplitude;
        float rndSeed = RANDOM.nextFloat() * 1000;

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
        float distorsionFactor = 0.15f; // Need to find the best value between 0 and 1

        basePoints.add(new Vector2(
            xBase + xNoise * radius * distorsionFactor,
            yBase + yNoise * radius * distorsionFactor
        ));
    }

    private void generatePoints() {
        Vector2 actual, next;
        List<Vector2> path;
        int numSamples;

        // Loop on the basePoints to get the distance between consecutive points
        // actual is initialized outside loop and modified at the end of the loop to avoid a List.get() per loop.
        // Apply smoothing with computeCatmullRom to every pair of consecutive points, with their distance as the number of samples (interpolated points).
        actual = basePoints.get(0);
        for(int i=0; i<basePoints.size()-1; i++) {
            next = basePoints.get(i+1);
            path = new ArrayList<>(Arrays.asList(actual, next));

            numSamples =  (int) actual.distance(next);

            path = TrackSmoothing.computeCatmullRom(path, numSamples);
            points.addAll(path);

            actual = next;
        }
    }

    // TEST GETTERS
    public float getRadius() {
        return radius;
    }

    public static int getMaxNumInitialPoints() {
        return MAX_NUM_POINTS;
    }

    public static int getMinNumInitialPoints() {
        return MIN_NUM_POINTS;
    }

    public static float getMinRadius() {
        return MIN_RADIUS;
    }

    public static float getMaxRadius() {
        return MAX_RADIUS;
    }
}
