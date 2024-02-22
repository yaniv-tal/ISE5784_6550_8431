package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;
import static primitives.Util.random;

public class Blackboard {
    public static final Blackboard oneRay = new Blackboard(0, 0, 0);
    private int rootNumberOfRays = 1;

    // Width of the grid
    private double width = 0;
    // height of the grid
    private double height = 0;

    /**
     * List to store the points on the grid
     */
    public List<Point> grid;

    private boolean useAntiAliasing  = false;

    public Blackboard setUseAntiAliasing(boolean useAntiAliasing) {
        this.useAntiAliasing = useAntiAliasing;
        return this;
    }

    public boolean getUseAntiAliasing() {
        return useAntiAliasing;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Constructs a Blackboard
     *
     * @param rootNumberOfRays The number of rays originating from the root point.
     * @param width            The width of the  grid.
     * @param height           The height of the  grid.
     */
    public Blackboard(int rootNumberOfRays, double width, double height) {
        this.rootNumberOfRays = (rootNumberOfRays == 0 ? 1 : rootNumberOfRays);
        this.width = width;
        this.height = height;
    }

    public void setRootNumberOfRays(int rootNumberOfRays) {
        this.rootNumberOfRays = (rootNumberOfRays == 0 ? 1 : rootNumberOfRays);
    }

    public void setGrid(Point pCenter, Vector vUp, Vector vRight) {
        if (rootNumberOfRays == 1 || !useAntiAliasing)
            grid = List.of(pCenter);
        else {
            grid = new LinkedList<Point>();

            //Ratio
            double Ry = height / rootNumberOfRays;
            double Rx = width / rootNumberOfRays;

            for (int i = 0; i < rootNumberOfRays; i++) {
                for (int j = 0; j < rootNumberOfRays; j++) {
                    double yI = -(i - (double) (rootNumberOfRays - 1) / 2) * Ry - Ry / 2;
                    double xJ = (j - (double) (rootNumberOfRays - 1) / 2) * Rx - Rx / 2;
                    Point pIJ = pCenter;
                    double yMove = random(0, Ry);
                    double xMove = random(0, Rx);
                    if (!isZero(xJ) || !isZero(xMove))
                        pIJ.add(vRight.scale(xJ + xMove));
                    if (!isZero(yI) || !isZero(yMove))
                        pIJ = pIJ.add(vUp.scale(yI + yMove));
                    grid.addLast(pIJ);
                }
            }
        }

    }
}
