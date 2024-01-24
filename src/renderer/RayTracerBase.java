package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract class for ray intersection color calculations.
 * @author Yaniv and Ahuvya.
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * copy constructor.
     * @param scene
     */
    public RayTracerBase(Scene scene){
        this.scene = scene;
    }
    public abstract Color traceRay(Ray ray);
}
