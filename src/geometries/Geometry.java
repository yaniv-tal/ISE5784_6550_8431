package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * interface of geometric shapes.
 * @author Yaniv and Ahuvya.
 */
public abstract class Geometry extends Intersectable{
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * getter function for the field emission.
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * getter function for the field material.
     * @return the material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter function for the field material.
     * @param material presents the material to set.
     * @return the geometry.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * setter function for the field emission.
     * @param emission presents the emission to set.
     * @return the geometry.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @param point represents the getting point.
     * @return The normal (vertical) vector to the body at this point.
     */
    public abstract Vector getNormal(Point point);
}

