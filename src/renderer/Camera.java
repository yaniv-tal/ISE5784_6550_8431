package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * The class implements a camera.
 * @author Yaniv and Ahuvya.
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vTo, vUp, vRight;
    private double width = 0.0, height = 0.0, distance = 0.0;

    /**
     * get function for the width of the View Plane.
     * @return the width of the View plane.
     */
    public double getWidth() {
        return width;
    }
    /**
     * get function for the height of the View Plane.
     * @return the height of the View plane.
     */
    public double getHeight() {
        return height;
    }
    /**
     * get function for the distance of the View Plane.
     * @return the distance of the View plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Default constructor.
     */
    private Camera(){}

    /**
     * function for a new Builder object.
     * @return a new Builder object.
     */
    public static Builder getBuilder() { return new Builder(); }

    /** להשלים
     *
     * @param nX - Represents the amount of columns (row width)
     * @param nY - represents the number of rows (column height).
     * @param j - column number
     * @param i - row number
     * @return
     */
    public Ray constructRay(int nX, int nY, int j, int i){ return null;};

    /**
     * The class implements a Builder Design Pattern.
     * @author Yaniv and Ahuvya.
     */
    public static class Builder {
        private final Camera camera = new Camera();

        /**
         * Determining the location of the camera
         * @param location
         * @return Returns the Builder object
         */
        public Builder setLocation(Point location) {
            camera.p0 = location;
            return this;
        }

        /**
         * Determining the Direction of the camera
         * @param to vector
         * @param up vector
         * @return Returns the Builder object
         */
        public Builder setDirection(Vector to, Vector up) {
            //Correctness check, make sure they are orthogonal.
            if( !Util.isZero((to.dotProduct(up))))
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            camera.vTo = to.normalize();
            camera.vUp = up.normalize();
            return this;
        }

        /**
         * set the size of the view plane
         * @param width
         * @param height
         * @return Returns the Builder object
         */
        public Builder setVpSize(double width, double height) {
            //Correctness check, make sure they are positive.
            if (Util.alignZero(width) <= 0) throw new IllegalArgumentException("The width should be bigger than 0");
            if (Util.alignZero(height) <= 0) throw new IllegalArgumentException("The height should be bigger than 0");
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * to set the distance between camera and view plane
         * @param distance
         * @return Returns the Builder object
         */
        public Builder setVpDistance(double distance) {
            //Correctness check, make sure they are positive.
            if (Util.alignZero(camera.distance) <= 0) throw new IllegalArgumentException("The distance should be bigger than 0");
            camera.distance = distance;
            return this;
        }

        public Camera build() {
            //Correctness check, make sure that the vectors and the points are not "NULL".
            if (camera.p0 == null) throw new MissingResourceException("Missing rendering argument", "Camera","p0");
            if (camera.vUp == null) throw new MissingResourceException("Missing rendering argument", "Camera","vUp");
            if (camera.vTo == null) throw new MissingResourceException("Missing rendering argument", "Camera","vTo");
            //Correctness check, make sure that the values are not 0.
            if (Util.alignZero(camera.width) == 0) throw new MissingResourceException("Missing rendering argument", "Camera","width");
            if (Util.alignZero(camera.height) == 0) throw new MissingResourceException("Missing rendering argument", "Camera","height");
            if (Util.alignZero(camera.distance) == 0) throw new MissingResourceException("Missing rendering argument", "Camera","distance");
            //Correctness check, make sure that the values are positives.
            if (Util.alignZero(camera.width) < 0) throw new IllegalArgumentException("The width can't be negative");
            if (Util.alignZero(camera.height) < 0) throw new IllegalArgumentException("The height can't be negative");
            if (Util.alignZero(camera.distance) < 0) throw new IllegalArgumentException("The distance can't be negative");
            //Correctness check, make sure that vTo and vUp are orthogonal.
            if (!isZero(camera.vUp.dotProduct(camera.vTo))) throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            //Correctness check, make sure that vTo, vUp and vRight are normalized.
            if(!Util.isZero(camera.vTo.lengthSquared()-1)) throw new IllegalArgumentException("The vTo vector must be normalized");
            if(!Util.isZero(camera.vUp.lengthSquared()-1)) throw new IllegalArgumentException("The vUp vector must be normalized");
            if(!Util.isZero(camera.vRight.lengthSquared()-1)) throw new IllegalArgumentException("The vRight vector must be normalized");
            return (Camera) camera.clone();
        }
    }
}

