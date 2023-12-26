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

    @Override
    public Vector add(Vector vec){
        return new Vector(this.xyz.add(vec.xyz));
    }

    public Vector scale(double number){
        return new Vector(this.xyz.scale(number));
    }

    public double dotProduct(Vector vec){
        return this.xyz.d1*vec.xyz.d1 + this.xyz.d2*vec.xyz.d2 + this.xyz.d3*vec.xyz.d3;
    }

    public Vector crossProduct(Vector vec){
        return new Vector(this.xyz.d2*vec.xyz.d3 - this.xyz.d3*vec.xyz.d2,this.xyz.d3*vec.xyz.d1 - this.xyz.d1*vec.xyz.d3,xyz.d1*vec.xyz.d2- xyz.d2*vec.xyz.d1);
    }

    public double lengthSquared(){
        return dotProduct(this);
    }

    public double length(){
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize(){
        return scale(1/length());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Vector other && super.equals(other);
    }
    @Override
    public String toString() { return "->" + super.toString(); }
}
