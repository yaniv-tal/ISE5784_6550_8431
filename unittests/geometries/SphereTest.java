package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        Sphere sphere = new Sphere(3,new Point(0,0,0));
        Point point = new Point(0,0,1);
        assertEquals(new Vector(0,0,1), sphere.getNormal(point), "ERROR: does not return the correct normal");
    }
}