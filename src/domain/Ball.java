package domain;

import presentation.GamePanel;

public class Ball {
    private int xPosition;
    private int yPosition;
    private int xVelocity;
    private int yVelocity;
    private int gravity;
    private int diameter;

    public Ball(int xPosition, int yPosition, int xVelocity, int yVelocity, int diameter){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.gravity = 10;
        this.diameter = diameter;
    }

    public void move(){
        if(xPosition + diameter > GamePanel.SCREEN_WIDHT || xPosition < 0){
            xVelocity = -xVelocity;
        }
        if(yPosition + diameter > GamePanel.SCREEN_HEIGHT || yPosition < 0){
            yVelocity = -yVelocity;
        }

        xPosition += xVelocity;
        yPosition += yVelocity;
    }
    public void detectColission(){

    }

    public int getXPosition(){
        return xPosition;
    }

    public int getYPosition(){
        return yPosition;
    }

    public int getDiameter(){
        return diameter;
    }
}
