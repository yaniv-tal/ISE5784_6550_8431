package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * The class implements a camera.
 * @author Yaniv and Ahuvya.
 */
public class Camera implements Cloneable {
    public boolean useAntiAliasing = false;
    private Point p0;
    private Vector vTo, vUp, vRight;
    private double width = 0.0, height = 0.0, distance = 0.0;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private Blackboard blackboardAntiAliasing = Blackboard.oneRay;

    /** Pixel manager for supporting:
     * <ul>
     * <li>multi-threading</li>
     * <li>debug print of progress percentage in Console window/tab</li>
     * <ul>
     */
    private PixelManager pixelManager;

    private int threadsCount = 0;
    private double printInterval = 0;

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
     * @return a new Builder object.
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * give the center point of pixel i,j
     *
     * @param nX number of columns
     * @param nY number of rows
     * @param j  pixel's column
     * @param i  pixel's row
     * @return the point
     */
    private Point pixelCenter(int nX, int nY, int j, int i) {
        Point pCenter = p0.add(vTo.scale(distance));

        //Ratio
        double Ry = height / nY;
        double Rx = width / nX;

        //Pixel[i,j] center
        double yI = -(i - (double) (nY - 1) / 2) * Ry;
        double xJ = (j - (double) (nX - 1) / 2) * Rx;

        Point pIJ = pCenter;
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));

        return pIJ;
    }

    /**
     * creates ray that go threw (i,j) indexes.
     *
     * @param nX - Represents the amount of columns (row width)
     * @param nY - represents the number of rows (column height).
     * @param j  - column number
     * @param i  - row number
     * @return the created ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return new Ray(p0, pixelCenter(nX, nY, j, i).subtract(p0));
    }

    public void setAliasingRays(int rootNumberOfRays,double width1, double height1) {

        blackboardAntiAliasing = new Blackboard(rootNumberOfRays, width1, height1);
    }

    /**
     * The class implements a Builder Design Pattern.
     *
     * @author Yaniv and Ahuvya.
     */
    public static class Builder {
        private final Camera camera = new Camera();

        /**
         * Determining the location of the camera
         *
         * @param location
         * @return Returns the Builder object
         */
        public Builder setLocation(Point location) {
            camera.p0 = location;
            return this;
        }

        /**
         * set the ImageWriter
         *
         * @param imageWriter
         * @return Returns the Builder object
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            this.camera.imageWriter = imageWriter;
            return this;
        }


        /**
         * set the RayTracer
         * @param rayTracer
         * @return Returns the Builder object
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
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

        public Builder setUseAntiAliasing(boolean useAntiAliasing, int rootNumberOfRays , double width1, double height1) {
            this.camera.useAntiAliasing = useAntiAliasing;
            if(useAntiAliasing)
                camera.setAliasingRays(rootNumberOfRays,width1,height1);
            return this;
        }

        public boolean getUseAntiAliasing() {
            return this.camera.useAntiAliasing;
        }

        public Builder setUseSoftShadows(boolean useSoftShadows, int rootNumberOfRays,double width1, double height1) {
            this.camera.rayTracer.useSoftShadow = useSoftShadows;
            if(useSoftShadows)
                camera.rayTracer.blackboardSoftShadows = new Blackboard(rootNumberOfRays, width1, height1);
            return this;
        }

        public Builder setThreadsCount(int threadsCount) {
            this.camera.threadsCount= threadsCount;
            return this;
        }

        public Builder setPrintInterval(double printInterval) {
            this.camera.printInterval= printInterval;
            return this;
        }


        public boolean getUseSoftShadows() {
            return this.camera.rayTracer.useSoftShadow;
        }

        /**
         * build the camera and Checking the correctness
         * @return camera
         */
        public Camera build() {
            final String MissingRenderingArgument = "Missing rendering argument";
            final String Camera = "Camera";

            //Correctness check, make sure that the vectors and the points are not "NULL".
            if (camera.p0 == null)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "p0");
            if (camera.vUp == null)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "vUp");
            if (camera.vTo == null)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "vTo");
            if (camera.rayTracer == null)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "rayTracer");
            if (camera.imageWriter == null)
                throw new MissingResourceException(MissingRenderingArgument, Camera, "imageWriter");
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

    /**
     * Performs the rendering by calling the cast ray function
     *
     * @return this camera
     */
    public Camera renderImage() {
        if (rayTracer == null)
            throw new MissingResourceException("Missing rendering argument", "Camera", "rayTracer");
        if (imageWriter == null)
            throw new MissingResourceException("Missing rendering argument", "Camera", "imageWriter");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        pixelManager = new PixelManager(nY, nX, printInterval);
        //Goes through the entire vp and calls the cast ray function that will color the pixel
        if (threadsCount == 0)
            for (int i = 0; i < nY; ++i)
                for (int j = 0; j < nX; ++j)
                    castRay(nX, nY, j, i);
        else { // see further... option 2
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0) // add appropriate number of threads
                threads.add(new Thread(() -> { // add a thread with its code
                    PixelManager.Pixel pixel; // current pixel(row,col)
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null)
                        // cast ray through pixel (and color it – inside castRay)
                        castRay(nX, nY, pixel.col(), pixel.row());
                }));
            // start all the threads
            for (var thread : threads) thread.start();
            // wait until all the threads have finished
            try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}
        }
        return this;
    }

    /**
     * create longitude and latitude lines
     *
     * @param interval - The size
     * @param color    - the color of the lines
     * @return this camera
     */
    public Camera printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new MissingResourceException("Missing rendering argument", "Camera", "imageWriter");
        }
        //We will go by the pixels we received and create longitude and latitude lines
        for (int j = 0; j < imageWriter.getNy(); j++) {
            for (int i = 0; i < imageWriter.getNx(); i++) {
                if (isZero(j % interval) || isZero(i % interval) || i == (imageWriter.getNx() - 1) || j == (imageWriter.getNy() - 1))
                    imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * Calling the method "writeToImage" of "imageWriter"
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("Missing rendering argument", "Camera", "imageWriter");
        }
        imageWriter.writeToImage(); //delegate to image writer
    }

    /**
     * cast a ray through pixel and set color to the pixel
     *
     * @param nX number of columns
     * @param nY number of rows
     * @param j  pixel's column
     * @param i  pixel's row
     */
    private void castRay(int nX, int nY, int j, int i) {
        Color color;
        if (blackboardAntiAliasing.rayBeam() && useAntiAliasing) {

            blackboardAntiAliasing.setGrid(pixelCenter(nX, nY, j, i), vUp, vRight);
            color = colorAverage(blackboardAntiAliasing.grid, p0);
        }
        else {
            Ray ray = constructRay(nX, nY, j, i);
            color = rayTracer.traceRay(ray);
        }
        imageWriter.writePixel(j, i, color);
        pixelManager.pixelDone();
    }

    /**
     * calculate the average color created by the ray beam
     * @param list The list of points
     * @param point Camera location
     * @return the average color
     */
    private Color colorAverage(List<Point> list, Point point) {
        Color color = Color.BLACK;
        for (Point p : list)
            color = color.add(rayTracer.traceRay(new Ray(point, p.subtract(point))));
        return color.reduce(list.size());
    }

}
