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
        return (point.xyz.d1 - this.xyz.d1) * (point.xyz.d1 - this.xyz.d1) +
                (point.xyz.d1 - this.xyz.d2) * (point.xyz.d1 - this.xyz.d2) +
                (point.xyz.d1 - this.xyz.d3) * (point.xyz.d1 - this.xyz.d3);
    }

    public double distance(Point point){
        return Math.sqrt(distanceSquared(point));
    }
}
