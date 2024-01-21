package renderer;

import primitives.*;

import java.util.MissingResourceException;
import java.util.prefs.PreferenceChangeEvent;

import static primitives.Util.isZero;

/**
 * The class implements a camera.
 * @author Yaniv and Ahuvya.
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vTo, vUp, vRight;
    private double width = 0.0, height = 0.0, distance = 0.0;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    /**
     * get function for the width of the View Plane.
     *
     * @return the width of the View plane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * get function for the height of the View Plane.
     *
     * @return the height of the View plane.
     */
    public double getHeight() {
        return height;
    }

    /**
     * get function for the distance of the View Plane.
     *
     * @return the distance of the View plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Default constructor.
     */
    private Camera() {
    }

    /**
     * function for a new Builder object.
     *
     * @return a new Builder object.
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * @param nX - Represents the amount of columns (row width)
     * @param nY - represents the number of rows (column height).
     * @param j  - column number
     * @param i  - row number
     * @return
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pCenter = p0.add(vTo.scale(distance));

        //Ratio
        double Ry = (double) height / nY;
        double Rx = (double) width / nX;

        //Pixel[i,j] center
        double yI = -(i - (double) (nY - 1) / 2) * Ry;
        double xJ = (j - (double) (nX - 1) / 2) * Rx;
        Point pIJ = pCenter;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(yI));

        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }

    ;

    /**
     * The class implements a Builder Design Pattern.
     *
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

        public Builder setImageWriter(ImageWriter imageWriter){
            this.camera.imageWriter = imageWriter;
            return this;
        }

        public Builder setRayTracer(RayTracerBase rayTracer){
            this.camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Determining the Direction of the camera
         *
         * @param to vector
         * @param up vector
         * @return Returns the Builder object
         */
        public Builder setDirection(Vector to, Vector up) {
            //Correctness check, make sure they are orthogonal.
            if (!Util.isZero((to.dotProduct(up))))
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            camera.vTo = to.normalize();
            camera.vUp = up.normalize();
            return this;
        }

        /**
         * set the size of the view plane
         *
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
         *
         * @param distance
         * @return Returns the Builder object
         */
        public Builder setVpDistance(double distance) {
            //Correctness check, make sure they are positive.
            if (Util.alignZero(distance) <= 0)
                throw new IllegalArgumentException("The distance should be bigger than 0");
            camera.distance = distance;
            return this;
        }

        public Camera build() {
            final String MissingRenderingArgument  = "Missing rendering argument";
            final String Camera  = "Camera";

            //Correctness check, make sure that the vectors and the points are not "NULL".
            if (camera.p0 == null) throw new MissingResourceException(MissingRenderingArgument, Camera, "p0");
            if (camera.vUp == null) throw new MissingResourceException(MissingRenderingArgument, Camera, "vUp");
            if (camera.vTo == null) throw new MissingResourceException(MissingRenderingArgument, Camera, "vTo");
            //Correctness check, make sure that the values are not 0.
            if (Util.alignZero(camera.width) == 0)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "width");
            if (Util.alignZero(camera.height) == 0)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "height");
            if (Util.alignZero(camera.distance) == 0)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "distance");
            //Correctness check, make sure that the values are positives.
            if (Util.alignZero(camera.width) < 0) throw new IllegalArgumentException("The width can't be negative");
            if (Util.alignZero(camera.height) < 0) throw new IllegalArgumentException("The height can't be negative");
            if (Util.alignZero(camera.distance) < 0)
                throw new IllegalArgumentException("The distance can't be negative");
            //Correctness check, make sure that vTo and vUp are orthogonal.
            if (!isZero(camera.vUp.dotProduct(camera.vTo)))
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            //Correctness check, make sure that vTo, vUp and vRight are normalized.
            if (!Util.isZero(camera.vTo.lengthSquared() - 1))
                throw new IllegalArgumentException("The vTo vector must be normalized");
            if (!Util.isZero(camera.vUp.lengthSquared() - 1))
                throw new IllegalArgumentException("The vUp vector must be normalized");
            if (!Util.isZero(camera.vRight.lengthSquared() - 1))
                throw new IllegalArgumentException("The vRight vector must be normalized");
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException ignore) {
                return null;
            }
        }
    }
}

