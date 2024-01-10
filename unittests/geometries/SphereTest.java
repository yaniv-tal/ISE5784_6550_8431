package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class SphereTest {
    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the normal to the sphere.
        Sphere sphere = new Sphere(3,new Point(0,0,0));
        assertEquals(new Vector(0,0,1), sphere.getNormal(new Point(0,0,1)), "ERROR: incorrect normal for point in sphere");
    }
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(1d,p100);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final Point gp3 = new Point(2, 0, 0);
        final var exp1 = List.of(gp1, gp2);
        final var exp2 = List.of(gp3);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector v001 = new Vector(0, 0, 1);
        final Point p01 = new Point(-1, 0, 0);
        final Point p02 = new Point(0.5, 0, 0);
        final Point p03 = new Point(1, 0, 5);
        final Point p04 = new Point(2, 0, -1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310));//.stream().sorted(Comparator.comparingDouble(p) -> p.distance(p01))).toList();
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        final var result2 = sphere.findIntersections(new Ray(p02, v100));//.stream().sorted(Comparator.comparingDouble(p) -> p.distance(p01))).toList();
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(exp2, result2, "Ray crosses sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p03, v001)), "Ray's line out of sphere");
        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        final var exp3 = List.of(gp2);
        final var result3 = sphere.findIntersections(new Ray(gp1, v310));//.stream().sorted(Comparator.comparingDouble(p) -> p.distance(p01))).toList();
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(exp3, result3, "Ray crosses sphere");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(gp3, v001)), "Ray's line out of sphere");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        // TC14: Ray starts at sphere and goes inside (1 points)
        // TC15: Ray starts inside (1 points)
        // TC16: Ray starts at the center (1 points)
        // TC17: Ray starts at sphere and goes outside (0 points)
        // TC18: Ray starts after sphere (0 points)
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(p04, v001)), "Ray's line out of sphere");
        // TC20: Ray starts at the tangent point
        // TC21: Ray starts after the tangent point
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line

    }
}