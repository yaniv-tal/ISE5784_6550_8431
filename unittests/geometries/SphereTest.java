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
        final Point p200 = new Point(2, 0, 0);
        final Point p000 = new Point(0, 0, 0);
        final var exp1 = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v011 = new Vector(0, 1, 1);
        final Vector v110 = new Vector(1, 1, 0);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector vm100 = new Vector(-1, 0, 0);
        final Vector v00m1 = new Vector(0, 0, -1);
        final Point pm100 = new Point(-1, 0, 0);
        final Point p201 = new Point(2, 0, 1);
        final Point p300 = new Point(3, 0, 0);
        final Point p0p500 = new Point(0.5, 0, 0);
        final Point p105 = new Point(1, 0, 5);
        final Point p20m1 = new Point(2, 0, -1);
        final Point p10m1 = new Point(1, 0, -1);
        final Point p101 = new Point(1, 0, 1);
        final Point p100p5 = new Point(1, 0, 0.5);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(pm100, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(pm100, v310));
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        final var exp2 = List.of(p000);
        final var result2 = sphere.findIntersections(new Ray(p0p500, vm100));
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(exp2, result2, "Ray crosses sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p105, v011)), "Ray's line out of sphere");
        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        final var exp3 = List.of(gp2);
        final var result3 = sphere.findIntersections(new Ray(gp1, v310));
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(exp3, result3, "Ray crosses sphere");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p200, v011)), "Ray's line out of sphere");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        final var exp4 = List.of(p000,p200);
        final var result4 = sphere.findIntersections(new Ray(pm100, v100));
        assertEquals(2, result4.size(), "Wrong number of points");
        assertEquals(exp4, result4, "Ray crosses sphere");
        // TC14: Ray starts at sphere and goes inside (1 points)
        final var exp5 = List.of(p10m1);
        final var result5 = sphere.findIntersections(new Ray(p101, v00m1));
        assertEquals(1, result5.size(), "Wrong number of points");
        assertEquals(exp5, result5, "Ray crosses sphere");
        // TC15: Ray starts inside (1 points)
        final var result6 = sphere.findIntersections(new Ray(p100p5, v00m1));
        assertEquals(1, result6.size(), "Wrong number of points");
        assertEquals(exp5, result6, "Ray crosses sphere");
        // TC16: Ray starts at the center (1 points)
        final var result7 = sphere.findIntersections(new Ray(p100, v00m1));
        assertEquals(1, result7.size(), "Wrong number of points");
        assertEquals(exp5, result7, "Ray crosses sphere");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p200, v100)), "Ray's line out of sphere");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p105, v001)), "Ray's line out of sphere");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(p20m1, v001)), "Ray's line out of sphere");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(p200, v001)), "Ray's line out of sphere");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(p201, v001)), "Ray's line out of sphere");
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(p300, v001)), "Ray's line out of sphere");
    }
}