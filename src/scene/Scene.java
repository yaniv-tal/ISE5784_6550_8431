package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * The class implements a scene.
 * @author Yaniv and Ahuvya.
 */
public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();

    /**
     * Constructor for Scene.
     * @param name for the name of the scene.
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * setter for the background.
     * @param background for the background of the scene.
     * @return the scene.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter for the ambient light.
     * @param ambientLight for the ambient light of the scene.
     * @return the scene.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

}
