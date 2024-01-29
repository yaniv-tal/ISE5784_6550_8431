package lighting;

import primitives.Color;

/**
 * The class implements lights.
 * @author Yaniv and Ahuvya.
 */
public abstract class Light {
    protected Color intensity;

    /**
     * constructor of the class.
     * @param intensity represents the received light intensity.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for the field 'intensity'.
     * @return the light intensity.
     */
    public Color getIntensity() {
        return intensity;
    }


}
