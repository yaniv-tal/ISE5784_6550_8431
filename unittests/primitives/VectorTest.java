package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class VectorTest {
    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(v1Opposite, v1.add(v2) , "ERROR: primitives.Vector + primitives.Vector does not work correctly");
        assertThrows(IllegalArgumentException.class,()-> v1.add(v1Opposite),"ERROR: primitives.Vector + -itself does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.001, "crossProduct() wrong result length");
        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(0, vr.dotProduct(v1), "crossProduct() result is not orthogonal to 1st operand");
        assertEquals(0, vr.dotProduct(v3), "crossProduct() result is not orthogonal to 2nd operand");
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of parallel vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), //
                "crossProduct() for parallel vectors does not throw an exception");

    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(0,v1.dotProduct(v2) + 28, "ERROR: dotProduct() wrong value");
    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        Vector vr = v1.crossProduct(v3);
        assertEquals(0, vr.length() - v1.length() * v3.length(), 0.001, "ERROR: crossProduct() wrong result length");
        assertEquals(0, vr.dotProduct(v1), "ERROR: dotProduct() wrong value");
        assertEquals(0, vr.dotProduct(v3), "ERROR: dotProduct() wrong value");
        assertThrows(IllegalArgumentException.class,()-> v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        assertEquals(0, v4.lengthSquared() - 9, "ERROR: lengthSquared() wrong value");
    }
    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() { assertEquals(0, v4.length() - 3, "ERROR: length() wrong value"); }
    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(0, u.length() - 1, "ERROR: the normalized vector is not a unit vector");

    }
}