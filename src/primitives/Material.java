package primitives;

/**
 *
 */
public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;

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
}
