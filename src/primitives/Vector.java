package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(this.xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("Zero Vector is illegal");
        }
    }

    public Vector(Double3 XYZ) {
        super(XYZ);
        if(this.xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector is illegal");
    }

    public Vector add(Vector vec){
        return new Vector(this.xyz.add(vec.xyz));
    }






}
