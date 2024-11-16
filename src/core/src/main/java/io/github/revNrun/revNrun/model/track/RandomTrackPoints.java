package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * <p>
 * RandomTrackPoints generates the points of the track.
 * All points are automatically generated when an instance is created.
 * There are three types of points, all of them can be returned with their specific getter method.
 * </p>
 * <p>
 * Initial points: These points could be compared to a 'track idea' or the track's 'general shape'. These
 * points randomly determine the size of the track and the number of control points to create curves.
 * </p>
 * <p>
 * Base points: These are a random distortion of the initial points, they're used as control points to generate
 * the curves. The distortion from initial points is made with one or several SimplexNoise iterations.
 * </p>
 * <p>
 * Points: These are the final points, the track with curves. It's the result of applying smooth interpolation
 * to the base points.
 * </p>
 */
public class RandomTrackPoints {
    // The following values determine the randomness of the track
    private static final int MIN_NUM_INITIAL_POINTS = 10;   // Minimum number of initial points
    private static final int MAX_NUM_INITIAL_POINTS = 40;   // Maximum number of initial points, can't be the same as MIN_NUM_INITIAL_POINTS
    private static final float MIN_RADIUS = 200;            // Minimum radius for the track
    private static final float MAX_RADIUS = 250;            // Maximum radius, can't be the same as MIN_RADIUS
    private static final float NOISE_SCALE = 0.6f;          // Noise scale: values can go from 0 to 1
    private static final int MIN_NUM_OCTAVES = 1;           // Minimum number of octaves to calculate the distortion of the base points, minimum 1
    private static final int MAX_NUM_OCTAVES = 4;           // Maximum number of octaves, can't be the same as MIN_NUM_OCTAVES
    private static final int MIN_NOISE_ITERATIONS = 1;      // Minimum number of times the base points are distorted from the previous distortion
    private static final int MAX_NOISE_ITERATIONS = 8;      // Maximum number of times the base points are distorted from the previous distortion, can't be the same as MIN_NOISE_ITERATIONS

    // The following values are the final ones, calculated randomly in generateRandomness()
    private int numInitialPoints;
    private float radius;
    private static int octaves;
    private static int noiseIterations;

    private static final float TWO_PI = (float) (2 * Math.PI);
    private final Random RANDOM = new Random();

    private final List<Vector2> initialPoints;
    private List<Vector2> basePoints;
    private final List<Vector2> points;

    /**
     * RandomTrackPoints constructor generates the random points of the track automatically when called.
     */
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

    /**
     * generateRandomness randomly calculates the final values of numInitialPoints, radius, octaves and noiseIterations,
     * all them based on the declared constants. octaves and noiseIterations are calculated with the same random value as
     * numInitialPoints, so they are proportional to it. numInitialPoints is calculated with the mean of the random value used
     * to calculate the radius and another new random value. The purpose of this new random value calculation is to get
     * a semi relation between radius and numInitialPoints, so big radius can get a medium or big quantity of points, but
     * not too few, and a small radius can get a small or medium quantity of points, but never too much.
     */
    private void generateRandomness() {
        float random = RANDOM.nextFloat();

        radius = random * (MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;

        random = (random + RANDOM.nextFloat()) / 2;

        numInitialPoints = Math.round(random * (MAX_NUM_INITIAL_POINTS - MIN_NUM_INITIAL_POINTS) + MIN_NUM_INITIAL_POINTS);
        octaves = Math.round(random * (MAX_NUM_OCTAVES - MIN_NUM_OCTAVES) + MIN_NUM_OCTAVES);
        noiseIterations = Math.round(MAX_NOISE_ITERATIONS - (random * (MAX_NOISE_ITERATIONS - MIN_NOISE_ITERATIONS)));
    }

    /**
     * Generates the initial points. This method is called from by the constructor and fills the initialPoints array.
     * Right now, initial points are just a circle determined by the random radius and the random number of initial points.
     */
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

    /**
     * generateBasePoints applies SimplexNoise to the given points. It's used to generate the base points
     * from the previous base points list.
     * @param controlPoints Vector2 list containing all points to be distorted by SimplexNoise.
     * @return Vector2 List containing only the generated points.
     */
    private List<Vector2> generateBasePoints(List<Vector2> controlPoints) {
        List<Vector2> points = new ArrayList<>();
        for (Vector2 controlPoint : controlPoints) {
            points.add(displacePoint(controlPoint));
        }
        return points;
    }

    /**
     * displacePoint applies SimplexNoise to a given point. It applies a given number of octaves (minimum 1).
     * Octaves are combined to generate a smoother result, so as more octaves we use, smoother and natural
     * results we'll get.
     * @param initialPoint Vector2 point to be distorted.
     * @return Vector2 distorted point.
     */
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

    /**
     * Given the base points, generates the final points using the TrackSmoothing class.
     * The method adds the base control points and their generated interpolations to the points list.
     */
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
