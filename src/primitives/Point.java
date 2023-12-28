package primitives;

/**
 * the class implements a Point in space.
 * @author Yaniv and Ahuvya.
 */
public class Point {
    public static final Point ZERO = new Point(Double3.ZERO);
    /**
     * Double3 object, which represents a point in space
     */
    protected final Double3 xyz;

    /**
     *constructor. Gets 3 double values, and creates a point.
     * @param x
     * @param y
     * @param z
     */
    public Point(double x,double y,double z) {
        this.xyz = new Double3(x,y,z);
    }

    /**
     *Gets "double3" and creates a point In space
     * @param XYZ
     */
    Point(Double3 XYZ) {this.xyz = XYZ;}

    /**
     * Makes subtraction between 2 points
     * @param point
     * @return the vector we have created
     */
    public Vector subtract(Point point){
        return new Vector(this.xyz.subtract(point.xyz));
    }

    /**
     * Add 2 points
     * @param vec
     * @return Returns a new point, adding the 2 points.
     */
    public Point add(Vector vec){
        return new Point(xyz.add(vec.xyz));
    }

    /**
     * Calculate the distance between 2 points in possession 2
     * @param point
     * @return the distance between 2 points in possession 2
     */
    public double distanceSquared(Point point){
        double x = point.xyz.d1 - this.xyz.d1;
        double y = point.xyz.d2 - this.xyz.d2;
        double z = point.xyz.d3 - this.xyz.d3;
        return x*x + y*y + z*z;
    }

    /**
     * Returns the distance between 2 points, the root of the distance in possession of 2
     * @param point
     * @return The distance between 2 points
     */
    public double distance(Point point){
        return Math.sqrt(distanceSquared(point));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Point other && xyz.equals(other.xyz);
    }

    @Override
    public String toString() { return "" + xyz; }
}
