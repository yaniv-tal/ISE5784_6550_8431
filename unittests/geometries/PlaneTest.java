package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class PlaneTest {
    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)} and for {@link Plane#Plane(Point, Vector)}
     */
    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================
        // TC01: The first and second points are same.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(1, 7, 9)),
                "ERROR: constructor does not throw error in illegal definition- 2 correlating points.");
        // TC02: The points are on the same line.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(3, 3, 3)),
                "ERROR: constructor does not throw error in illegal definition - all points on same line.");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the normal to the plane.
        Plane plane = new Plane(new Point(0, 0, 0), new Point(0, 1, 0), new Point(1, 0, 0));
        assertEquals(new Vector(0, 0, 1), plane.getNormal(), "ERROR: normal to plane does not work correctly");
        assertEquals(1, plane.getNormal().length(), "ERROR: plane normalized function does not work correctly");
    }

    /**
     * Test method for {@link Intersectable#findGeoIntersections(Ray)}.
     */
    @Test
    void testFindGeoIntersections() {
        final Point pm100 = new Point(-1, 0, 0);
        final Point p100 = new Point(1, 0, 0);
        final Point p010 = new Point(0, 1, 0);
        final Point p001 = new Point(0, 0, 1);
        final Point pm1m1m1 = new Point(-1, -1, -1);
        final Vector vm100 = new Vector(-1, 0, 0);
        final Vector v10m1 = new Vector(1, 0, -1);
        final Vector v111 = new Vector(1, 1, 1);
        final Vector v100 = new Vector(1, 0, 0);
        final Point p1p3 = new Point((double) 1 / 3, (double) 1 / 3, (double) 1 / 3);
        final Point p555 = new Point(5, 5, 5);

        Plane plane = new Plane(p100, p010, p001);
        final var exp1 = List.of(new Intersectable.GeoPoint(plane,p100));
        final var exp2 = List.of(new Intersectable.GeoPoint(plane,p1p3));
        // ============ Equivalence Partitions Tests ==============
        // TC01: A ray that starts outside the plane, is not parallel to the plane,
        // makes a non-right angle with the plane, and cuts the plane
        final var result1 = plane.findGeoIntersections(new Ray(pm100, v100));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray crosses Plane");
        // TC02: A ray that starts outside the plane, is not parallel to the plane,
        // makes a non-right angle with the plane, and does not intersect the plane
        assertNull(plane.findGeoIntersections(new Ray(pm100, vm100)), "Ray's line out of Plane");
        // =============== Boundary Values Tests ==================
        // **** Group: cases of a ray parallel to the plane
        // TC11: case of a ray out of the plane
        assertNull(plane.findGeoIntersections(new Ray(pm100, v10m1)), "Ray's line out of Plane");
        // TC12: case of a ray inside the plane
        assertNull(plane.findGeoIntersections(new Ray(p100, v10m1)), "Ray's line does not intersect with the plane");
        // **** Group: 3 cases of a ray perpendicular to the plane
        // TC13: starting "after" the plane
        assertNull(plane.findGeoIntersections(new Ray(p555, v111)), "Ray's line out of Plane");
        // TC14: starting "inside" the plane
        assertNull(plane.findGeoIntersections(new Ray(p010, v111)), "Ray's line out of Plane");
        // TC15: starting "before" the plane
        final var result2 = plane.findGeoIntersections(new Ray(pm1m1m1, v111));
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(exp2, result2, "Ray crosses Plane");
        // TC15: One case of a ray that is neither parallel nor perpendicular to the plane but starts inside the plane
        assertNull(plane.findGeoIntersections(new Ray(p010, vm100)), "Ray's line out of Plane");
        // TC16: And one case that is similar to the previous case, but the beginning of the beam is exactly at the "reference point" of
        //The plane (the point stored in the object of the plane in addition to the normal vector, or
        //In other words - a given point inside the plane)
        assertNull(plane.findGeoIntersections(new Ray(p100, vm100)), "Ray's line out of Plane");
    }

    /**
     * Test method for
     * {@link geometries.Plane#findGeoIntersections(Ray, double)}.
     */
    @Test
    void testDistanceIntersections() {
        Plane plane = new Plane(new Point(1, 1, 0), new Point(1, -1, 0), new Point(-1, -1, 0));
        // ============ Equivalence Partitions Tests ==============
        // EP02: there are intersections farther than max distance
        assertNull(plane.findGeoIntersections(new Ray(new Point(0, 0, -10), Vector.Z), 9), "farther than maxDistance");
        // =============== Boundary Values Tests ==================
        // BV01: there is intersections on max distance
        assertEquals(1, plane.findGeoIntersections(new Ray(new Point(0, 0, -10), Vector.Z), 10).size(), "at max distance");
    }
}