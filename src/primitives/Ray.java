package primitives;

import java.util.Objects;
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

    public Vector getDirection() {
        return direction;
    }

    public Point getHead() {
        return head;
    }
}
