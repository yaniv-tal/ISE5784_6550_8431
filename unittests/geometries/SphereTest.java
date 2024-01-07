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
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the normal to the sphere.
        Sphere sphere = new Sphere(3,new Point(0,0,0));
        assertEquals(new Vector(0,0,1), sphere.getNormal(new Point(0,0,1)), "ERROR: incorrect normal for point in sphere");
    }

    @Test
    void testFindIntsersections() {
    }
}