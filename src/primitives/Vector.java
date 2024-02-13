package primitives;
/**
 * the class implements a vector.
 * @author Yaniv and Ahuvya.
 */
public class Vector extends Point {
    public static final Vector X = new Vector(1,0,0);
    public static final Vector Y = new Vector(0,1,0);
    public static final Vector Z = new Vector(0,0,1);

    /**
     * The constructor gets three double values
     * And creates a vector by the head point.
     * If it is the 0 vector, exception will be thrown
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Zero Vector is illegal");
        }
    }

    /**
     * The constructor gets double3 values
     * And creates a vector by the head point.
     * If it is the 0 vector, exception will be thrown
     *
     * @param XYZ
     */
    public Vector(Double3 XYZ) {
        super(XYZ);
        if (this.xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector is illegal");
    }

    @Override
    public Vector add(Vector vec) {
        return new Vector(this.xyz.add(vec.xyz));
    }

    /**
     * Multiply a vector by a scalar
     *
     * @param number
     * @return The new vector we multiplied by the scalar
     */
    public Vector scale(double number) {
        return new Vector(this.xyz.scale(number));
    }

    /**
     * dotProduct.
     *
     * @param vec
     * @return The scalar we got.
     */
    public double dotProduct(Vector vec) {
        return this.xyz.d1 * vec.xyz.d1 + this.xyz.d2 * vec.xyz.d2 + this.xyz.d3 * vec.xyz.d3;
    }

    /**
     * vector multiplication
     *
     * @param vec
     * @return A new vector, created from the multiplication
     */
    public Vector crossProduct(Vector vec) {
        return new Vector(this.xyz.d2 * vec.xyz.d3 - this.xyz.d3 * vec.xyz.d2, this.xyz.d3 * vec.xyz.d1 - this.xyz.d1 * vec.xyz.d3, xyz.d1 * vec.xyz.d2 - xyz.d2 * vec.xyz.d1);
    }

    /**
     * Calculation of the length of the vector to the 2nd power,
     * using the property that the length of the vector to the 2nd power
     * is equal to a scalar product of the vector itself
     *
     * @return the length to the 2nd power
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Calculate the actual length using the previous function
     *
     * @return The length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Calculates the normalized vector (by multiplying it by the inverse of its length)
     *
     * @return A new normalized vector
     */
    public Vector normalize() {
        return scale(1 / length());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Vector other && super.equals(other);
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }
}
