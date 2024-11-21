package io.github.revNrun.revNrun.model.vector;

/**
 * Represents a 2D vector with x and y components.
 * This class provides basic vector operations such as distance calculation
 * and angle computation between vectors.
 */
public class Vector2 {
    private float x;
    private float y;

    /**
     * Default constructor that initializes the vector to (0, 0).
     */
    public Vector2() {
    }

    /**
     * Constructs a vector with specified x and y components.
     *
     * @param x the x-coordinate of the vector.
     * @param y the y-coordinate of the vector.
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor that creates a new vector from an existing one.
     *
     * @param v the vector to copy. Must not be {@code null}.
     * @throws NullPointerException if {@code v} is null.
     */
    public Vector2(Vector2 v) {
        if (v == null) throw new NullPointerException("Vector2 cannot be null.");
        this.x = v.x;
        this.y = v.y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance between this vector and another vector.
     *
     * <p>Preconditions:</p>
     * <ul>
     *   <li>{@code v} must not be {@code null}.</li>
     * </ul>
     *
     * @param v the other vector. Must not be {@code null}.
     * @return the Euclidean distance between the two vectors.
     * @throws NullPointerException if {@code v} is null.
     */
    public float distance(Vector2 v) {
        if (v == null) throw new NullPointerException("Vector2 cannot be null.");
        float dx = v.getX() - this.getX();
        float dy = v.getY() - this.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Computes the angle (in radians) between this vector and another vector,
     * measured counterclockwise from this vector to the other.
     *
     * @param other the other vector. Must not be {@code null}.
     * @return the angle between the vectors in radians, in the range (-π, π].
     * @throws NullPointerException if {@code other} is null, has a Nan, or has an infinite value.
     */
    public float angleBetween(Vector2 other) {
        // Preconditions
        if (other == null) throw new IllegalArgumentException("Vector cannot be null.");
        else if (Float.isNaN(other.getX()) || Float.isNaN(other.getY())) throw new IllegalArgumentException("Vector cannot contain a NaN value.");
        else if (Float.isInfinite(other.getX()) || Float.isInfinite(other.getY())) throw new IllegalArgumentException("Vector cannot contain an Infinite value.");
        if (Float.isNaN(this.getX()) || Float.isNaN(this.getY())) throw new IllegalArgumentException("Vector cannot contain a NaN value.");
        else if (Float.isInfinite(this.getX()) || Float.isInfinite(this.getY())) throw new IllegalArgumentException("Vector cannot contain an Infinite value.");

        float dx = other.x - this.x;
        float dy = other.y - this.y;
        return (float) Math.atan2(dy, dx);
    }

    /**
     * Determines the orientation of three points (p, q, r).
     * The orientation can be:
     * <ul>
     *     <li>0: Collinear</li>
     *     <li>1: Clockwise</li>
     *     <li>2: Counterclockwise</li>
     * </ul>
     *
     * @param p the first point
     * @param q the second point
     * @param r the third point
     * @return 0 if the points are collinear, 1 if clockwise, and 2 if counterclockwise
     */
    private static int orientation(Vector2 p, Vector2 q, Vector2 r) {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (isZero(val)) return 0;  // Collinear
        return (val > 0) ? 1 : 2; // Clockwise or counterclockwise
    }

    /**
     * Checks if a point q lies on the line segment defined by points p and r.
     *
     * @param p the starting point of the segment
     * @param q the point to check
     * @param r the ending point of the segment
     * @return true if q lies on the segment, false otherwise
     */
    private static boolean onSegment(Vector2 p, Vector2 q, Vector2 r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
            q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    /**
     * Determines if a value is effectively zero, within a small epsilon threshold.
     *
     * @param val the value to check
     * @return true if the value is approximately zero, false otherwise
     */
    private static boolean isZero(double val) {
        return Math.abs(val) < 1e-10;
    }

    /**
     * Determines if two line segments intersect.
     * Each segment is defined by two endpoints: (p1, q1) and (p2, q2).
     * This method considers both general and special cases of intersection.
     *
     * @param p1 the first endpoint of the first segment
     * @param q1 the second endpoint of the first segment
     * @param p2 the first endpoint of the second segment
     * @param q2 the second endpoint of the second segment
     * @return true if the segments intersect, false otherwise
     */
    public static boolean doIntersect(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case (formal intersection)
        if (o1 != o2 && o3 != o4) return true;

        // Special cases (not exactly an intersection, but one point is on a segment, so it's considered as
        // an intersection)
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        // Segments do not intersect
        return false;
    }

    // TEST METHODS
    public static boolean onSegmentTest(Vector2 p, Vector2 q, Vector2 r) {
        return onSegment(p, q, r);
    }

    public static int orientationTest(Vector2 p, Vector2 q, Vector2 r) {
        return orientation(p, q, r);
    }
}
