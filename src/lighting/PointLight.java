package lighting;

import geometries.Plane;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
import renderer.Blackboard;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.random;

/**
 * A class for representing point light.
 * @author Yaniv and Ahuvya.
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    //Factors for attenuation with distance (d)
    private double kC = 1;
    //Factors for attenuation with distance (d)
    private double kL = 0;
    //Factors for attenuation with distance (d)
    private double kQ = 0;
    /**
     * square edge size parameter
     */
    private int lengthOfTheSide = 9;

    /**
     * The amount of rays of the soft shadow.
     */
    public static int softShadowsRays = 36;


    protected double narrowBeam = 1;

    private Blackboard blackboard = Blackboard.oneRay;

    /**
     * constructor
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * set function for KS
     * @param kC
     * @return the object
     */
    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }
    /**
     * set function for kl
     * @param kL
     * @return the object
     */
    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }
    /**
     * set function for kQ
     * @param kQ
     * @return the object
     */
    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity.scale(1 / (kC + kL * position.distance(p) + kQ * position.distanceSquared(p)));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
    /**
     * set function for NarrowBeam
     * @param narrowBeam
     * @return the object
     */
    public PointLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }

    @Override
    public List<Vector> getLBeam(Point p) {
//        if (lengthOfTheSide == 0) return List.of(getL(p).scale(-1));
//        List<Vector> vectors = new LinkedList<>();
//        Vector vTo = getL(p);
//        Vector vUp = new Vector(vTo.getZ(), 0 ,-vTo.getX()).normalize();
//        Vector vRight = vTo.crossProduct(vUp).normalize();
//        blackboard.setRootNumberOfRays(3);
//        blackboard.setWidth(9);
//        blackboard.setHeight(9);
//
//        blackboard.setGrid(position,vUp,vRight);
//        for (Point i : blackboard.grid)
//            vectors.add(i.subtract(p).normalize());
//        return vectors;


//        if (lengthOfTheSide == 0) return List.of(getL(p));
//        List<Vector> vectors = new LinkedList<>();
//        Vector v0, v1;
//        Vector vTo = getL(p);
//        Vector vUp = new Vector(vTo.getZ(), 0 ,-vTo.getX()).normalize();
//        Vector vRight = vTo.crossProduct(vUp).normalize();
//        double divided = Math.sqrt(softShadowsRays);
//        Point startPoint = position.add(vTo.normalize().scale(-lengthOfTheSide / 2.0))
//                .add(vRight.normalize().scale(-lengthOfTheSide / 2.0));
//
//        // A loop that runs as the number of vectors and in each of its runs it brings a vector around the lamp
//        for (double i = 0; i < lengthOfTheSide; i += lengthOfTheSide / divided) {
//            for (double j = 0; j < lengthOfTheSide; j += lengthOfTheSide / divided) {
//                v0 = vTo.normalize()
//                        .scale(random(i, i + lengthOfTheSide / divided));
//                v1 = vRight.normalize()
//                        .scale(random(j, j + lengthOfTheSide / divided));
//                vectors.add(p.subtract(startPoint.add(v0).add(v1)).normalize());
//            }
//        }




        if (lengthOfTheSide == 0) return List.of(getL(p));
        List<Vector> vectors = new LinkedList<>();
        Vector v0, v1;
        // A variable that tells how many divide each side
        double divided = Math.sqrt(softShadowsRays);
        // plane of the light
        Plane plane = new Plane(position, getL(p));
        // vectors of the plane
        List<Vector> vectorsOfThePlane = plane.findVectorsOfPlane();

        // Starting point of the square around the lighting
        Point startPoint = position.add(vectorsOfThePlane.get(0).normalize().scale(-lengthOfTheSide / 2.0))
                .add(vectorsOfThePlane.get(1).normalize().scale(-lengthOfTheSide / 2.0));

        // A loop that runs as the number of vectors and in each of its runs it brings a vector around the lamp
        for (double i = 0; i < lengthOfTheSide; i += lengthOfTheSide / divided) {
            for (double j = 0; j < lengthOfTheSide; j += lengthOfTheSide / divided) {
                v0 = vectorsOfThePlane.get(0).normalize()
                        .scale(random(i, i + lengthOfTheSide / divided));
                v1 = vectorsOfThePlane.get(1).normalize()
                        .scale(random(j, j + lengthOfTheSide / divided));
                vectors.add(p.subtract(startPoint.add(v0).add(v1)).normalize());
            }
        }
        return vectors;
    }
}

