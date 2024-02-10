package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Geometries class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class GeometriesTest {

    /**
     * Test method for {@link Intersectable#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Sphere sphere = new Sphere(1, new Point(1,0,0));
        Plane plane = new Plane(new Point(1,1,0), new Vector(0,0,1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Some of the geometries intersect the ray, and some don't.
        Geometries geometries = new Geometries(sphere, plane, new Triangle(new Point(9,9,9),new Point(10,10,10),new Point(11,11,12)));
        assertEquals(3, geometries.findGeoIntersections(new Ray(new Point(1,0,-2),new Vector(0,0,1))).size(), "ERROR: Wrong number of intersections.");

        // =============== Boundary Values Tests ==================
        Geometries geometries1 = new Geometries(
                sphere,
                plane,
                new Triangle(new Point(2,0,0),new Point(0,2,0),new Point(0,-2,0)),
                new Polygon(new Point(0,-3,0),new Point(0,3,0),new Point(3,3,0),new Point(3,-3,0)));
        //TC11: Empty list of geometries.
        Geometries geometries2 = new Geometries();
        assertNull(geometries2.findGeoIntersections(new Ray(new Point(0,0,1),new Vector(2,2,18))), "ERROR: Wrong number of intersections.");
        //TC12: None of the geometries intersects the ray.
        assertNull(geometries1.findGeoIntersections(new Ray(new Point(6,6,6),new Vector(1,1,0))), "ERROR: Wrong number of intersections.");
        //TC13: Only one geometry intersect the ray.
        assertEquals(1,geometries1.findGeoIntersections(new Ray(new Point(0.5,0.5,0.5),new Vector(1,1,0))).size(),"ERROR: Wrong number of intersections.");
        //TC14: All the geometries intersects the ray.
        assertEquals(5, geometries1.findGeoIntersections(new Ray(new Point(1,0,-2),new Vector(0,0,1))).size(), "ERROR: Wrong number of intersections.");
    }
}