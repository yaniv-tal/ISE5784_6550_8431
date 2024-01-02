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
        Point center = new Point(1,1,1);
        Sphere sphere = new Sphere(3,new Point(1,1,1));
        Point point = new Point(3,3,2);
        assertEquals(point.subtract(center).normalize(), sphere.getNormal(point), "ERROR:get normal does not work correctly");
    }
}