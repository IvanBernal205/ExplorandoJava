package domain;

import presentation.GamePanel;

public class Ball {
    public static final double GRAVITY = -0.5;
    public static final double WALL_FRICTION = 0.8;
    public static final double AIR_FRICTION = 0.99;
    public static final double FLOOR_FRICTION = 0.55;


    private double xPosition;
    private double yPosition;
    private double xVelocity;
    private double yVelocity;
    private int diameter;

    public Ball(int xPosition, int yPosition, int xVelocity, int yVelocity, int diameter){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.diameter = diameter;
    }

    public void move(){
        xPosition += xVelocity;
        yPosition += yVelocity;

        if(xPosition + diameter > GamePanel.SCREEN_WIDHT){
            xPosition = GamePanel.SCREEN_WIDHT - diameter;
            xVelocity = -xVelocity * WALL_FRICTION;
        }else if(xPosition < 0){
            xPosition = 0;
            xVelocity = -xVelocity * WALL_FRICTION;
        }else{
            xVelocity *= 0.99;
            if (Math.abs(xVelocity) < 1.9) xVelocity = 0;
        }


        if (yPosition + diameter > GamePanel.SCREEN_HEIGHT) {
            yPosition = GamePanel.SCREEN_HEIGHT - diameter;  
                if (Math.abs(yVelocity) < 1) {
                    yVelocity = 0;                
                } else {
                    yVelocity = -yVelocity * FLOOR_FRICTION;
                }
        } else if (yPosition < 0) {
            yPosition = 0;                                  
            yVelocity = -yVelocity;
        } else {
            yVelocity -= GRAVITY;
        }
    }
    public void detectColission(){

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
