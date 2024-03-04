package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;
import static primitives.Util.random;
/**
 * Responsible for the points of the pixel,
 * through which color test rays will pass
 * @author Yaniv and Ahuvya.
 */
public class Blackboard {
    //initialization value
    public static final Blackboard oneRay = new Blackboard(1, 0, 0);

    private int rootNumberOfRays = 1;

    // Width of the grid
    private double width = 0;
    // height of the grid
    private double height = 0;

    /**
     * List to store the points on the grid
     */
    public List<Point> grid;

    /**
     * setter and getter
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setRootNumberOfRays(int rootNumberOfRays) {
        this.rootNumberOfRays = (rootNumberOfRays == 0 ? 1 : rootNumberOfRays);
    }

    public void setGridSides(Point pCenter, Vector vUp, Vector vRight, double k) { //k=1 in the beginning.

        grid = new LinkedList<Point>();
        double Ry = (height / rootNumberOfRays) / k;
        double Rx = (width / rootNumberOfRays) / k;
        //Calculate the coordinate
        Point pIJ = pCenter.add(vUp.scale(Ry/2)).add(vRight.scale(Rx/2));
        grid.addLast(pIJ);
    }



    /**
     * Constructor
     *
     * @param rootNumberOfRays The number of rays originating from the root point.
     * @param width            - The width of the  grid.
     * @param height           - The height of the  grid.
     */
    public Blackboard(int rootNumberOfRays, double width, double height) {
        this.rootNumberOfRays = (rootNumberOfRays == 0 ? 1 : rootNumberOfRays);
        this.width = width;
        this.height = height;
    }

    /**
     * checks if there is ray beam to create.
     *
     * @return if there is ray beam
     */
    public boolean rayBeam() {
        return (rootNumberOfRays != 1);
    }

    /**
     * Creates a grid of points through which the rays passed
     *
     * @param pCenter
     * @param vUp
     * @param vRight
     */
    public void setGrid(Point pCenter, Vector vUp, Vector vRight) {
        if (rootNumberOfRays == 1)
            grid = List.of(pCenter);
        else {
            grid = new LinkedList<Point>();
            double Ry = height / rootNumberOfRays;
            double Rx = width / rootNumberOfRays;
            for (int i = 0; i < rootNumberOfRays; i++) {
                for (int j = 0; j < rootNumberOfRays; j++) {
                    //Calculate the coordinates
                    double yI = -(i - (double) (rootNumberOfRays - 1) / 2) * Ry - Ry / 2; //Calculate the coordinates
                    double xJ = (j - (double) (rootNumberOfRays - 1) / 2) * Rx - Rx / 2;
                    Point pIJ = pCenter;
                    //move randomly
                    double yMove = random(0, Ry);
                    double xMove = random(0, Rx);
                    if (!isZero(xJ) || !isZero(xMove))
                        pIJ = pIJ.add(vRight.scale(xJ + xMove));
                    if (!isZero(yI) || !isZero(yMove))
                        pIJ = pIJ.add(vUp.scale(yI + yMove));
                    grid.addLast(pIJ);
                }
            }
        }

    }
}
