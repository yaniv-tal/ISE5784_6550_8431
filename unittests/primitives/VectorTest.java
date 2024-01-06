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
    //The vectors we will use for testing
    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);

    /**
     * Test method for {@link Vector#Vector(double, double, double)}
     * and for {@link Vector#Vector(Double3)}
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test construct a vector.
        assertDoesNotThrow(() -> new Vector(1, 1, 1), "Failed constructing a vector");
        assertDoesNotThrow(() -> new Vector(new Double3(1, 1, 1)),"Failed constructing a vector");

        // =============== Boundary Values Tests ==================
        // TC02: Test construct the zero vector.
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "Constructed zero vector");
        assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), "Constructed zero vector");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test add vectors.
        assertEquals(v1Opposite, v1.add(v2) , "ERROR: primitives.Vector + primitives.Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC02: adding vector to itself should throw exception.
        assertThrows(IllegalArgumentException.class,()-> v1.add(v1Opposite),"ERROR: primitives.Vector + -itself does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);
        // TC01: Test Multiplication of a vector by a negative number.
        assertEquals(v1,v1Opposite.scale(-1) , "ERROR: Multiplication of a vector by a negative number");
        // TC02: Test Multiplication of a vector by a positive number.
        assertEquals(v2, v1Opposite.scale(2), "Multiplication of a vector by a positive number");
        // TC03: Test Multiplication of a vector by a number between -1 to 1.
        assertEquals(v1Opposite,v2.scale(0.5), "Multiplication of a vector by a number between -1 to 1.");

        // =============== Boundary Values Tests ==================
        // TC04: Multiplication of a vector by zero should throw an exception.
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "Multiplication of a vector by zero doesn't throw an exception.");

    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test the dotProduct of 2 vectors.
        assertEquals(0,v1.dotProduct(v2) + 28, "ERROR: dotProduct() wrong value");
        // =============== Boundary Values Tests ==================
        // TC02: Test dotProduct with 2 orthogonal vectors.
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        //TC01; Test the crossProduct for usual vectors.
        Vector vr = v1.crossProduct(v3);
        assertEquals(0, vr.length() - v1.length() * v3.length(), 0.001, "ERROR: crossProduct() wrong result length");
        assertEquals(0, vr.dotProduct(v1), "ERROR: crossProduct() result is not orthogonal to its 1st operand");
        assertEquals(0, vr.dotProduct(v3), "ERROR: crossProduct() result is not orthogonal to its 2nd operand");

        // =============== Boundary Values Tests ==================
        //TC02: parallel vectors throw an exception.
        assertThrows(IllegalArgumentException.class,()-> v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test the length squared of vector
        assertEquals(9, v4.lengthSquared(), "ERROR: lengthSquared() wrong value");
    }
    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test the length of vector
        assertEquals(3, v4.length(), "ERROR: length() wrong value");
    }
    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: check the normalized.
        Vector vr = v1.normalize();
        assertEquals(1,vr.length(),0.001,"ERROR: the normalized vector is not a unit vector");
        assertThrows(IllegalArgumentException.class,()->v1.crossProduct(vr),"ERROR: the normalized vector is not parallel to the original one");
        assertTrue(Util.compareSign(v1.dotProduct(vr),1),"ERROR: the normalized vector is opposite to the original one");
    }
}