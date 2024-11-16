package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO javadocs comments
public class RandomTrackPoints {
    private static final int MIN_NUM_INITIAL_POINTS = 10;
    private static final int MAX_NUM_INITIAL_POINTS = 40;
    private static final float MIN_RADIUS = 100;
    private static final float MAX_RADIUS = 150;
    private static final float NOISE_SCALE = 0.6f;
    private static final int MIN_NUM_OCTAVES= 1;
    private static final int MAX_NUM_OCTAVES = 4;
    private static final int MIN_NOISE_ITERATIONS = 1;
    private static final int MAX_NOISE_ITERATIONS = 8;
    private static final float TWO_PI = (float) (2 * Math.PI);

    private final Random RANDOM = new Random();
    private int numInitialPoints;
    private float radius;
    private static int octaves;
    private static int noiseIterations;
    private final List<Vector2> initialPoints;
    private List<Vector2> basePoints;
    private final List<Vector2> points;

    public RandomTrackPoints() {
        initialPoints = new ArrayList<>();
        basePoints = new ArrayList<>();
        points = new ArrayList<>();

        // Generate the values that determines the initial and base points randomness
        this.generateRandomness();

        // Generate the initial points (basics)
        // Base points (randomized by SimplexNoise) are also generated from the initial points (first noise iteration)
        this.generateInitialPoints();

        // Generate again the base points, from the past base points, so there's more even more noise (second and higher noise iterations)
        for (int i = 0; i < noiseIterations - 1; i++) {
            basePoints = generateBasePoints(basePoints);
        }

        // Add the first initialPoint as the last point too
        initialPoints.add(initialPoints.get(0));
        // Add the first basePoint as the last point too, we cannot recalculate it or the end would probably become a different point than the start one
        basePoints.add(basePoints.get(0));

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

    private void generateRandomness() {
        float random = RANDOM.nextFloat();

        numInitialPoints = Math.round(random * (MAX_NUM_INITIAL_POINTS - MIN_NUM_INITIAL_POINTS) + MIN_NUM_INITIAL_POINTS);
        radius = random * (MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
        octaves = Math.round(random * (MAX_NUM_OCTAVES - MIN_NUM_OCTAVES) + MIN_NUM_OCTAVES);
        noiseIterations = Math.round(MAX_NOISE_ITERATIONS - (random * (MAX_NOISE_ITERATIONS - MIN_NOISE_ITERATIONS)));

    }

    private void generateInitialPoints() {
        float angle, xBase, yBase;
        Vector2 initialPoint;

        for (int i = 0; i < numInitialPoints; i++) {
            angle = TWO_PI * i / numInitialPoints;
            xBase = (float) (Math.cos(angle) * radius);
            yBase = (float) (Math.sin(angle) * radius);

            initialPoint = new Vector2(xBase, yBase);

            initialPoints.add(initialPoint);
            basePoints.add(displacePoint(initialPoint));
        }
    }

    private List<Vector2> generateBasePoints(List<Vector2> controlPoints) {
        List<Vector2> points = new ArrayList<>();
        for(Vector2 controlPoint : controlPoints) {
            points.add(displacePoint(controlPoint));
        }
        return points;
    }

    private Vector2 displacePoint(Vector2 initialPoint) {
        float xBase = initialPoint.getX(), yBase = initialPoint.getY();
        float xNoise = 0, yNoise = 0;
        float freq, amplitude;
        float rndSeed = RANDOM.nextFloat() * 1000;

        // Sum of 3 noise octaves
        for (int octave = 0; octave < octaves; octave++) {
            freq = (float) Math.pow(2, octave);
            amplitude = (float) Math.pow(0.5, octave);

            xNoise += SimplexNoise.noise(
                (xBase * NOISE_SCALE + rndSeed) * freq,
                (yBase * NOISE_SCALE) * freq
            ) * amplitude;

            yNoise += SimplexNoise.noise(
                (yBase * NOISE_SCALE + rndSeed) * freq,
                (xBase * NOISE_SCALE) * freq
            ) * amplitude;
        }

        // Apply the noise with a variable distortionFactor
        float distortionFactor = 0.15f; // Need to find the best value between 0 and 1

        return new Vector2(
            xBase + xNoise * radius * distortionFactor,
            yBase + yNoise * radius * distortionFactor
        );
    }

    private void generatePoints() {
        /* As Catmull-Rom implementation uses first and last control points as direction guides and
        doesn't generate interpolations for their segments, we add to the basePoints the second last and second
        control points at the beginning and at the end of the list, respectively, so the final curve will
        be well-closed and smooth. First point is already added to the list as part of the segments that need to be
        smoothed. */
        List<Vector2> controlPoints = new ArrayList<>();
        controlPoints.add(basePoints.get(basePoints.size() - 2)); // size()-1 is the duplicate of the start point
        controlPoints.addAll(basePoints);
        assert controlPoints.getLast() == basePoints.get(0) : "Start point has not been added as the last point of the track";
        controlPoints.add(basePoints.get(1));

        points.addAll(TrackSmoothing.computeCatmullRom(controlPoints, 1, true));
    }

    // TEST GETTERS
    public float getRadius() {
        return radius;
    }

    public static int getMaxNumInitialPoints() {
        return MAX_NUM_INITIAL_POINTS;
    }

    public static int getMinNumInitialPoints() {
        return MIN_NUM_INITIAL_POINTS;
    }

    public static float getMinRadius() {
        return MIN_RADIUS;
    }

    public static float getMaxRadius() {
        return MAX_RADIUS;
    }
}
