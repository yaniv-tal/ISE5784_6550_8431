package primitives;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

/**
 * the class implements a Ray in space .
 * @author Yaniv and Ahuvya.
 */
public class Ray {
    private final Point head;
    private final Vector direction;

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

    public Point findClosestPoint( List<Point> points) {
        return null;
    }
}
