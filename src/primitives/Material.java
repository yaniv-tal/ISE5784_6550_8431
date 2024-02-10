package primitives;

/**
 * class for the material of the object.
 * @author Yaniv and Ahuvya.
 */
public class Material {
    //Material properties
    public Double3 kD = Double3.ZERO;
    //Material properties
    public Double3 kS = Double3.ZERO;
    //the shine
    public int nShininess = 0;
    //The attenuation coefficient of transparency
    public Double3 kT = Double3.ZERO;
    //The attenuation coefficient of reflection
    public Double3 kR = Double3.ZERO;

    /**
     * setter for the field 'kD'.
     * @param kD
     * @return the Material.
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setKD(Double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter for the field 'kS'.
     * @param kS
     * @return the Material.
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setKS(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    /**
     * setter for the field 'nShininess'.
     * @param nShininess
     * @return the Material.
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
    /**
     * setter for the field 'kT'.
     * @param kT represents the attenuation coefficient of transparency
     * @return the Material.
     */
    public Material setKT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Material setKT(Double kT) {
        this.kT = new Double3(kT);
        return this;
    }
    /**
     * setter for the field 'kR'.
     * @param kR represents the attenuation coefficient of reflection
     * @return the Material.
     */
    public Material setKR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    public Material setKR(Double kR) {
        this.kR = new Double3(kR);
        return this;
    }
}
