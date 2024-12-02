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

    public float getY() {
        return y;
    }

    /**
     * Calculates the Euclidean distance between this vector and another vector.
     *
     * <p>Preconditions:</p>
     * <ul>
     *   <li> {@code v} must not be {@code null}.</li>
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
     * Subtracts another vector from this vector.
     * @param other The vector to subtract.
     * @return A new vector representing the difference.
     */
    public Vector2 sub(Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector2 cannot be null.");
        }
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    /**
     * Calculates the length (magnitude) of the vector.
     * @return The vector's length
     */
    private float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Normalizes the vector (creates a unit vector in the same direction).
     * @return A new normalized vector.
     */
    public Vector2 nor() {
        float len = length();
        if (len == 0) return new Vector2(0, 0);
        return new Vector2(x / len, y / len);
    }

    /**
     * Creates a copy of this vector.
     * @return A new vector with the same coordinates.
     */
    public Vector2 cpy() {
        return new Vector2(x, y);
    }

    /**
     * Adds another vector to this vector.
     * @param other The vector to add.
     * @return A new vector representing the sum.
     */
    public Vector2 add(Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector2 cannot be null.");
        }
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    /**
     * Scales the vector by a scalar value.
     * @param scalar The value to multiply the vector by.
     * @return A new vector scaled by the given value.
     */
    public Vector2 scl(float scalar) {
        return new Vector2(x * scalar, y * scalar);
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
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null.");
        } else if (Float.isNaN(other.getX()) || Float.isNaN(other.getY())) {
            throw new IllegalArgumentException("Vector cannot contain a NaN value.");
        } else if (Float.isInfinite(other.getX()) || Float.isInfinite(other.getY())) {
            throw new IllegalArgumentException("Vector cannot contain an Infinite value.");
        }

        if (Float.isNaN(this.getX()) || Float.isNaN(this.getY())) {
            throw new IllegalArgumentException("Vector cannot contain a NaN value.");
        } else if (Float.isInfinite(this.getX()) || Float.isInfinite(this.getY())) {
            throw new IllegalArgumentException("Vector cannot contain an Infinite value.");
        }

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
        if (p == null || q == null || r == null) {
            throw new IllegalArgumentException("Vector2 cannot be null.");
        }

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
        if (p == null || q == null || r == null) {
            throw new IllegalArgumentException("Vector2 cannot be null.");
        }

        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
            q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    /**
     * Checks if a point is near a segment within a given minimum distance.
     *
     * @param p           the starting point of the segment
     * @param q           the point to check
     * @param r           the ending point of the segment
     * @param minDistance the minimum distance threshold
     * @return true if the point is near the segment, false otherwise
     */
    private static boolean nearSegment(Vector2 p, Vector2 q, Vector2 r, float minDistance) {
        if (p == null || r == null || q == null) {
            throw new IllegalArgumentException("Vector2 cannot be null.");
        }

        // Calculate the components of the line (p-r)
        double dx = r.x - p.x;
        double dy = r.y - p.y;

        // Handle degenerate case where p and r are the same point
        if (dx == 0 && dy == 0) {
            double distToP = Math.sqrt((q.x - p.x) * (q.x - p.x) + (q.y - p.y) * (q.y - p.y));
            return distToP <= minDistance;
        }

        // Calculate the perpendicular distance from q to the line p-r
        double numerator = Math.abs(dy * q.x - dx * q.y + r.x * p.y - r.y * p.x);
        double denominator = Math.sqrt(dx * dx + dy * dy);
        double perpDistance = numerator / denominator;

        // If the perpendicular distance is more than minDistance, point is too far
        if (perpDistance > minDistance) {
            return false;
        }

        // Check if the projection of q falls within the segment p-r
        double dot = (q.x - p.x) * dx + (q.y - p.y) * dy;
        double lengthSquared = dx * dx + dy * dy;
        double t = Math.max(0, Math.min(1, dot / lengthSquared));

        // Calculate the closest point on the segment
        double closestX = p.x + t * dx;
        double closestY = p.y + t * dy;

        // Calculate the actual distance to the closest point
        double actualDistance = Math.sqrt((q.x - closestX) * (q.x - closestX) +
            (q.y - closestY) * (q.y - closestY));

        return actualDistance <= minDistance;
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
     * This method considers general and special cases of intersection.
     *
     * @param p1 the first endpoint of the first segment
     * @param q1 the second endpoint of the first segment
     * @param p2 the first endpoint of the second segment
     * @param q2 the second endpoint of the second segment
     * @param minDistance the minimum distance threshold
     * @return true if the segments intersect, false otherwise
     */
    public static boolean doIntersect(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2, float minDistance) {
        if(p1 == null || q1 == null || p2 == null || q2 == null) {
            throw new IllegalArgumentException("Vector2 cannot be null.");
        }

        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case (formal intersection)
        if (o1 != o2 && o3 != o4) return true;

        // Special cases: not exactly an intersection, but one point is on a segment, so it's considered as
        // an intersection
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        // Special cases: a point very near to a segment is also considered as an intersection
        if (nearSegment(p1, p2, q1, minDistance)) return true;
        if (nearSegment(p1, q2, q1, minDistance)) return true;
        if (nearSegment(p2, p1, q2, minDistance)) return true;
        return nearSegment(p2, q1, q2, minDistance);

        // Segments do not intersect
    }

    public boolean equals (Vector2 other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    public boolean equalsRange(Vector2 that) {
        float range = 30;
        return (this.x + range >= that.x && that.x >= this.x - range) &&
            (this.y + range >= that.y && that.y >= this.y);
    }

    // TEST METHODS


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public static boolean onSegmentTest(Vector2 p, Vector2 q, Vector2 r) {
        return onSegment(p, q, r);
    }

    public static boolean nearSegmentTest(Vector2 p, Vector2 q, Vector2 r, float minDistance) {
        return nearSegment(p, q, r, minDistance);
    }

    public static int orientationTest(Vector2 p, Vector2 q, Vector2 r) {
        return orientation(p, q, r);
    }

    public float lengthTest() {
        return length();
    }
}
