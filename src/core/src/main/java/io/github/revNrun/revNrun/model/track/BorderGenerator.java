package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

class BorderGenerator {
    static List<Vector2> generateLeftBorder(List<Vector2> points, float width){
        return generateBorder(points, width, true);
    }

    static List<Vector2> generateRightBorder(List<Vector2> points, float width){
        return generateBorder(points, width, false);
    }

    private static List<Vector2> generateBorder(List<Vector2> points, float width, boolean isLeft) {
        List<Vector2> borderPoints = new ArrayList<>();

        if (points.size() < 2) {
            throw new IllegalArgumentException("At least two points are required to calculate a border.");
        }

        float offset = isLeft ? width / 2 : -width / 2;

        for (int i = 0; i < points.size(); i++) {
            Vector2 current = points.get(i);
            Vector2 previous = i > 0 ? points.get(i - 1) : null;
            Vector2 next = i < points.size() - 1 ? points.get(i + 1) : null;

            Vector2 tangent = getTangent(previous, current, next);
            Vector2 normal = new Vector2(-tangent.getY(), tangent.getX()).nor();
            Vector2 borderPoint = current.cpy().add(normal.scl(offset));

            borderPoints.add(borderPoint);
        }

        return borderPoints;
    }

    private static Vector2 getTangent(Vector2 previous, Vector2 current, Vector2 next) {
        if (previous != null && next != null) {
            return next.sub(previous).nor(); // Midpoint tangent
        } else if (next != null) {
            return next.sub(current).nor(); // Start point
        } else if (previous != null) {
            return current.sub(previous).nor(); // End point
        } else {
            return new Vector2(0,0); // Single point, should not happen due to initial check
        }
    }
}
