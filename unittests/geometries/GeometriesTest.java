package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() {
        Geometries geometries = new Geometries();

    }

    @Test
    void findIntersections() {
        Sphere sphere = new Sphere(1, new Point(1,0,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Some of the geometries intersect the ray, and some don't.
        Geometries geometries = new Geometries(
                sphere,
                new Plane(new Point(1,1,0), new Vector(0,0,1)),
                new Triangle(new Point(9,9,9),new Point(10,10,10),new Point(11,11,11)));
        assertEquals(3, geometries.findIntersections(new Ray(new Point(1,0,-2),new Vector(0,0,1))).size(),
                "ERROR: Wrong number of intersections.");
        // =============== Boundary Values Tests ==================
        //TC11:
        //TC12:
        //TC13:
        //TC14:
    }
}