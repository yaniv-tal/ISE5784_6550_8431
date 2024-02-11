package primitives;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * the class implements a Ray in space .
 * @author Yaniv and Ahuvya.
 */
public class Ray {
    private final Point head;
    private final Vector direction;
    private static final double DELTA = 0.1;

    /**
     * constructor. Gets the head and the direction vector,
     * normalizes the vector and creates the ray.
     * @param head
     * @param direction
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     *
     */
    public Ray(Point point, Vector direction, Vector normal) {
        this.direction = direction.normalize();
        if (isZero(normal.dotProduct(direction)))
                throw new IllegalArgumentException("Vector zero is not valid");
        Vector epsVector = normal.scale(normal.dotProduct(direction) > 0 ? DELTA : -DELTA);
        this.head = point.add(epsVector);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(head, ray.head) && Objects.equals(direction, ray.direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }

    /**
     * The method calculates a point on the ray, at a given distance from the ray's head.
     * @param t for the distance from the head.
     * @return the calculated point.
     */
    public Point getPoint(double t) {
        if (isZero(t))
            return head;
        return head.add(direction.scale(t));
    }

    /**
     * getter function for the field 'direction'.
     * @return the field direction.
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * getter function for the field 'head'.
     * @return the field head.
     */
    public Point getHead() {
        return head;
    }


    /**
     * function for find the closest point to the head of the ray
     * @param points - a list of points
     * @return the closest point to the head of the ray from the list.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * function for find the closest point to the head of the ray
     * @param points - a list of "geopoints"
     * @return the closest point to the head of the ray from the list.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points){
        //Makes sure the list is not empty
        if(points == null || points.isEmpty())
            return null;

        GeoPoint closest = null;
        double shortestDistance = Double.MAX_VALUE;
        for(GeoPoint geoPoint: points) {
            double calcDistance = geoPoint.point.distance(head);
            if (calcDistance < shortestDistance ) {
                closest = geoPoint;
                shortestDistance = calcDistance;
            }
        }
        //Arranges the points in order of their distance from the beginning of the ray, and returns the closest one
        return closest;
    }

}
