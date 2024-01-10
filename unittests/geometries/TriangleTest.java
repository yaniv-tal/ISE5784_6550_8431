package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the normal to the triangle.
        // Creating a triangle with vertices (0,0,0),(0,1,0),(1,0,0)
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(0, 1, 0), new Point(1, 0, 0));
        // Getting the normal at the given point
        Vector normal = new Vector(0, 0, 1);
        assertEquals(normal, triangle.getNormal(new Point(0.1,0.1,0)), "ERROR: normal to plane does not work correctly");
        assertEquals(0, new Vector(1, 0, 0).dotProduct(normal), "ERROR: The vector is not orthogonal to the vector in the triangle");
        assertEquals(1, normal.length(), "ERROR: the normal is not a unit vector");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        final Point p01 = new Point(-1, 0, 0);
        final Point p02 = new Point(1, 0, 0);
        final Point p03 = new Point(0, 1, 0);
        final Point p04 = new Point(0, 0, 1);
        final Point p05 = new Point(-1, -1, -1);
        Triangle triangle = new Triangle(p02,p03,p04);
        final Vector v01 = new Vector(-1, 0, 0);
        final Vector v02 = new Vector(1, 0, -1);
        final Vector v03 = new Vector(1, 1, 1);
        final Point gp1 = new Point(1, 1, 1);
        final var exp1 = List.of(gp1);

        assertNull(triangle.findIntersections(new Ray(p01, v01)), "Ray's line out of Plane");
        assertNull(triangle.findIntersections(new Ray(p02, v01)), "Ray's line out of Plane");
        assertNull(triangle.findIntersections(new Ray(p02, v02)), "Ray's line does not intersect with the plane");
        assertNull(triangle.findIntersections(new Ray(p01, v02)), "Ray's line out of Plane");
        final var result1 = plane.findIntersections(new Ray(p05, v03));//.stream().sorted(Comparator.comparingDouble(p) -> p.distance(p01))).toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray crosses Plane");

        //טוב זה טעות לשנות
    }
}

