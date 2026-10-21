package domain;

public class Vector2D {
    private double  x;
    private double  y;

    public Vector2D(double  x, double  y){
        this.x = x;
        this.y = y;
    }
    
    public void scalarProduct( int scalar){
        this.x = x*scalar;
        this.y = y*scalar;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double setX(double x){
        return this.x = x;
    }
    public double setY(double y){
        return this.y = y;
    }


    public static Vector2D subtract(Vector2D v1, Vector2D v2){
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public static double dot(Vector2D v1, Vector2D v2){
        return v1.x*v2.x + v1.y*v2.y;
    }

    public static Vector2D scalar(Vector2D v, double scalar){
        return new Vector2D(v.x*scalar, v.y*scalar);
    }

    public static Vector2D add(Vector2D v1, Vector2D v2){
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public static double length(Vector2D v){
        return Math.sqrt(v.x*v.x + v.y*v.y);
    }
}
