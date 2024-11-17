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
     * @param other the other vector. Must not be {@code null}.
     * @return the angle between the vectors in radians, in the range (-π, π].
     * @throws NullPointerException if {@code other} is null.
     */
    public float angleBetween(Vector2 other) {
        if (other == null) throw new NullPointerException("Vector2 cannot be null.");
        float dx = other.x - this.x;
        float dy = other.y - this.y;
        return (float) Math.atan2(dy, dx);
    }
}
