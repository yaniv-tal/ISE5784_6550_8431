package primitives;

public class Point {
    protected final Double3 xyz;

    public Point(double x,double y,double z) {
        this.xyz = new Double3(x,y,z);
    }

    Point(Double3 XYZ) {this.xyz = XYZ;}

    public Vector subtract(Point point){
        return new Vector(this.xyz.subtract(point.xyz));
    }

    public Point add(Vector vec){
        return new Point(this.xyz.add(vec.xyz));
    }

    public double distanceSquared(Point point){
        double x = point.xyz.d1 - this.xyz.d1;
        double y = point.xyz.d1 - this.xyz.d2;
        double z = point.xyz.d1 - this.xyz.d3;
        return x*x + y*y + z*z;
    }

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
