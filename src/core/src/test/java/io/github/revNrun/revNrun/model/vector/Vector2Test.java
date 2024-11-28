package io.github.revNrun.revNrun.model.vector;

import org.junit.jupiter.api.Test;

import static io.github.revNrun.revNrun.model.vector.Vector2.*;
import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {
    private static final float EPSILON = 0.0001f;

    @Test
    void getX() {
        Vector2 a = new Vector2(3, 4);
        Vector2 b = new Vector2(0, 0);
        Vector2 c = new Vector2(-1, 0);
        Vector2 d = new Vector2(0, -1);
        Vector2 e = new Vector2(-20, -30);
        Vector2 f = new Vector2(e);
        Vector2 g = new Vector2();

        g.setX(2);

        assertEquals(3, a.getX());
        assertEquals(0, b.getX());
        assertEquals(-1, c.getX());
        assertEquals(0, d.getX());
        assertEquals(-20, e.getX());
        assertEquals(-20, f.getX());
        assertEquals(2, g.getX());
    }

    @Test
    void getY() {
        Vector2 a = new Vector2(3, 4);
        Vector2 b = new Vector2(0, 0);
        Vector2 c = new Vector2(-1, 0);
        Vector2 d = new Vector2(0, -1);
        Vector2 e = new Vector2(-20, -30);
        Vector2 f = new Vector2(e);
        Vector2 g = new Vector2();

        g.setY(4);

        assertEquals(4, a.getY());
        assertEquals(0, b.getY());
        assertEquals(0, c.getY());
        assertEquals(-1, d.getY());
        assertEquals(-30, e.getY());
        assertEquals(-30, f.getY());
        assertEquals(4, g.getY());
    }

    @Test
    public void testDistance(){
        Vector2 a = new Vector2(0, 0);
        Vector2 b = new Vector2(3, 4);
        Vector2 c = new Vector2(-8, -5);

        // Positive values
        assertEquals(a.distance(b), 5.0f);
        // Same values
        assertEquals(b.distance(b), 0.0f);
        // Negative values
        assertEquals(b.distance(c), 14.2126f, EPSILON);
    }

    @Test
    public void testAngleBetween_PositiveXAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(1, 0);
        float angle = vector.angleBetween(other);
        assertEquals(0.0, angle, EPSILON, "Angle with positive X axis should be 0");
    }

    @Test
    public void testAngleBetween_PositiveYAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0, 1);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI / 2, angle, EPSILON, "Angle with positive Y axis should be PI/2");
    }

    @Test
    public void testAngleBetween_NegativeXAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(-1, 0);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI, angle, EPSILON, "Angle with negative X axis should be PI");
    }

    @Test
    public void testAngleBetween_NegativeYAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0, -1);
        float angle = vector.angleBetween(other);
        assertEquals(-Math.PI / 2, angle, EPSILON, "Angle with negative Y axis should be -PI/2");
    }

    @Test
    public void testAngleBetween_SamePoint() {
        Vector2 vector = new Vector2(1, 1);
        Vector2 other = new Vector2(1, 1);
        float angle = vector.angleBetween(other);
        // This test should verify if the method properly handles identical points
        // It could throw an exception or return 0, depending on desired implementation
        assertEquals(0.0, angle, EPSILON, "Angle between identical points should be 0");
    }

    @Test
    public void testAngleBetween_ArbitraryPoints() {
        Vector2 vector = new Vector2(1, 1);
        Vector2 other = new Vector2(4, 5);
        float angle = vector.angleBetween(other);
        // The correct calculation should be:
        float expectedAngle = (float) Math.atan2(5 - 1, 4 - 1);
        assertEquals(expectedAngle, angle, EPSILON,
            "Angle between points (1,1) and (4,5) should be " + expectedAngle);
    }

    // Additional recommended tests
    @Test
    public void testAngleBetween_DiagonalQuadrant1() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(1, 1);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI / 4, angle, EPSILON,
            "45-degree angle in first quadrant should be PI/4");
    }

    @Test
    public void testAngleBetween_NonZeroOrigin() {
        Vector2 vector = new Vector2(2, 2);
        Vector2 other = new Vector2(4, 4);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI / 4, angle, EPSILON,
            "Angle should be the same regardless of vector origin");
    }

    // Boundary output values tests
    @Test
    public void testAngleBetween_NearPi() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(-1, 0.0001f);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI - EPSILON, angle, EPSILON,
            "Angle should be very close to but less than PI");
    }

    @Test
    public void testAngleBetween_NearMinusPiOver2() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0.0001f, -1);
        float angle = vector.angleBetween(other);
        assertEquals(-Math.PI/2 + EPSILON, angle, EPSILON,
            "Angle should be very close to but greater than -PI/2");
    }

    // Input boundary values tests
    @Test
    public void testAngleBetween_VeryLargeValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.MAX_VALUE, Float.MAX_VALUE);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be PI/4 even with very large values");
    }

    @Test
    public void testAngleBetween_VerySmallValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.MIN_VALUE, Float.MIN_VALUE);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be PI/4 even with very small values");
    }

    @Test
    public void testAngleBetween_InfiniteValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        assertThrows(IllegalArgumentException.class, () -> vector.angleBetween(other),
            "Method should throw IllegalArgumentException for infinite values");
    }

    @Test
    public void testAngleBetween_NaNValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.NaN, 1);
        assertThrows(IllegalArgumentException.class, () -> vector.angleBetween(other),
            "Method should throw IllegalArgumentException for NaN values");
    }


    @Test
    public void testAngleBetween_ZeroVector() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0, 0);
        assertEquals(0, vector.angleBetween(other));
    }

    @Test
    public void testAngleBetween_VeryClosePoints() {
        Vector2 vector = new Vector2(1, 1);
        Vector2 other = new Vector2(1 + EPSILON, 1 + EPSILON);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be stable for very close points");
    }

    @Test
    public void testAngleBetween_VeryDistantPoints() {
        Vector2 vector = new Vector2(-1000000, -1000000);
        Vector2 other = new Vector2(1000000, 1000000);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be stable for very distant points");
    }

    @Test
    public void testAngleBetween_ExtremelySmallDistance() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.MIN_NORMAL, 0);
        float angle = vector.angleBetween(other);
        assertEquals(0, angle, EPSILON,
            "Angle should be stable for extremely small distances");
    }

    @Test
    public void testAngleBetween_OppositeQuadrants() {
        Vector2 vector = new Vector2(-1, -1);
        Vector2 other = new Vector2(1, 1);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be consistent when points are in opposite quadrants");
    }

    @Test
    void testOrientation() {
        Vector2 p = new Vector2(0, 0);
        Vector2 q = new Vector2(1, 1);
        Vector2 r1 = new Vector2(2, 2);  // Collinear
        Vector2 r2 = new Vector2(2, 0);  // Clockwise
        Vector2 r3 = new Vector2(0, 2);  // Counterclockwise

        assertEquals(0, orientationTest(p, q, r1), "Collinear points should return 0");
        assertEquals(1, orientationTest(p, q, r2), "Clockwise points should return 1");
        assertEquals(2, orientationTest(p, q, r3), "Counterclockwise points should return 2");
    }

    @Test
    void testOnSegment() {
        Vector2 p = new Vector2(0, 0);
        Vector2 q = new Vector2(5, 5);
        Vector2 r1 = new Vector2(3, 3);  // On segment
        Vector2 r2 = new Vector2(6, 6);  // Outside segment

        assertTrue(onSegmentTest(p, r1, q), "Point should be on segment");
        assertFalse(onSegmentTest(p, r2, q), "Point should not be on segment");
    }

    @Test
    void testNearSegment() {
        Vector2 p = new Vector2(0, 0);
        Vector2 r = new Vector2(4, 0);
        float minDistance = 0.5f;

        // Test point exactly on segment
        Vector2 q1 = new Vector2(2, 0);
        assertTrue(nearSegmentTest(p, q1, r, minDistance), "Point on segment should be near");

        // Test point slightly above segment within minDistance
        Vector2 q2 = new Vector2(2, 0.4f);
        assertTrue(nearSegmentTest(p, q2, r, minDistance), "Point near segment should be detected");

        // Test point too far from segment
        Vector2 q3 = new Vector2(2, 1);
        assertFalse(nearSegmentTest(p, q3, r, minDistance), "Point far from segment should not be detected");

        // Test degenerate case (p = r)
        Vector2 q4 = new Vector2(0.3f, 0.3f);
        assertTrue(nearSegmentTest(p, q4, p, minDistance), "Point near degenerate segment should be detected");

        // Test point near endpoint
        Vector2 q5 = new Vector2(4.3f, 0.3f);
        assertTrue(nearSegmentTest(p, q5, r, minDistance), "Point near endpoint should be detected");
    }

    @Test
    void testDoIntersectWithMinDistance() {
        float minDistance = 0.5f;

        // Test segments that don't actually intersect but are very close
        Vector2 p1 = new Vector2(0, 0);
        Vector2 q1 = new Vector2(4, 0);
        Vector2 p2 = new Vector2(2, 0.4f);
        Vector2 q2 = new Vector2(2, 4);
        assertTrue(doIntersect(p1, q1, p2, q2, minDistance), "Near-intersecting segments should be detected");

        // Test segments that are definitely too far to intersect
        Vector2 p3 = new Vector2(0, 0);
        Vector2 q3 = new Vector2(4, 0);
        Vector2 p4 = new Vector2(2, 1);
        Vector2 q4 = new Vector2(2, 4);
        assertFalse(doIntersect(p3, q3, p4, q4, minDistance), "Non-intersecting segments should not be detected");

        // Test segments that intersect normally
        Vector2 p5 = new Vector2(0, 0);
        Vector2 q5 = new Vector2(4, 4);
        Vector2 p6 = new Vector2(0, 4);
        Vector2 q6 = new Vector2(4, 0);
        assertTrue(doIntersect(p5, q5, p6, q6, minDistance), "Intersecting segments should be detected");
    }

    @Test
    void testDoIntersectSpecialCases() {
        float minDistance = 0.5f;

        // Test collinear segments that touch
        Vector2 p1 = new Vector2(0, 0);
        Vector2 q1 = new Vector2(4, 0);
        Vector2 p2 = new Vector2(4, 0);
        Vector2 q2 = new Vector2(8, 0);
        assertTrue(doIntersect(p1, q1, p2, q2, minDistance), "Touching collinear segments should intersect");

        // Test collinear segments that are near but don't touch
        Vector2 p3 = new Vector2(0, 0);
        Vector2 q3 = new Vector2(4, 0);
        Vector2 p4 = new Vector2(4.3f, 0);
        Vector2 q4 = new Vector2(8, 0);
        assertTrue(doIntersect(p3, q3, p4, q4, minDistance), "Near collinear segments should intersect");

        // Test T-junction with small gap
        Vector2 p5 = new Vector2(0, 0);
        Vector2 q5 = new Vector2(4, 0);
        Vector2 p6 = new Vector2(2, 0.4f);
        Vector2 q6 = new Vector2(2, 4);
        assertTrue(doIntersect(p5, q5, p6, q6, minDistance), "T-junction with small gap should intersect");
    }

    @Test
    void testSub() {
        Vector2 v1 = new Vector2(5, 7);
        Vector2 v2 = new Vector2(3, 2);
        Vector2 result = v1.sub(v2);

        assertEquals(2, result.getX());
        assertEquals(5, result.getY());
    }

    @Test
    void testNor() {
        Vector2 v = new Vector2(3, 4); // Length = 5
        Vector2 result = v.nor();

        assertEquals(3 / 5.0, result.getX(), 1e-6);
        assertEquals(4 / 5.0, result.getY(), 1e-6);

        // Test zero vector
        Vector2 zero = new Vector2(0, 0);
        Vector2 zeroResult = zero.nor();

        assertEquals(0, zeroResult.getX());
        assertEquals(0, zeroResult.getY());
    }

    @Test
    void testCpy() {
        Vector2 original = new Vector2(6, -4);
        Vector2 copy = original.cpy();

        assertNotSame(original, copy); // Ensure it's a new instance
        assertEquals(original.getX(), copy.getX());
        assertEquals(original.getY(), copy.getY());
    }

    @Test
    void testAdd() {
        Vector2 v1 = new Vector2(1, 2);
        Vector2 v2 = new Vector2(3, 4);
        Vector2 result = v1.add(v2);

        assertEquals(4, result.getX());
        assertEquals(6, result.getY());
    }

    @Test
    void testScl() {
        Vector2 v = new Vector2(2, -3);
        float scalar = 3;
        Vector2 result = v.scl(scalar);

        assertEquals(6, result.getX());
        assertEquals(-9, result.getY());
    }

    @Test
    void testLength() {
        Vector2 v = new Vector2(3, 4); // Length = 5

        float length = v.lengthTest();

        assertEquals(5, length, 1e-6);

        // Test zero vector
        Vector2 zero = new Vector2(0, 0);
        float zeroLength = zero.lengthTest();
        assertEquals(0, zeroLength, 1e-6);
    }
}
