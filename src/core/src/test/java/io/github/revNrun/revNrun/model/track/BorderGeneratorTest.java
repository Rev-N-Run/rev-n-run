package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorderGeneratorTest {
    @Test
    void testGenerateLeftBorder_validInput() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 1.0f;

        // Act
        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, width);

        // Assert
        assertNotNull(leftBorder, "Left border should not be null.");
        assertEquals(points.size(), leftBorder.size(), "Left border should have the same number of points as input.");
    }

    @Test
    void testGenerateRightBorder_validInput() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 1.0f;

        // Act
        List<Vector2> rightBorder = BorderGenerator.generateRightBorder(points, width);

        // Assert
        assertNotNull(rightBorder, "Right border should not be null.");
        assertEquals(points.size(), rightBorder.size(), "Right border should have the same number of points as input.");
    }

    @Test
    void testGenerateLeftBorder_emptyPoints() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        float width = 1.0f;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            BorderGenerator.generateLeftBorder(points, width)
        );
        assertEquals("At least two points are required to calculate a border.", exception.getMessage());
    }

    @Test
    void testGenerateRightBorder_singlePoint() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        float width = 1.0f;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            BorderGenerator.generateRightBorder(points, width)
        );
        assertEquals("At least two points are required to calculate a border.", exception.getMessage());
    }

    @Test
    void testGenerateBorder_tangentCalculation() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 1.0f;

        // Act
        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, width);

        // Assert
        // Tangent vectors and offsets should produce expected results
        assertNotNull(leftBorder);
        assertEquals(3, leftBorder.size());
        // Check specific coordinates (example values, adjust based on exact behavior)
        assertEquals(-0.3536f, leftBorder.get(0).getX(), 1e-4);
        assertEquals(0.3536f, leftBorder.get(0).getY(), 1e-4);
    }

    @Test
    void testGenerateBorder_handlesWideWidth() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 10.0f;

        // Act
        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, width);

        // Assert
        assertNotNull(leftBorder);
        assertEquals(3, leftBorder.size());
        assertTrue(leftBorder.get(0).getX() < points.get(0).getX(), "Left border should shift left for wide width.");
    }

    @Test
    void testGenerateBorder_handlesNarrowWidth() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 0.1f;

        // Act
        List<Vector2> rightBorder = BorderGenerator.generateRightBorder(points, width);

        // Assert
        assertNotNull(rightBorder);
        assertEquals(3, rightBorder.size());
        assertTrue(rightBorder.get(0).getX() > points.get(0).getX(),
            "Right border should shift right for narrow width.");
    }

    // LOOP TESTING

    @Test
    void testNoIterations() {
        // Points list less than 2 - loop doesn't execute
        List<Vector2> points = Collections.singletonList(new Vector2(0, 0)); // Single point

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> BorderGenerator.generateLeftBorder(points, 2.0f));
        assertEquals("At least two points are required to calculate a border.", exception.getMessage());
    }

    @Test
    void testSingleIteration() {
        // Points list with exactly 2 points - loop executes once
        List<Vector2> points = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 0)
        );

        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, 2.0f);

        assertEquals(2, leftBorder.size(), "Left border should have exactly 2 points.");
        assertNotEquals(points.get(0), leftBorder.get(0), "Left border point should be offset.");
        assertNotEquals(points.get(1), leftBorder.get(1), "Left border point should be offset.");
    }

    @Test
    void testMultipleIterations() {
        // Points list with multiple points - loop executes multiple times
        List<Vector2> points = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 0),
            new Vector2(1, 1)
        );

        List<Vector2> rightBorder = BorderGenerator.generateRightBorder(points, 2.0f);

        assertEquals(3, rightBorder.size(), "Right border should have exactly 3 points.");
        for (int i = 0; i < points.size(); i++) {
            assertNotEquals(points.get(i), rightBorder.get(i), "Right border point should be offset.");
        }
    }

    @Test
    void testBoundaryConditionsStart() {
        // Ensure start point tangent is calculated correctly
        List<Vector2> points = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 0),
            new Vector2(1, 1)
        );

        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, 2.0f);

        // Check the first point offset
        Vector2 expectedOffsetPoint = points.get(0).cpy().add(new Vector2(0, 1).scl(1.0f));
        assertTrue(expectedOffsetPoint.equals(leftBorder.get(0)), "Start point offset is incorrect.");
    }

    @Test
    void testBoundaryConditionsEnd() {
        // Ensure end point tangent is calculated correctly
        List<Vector2> points = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 0),
            new Vector2(1, 1)
        );

        List<Vector2> rightBorder = BorderGenerator.generateRightBorder(points, 2.0f);

        // Check the last point offset
        Vector2 expectedOffsetPoint = points.get(2).cpy().add(new Vector2(-1, 0).scl(-1.0f));
        assertTrue(expectedOffsetPoint.equals(rightBorder.get(2)), "End point offset is incorrect.");
    }

    @Test
    void testAllPointsTangentAndOffset() {
        // Complex track with more points
        List<Vector2> points = Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 0),
            new Vector2(2, 1),
            new Vector2(3, 3)
        );

        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, 2.0f);

        assertEquals(4, leftBorder.size(), "Border should have the same number of points as input.");
        for (int i = 0; i < points.size(); i++) {
            Vector2 tangent = i == 0
                ? points.get(1).sub(points.get(0)).nor()
                : (i == points.size() - 1
                ? points.get(i).sub(points.get(i - 1)).nor()
                : points.get(i + 1).sub(points.get(i - 1)).nor());

            Vector2 expectedOffset = new Vector2(-tangent.getY(), tangent.getX()).nor().scl(1.0f);
            Vector2 expectedPoint = points.get(i).cpy().add(expectedOffset);
            assertTrue(expectedPoint.equals(leftBorder.get(i)), "Border point offset calculation is incorrect.");
        }
    }
}
