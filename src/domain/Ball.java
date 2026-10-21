package domain;

import presentation.GamePanel;

public class Ball {
    public static final double GRAVITY = -0;
    public static final double WALL_FRICTION = 0.8;
    public static final double AIR_FRICTION = 1;
    public static final double FLOOR_FRICTION = 0.55;


    private double xPosition;
    private double yPosition;
    private Vector2D velocity;
    private int diameter;

    public Ball(int xPosition, int yPosition, int xVelocity, int yVelocity, int diameter){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        velocity = new Vector2D(xVelocity, yVelocity);
        this.diameter = diameter;
    }

    public void move(){
        xPosition += velocity.getX();
        yPosition += velocity.getY();

        if(xPosition + diameter > GamePanel.SCREEN_WIDHT){
            xPosition = GamePanel.SCREEN_WIDHT - diameter;
            velocity.setX(velocity.getX() * -WALL_FRICTION);
        }else if(xPosition < 0){
            xPosition = 0;
            velocity.setX(velocity.getX() * -WALL_FRICTION);
        }else{
            velocity.setX(velocity.getX() * AIR_FRICTION);

            if (Math.abs(velocity.getX()) < 1.9) velocity.setX(0);
        }


        if (yPosition + diameter > GamePanel.SCREEN_HEIGHT) {
            yPosition = GamePanel.SCREEN_HEIGHT - diameter;  
                if (Math.abs(velocity.getY()) < 1) {
                    velocity.setY(0);                
                } else {
                    velocity.setY(velocity.getY() * -FLOOR_FRICTION);
                }
        } else if (yPosition < 0) {
            yPosition = 0;  
            velocity.setY(-velocity.getY());                                
        } else {
            velocity.setY(velocity.getY() - GRAVITY);
        }
    }

    public Vector2D center(){
        return new Vector2D(xPosition+diameter/2, yPosition+diameter/2);
    }

    public void translate(double dx, double dy){
        xPosition += dx;
        yPosition += dy;
    }


    public void detectColission(){

    }

    public Vector2D getVelocity(){
        return velocity;
    }

    public void setVelocity(Vector2D v){
        this.velocity = v;
    }

    public int getXPosition(){
        return (int) xPosition;
    }

    public int getYPosition(){
        return (int) yPosition;
    }

    public int getDiameter(){
        return diameter;
    }
}
