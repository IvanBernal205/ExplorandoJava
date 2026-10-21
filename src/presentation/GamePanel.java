package presentation;

import domain.Ball;
import domain.Utilidades;
import domain.Vector2D;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    
    public static final int SCREEN_WIDHT = 1300;
    public static final int SCREEN_HEIGHT = 700;
    public static final Dimension SIZE = new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT);
    public static final int DIAMETER = 40;
    Image image;
    private Thread gameThread;
    private Graphics graphics;
    private Ball ball;
    private Ball testBall;

    public GamePanel(){
        int xV = Utilidades.randomNumberInARange(-5, 5);
        int yV = Utilidades.randomNumberInARange(-5, 5);


        ball = new Ball(0,350, 5, -0, DIAMETER);
        testBall = new Ball(1260, 350, -5, 0, DIAMETER);
        this.setPreferredSize(SIZE);

        gameThread = new Thread(this);
        gameThread.start(); // Inicio del Hilo que ejecuta run()
    }

    @Override
    // Este metodo es llamado por setVisible(true) o tambien por repaint(), y muchos mas.
    public void paint(Graphics g){                  
        image = createImage(getWidth(),getHeight());// Crea un lienzo vacio de 1300*700
        graphics = image.getGraphics();             // Obtiene acceso para pintar en el lienzo (pincel)
        draw(graphics);                             // Le da el pincel a todos los que quiera dibujar en el lienzo 
        g.drawImage(image, 0, 0, this);             // Copia la imagen ya terminada a la pantalla, desde la esquina superior izquierda (0, 0)
    }

    public void draw(Graphics g){
        drawBall(g, ball);
        drawBall(g, testBall);
    }

    private void drawBall(Graphics g, Ball ball){
        g.setColor(Color.WHITE);
        g.fillOval(ball.getXPosition(), ball.getYPosition(), ball.getDiameter(), ball.getDiameter());
    }

    @Override
    // Metodo llamado por el gameThread
    // Permite calcular acciones (move) a 60 ticks por segunfo y ademas refrescar (repaint) la pantalla a 60FPS
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while (true) { 
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                detectColission();
                repaint();  // Le pide al EDT que llame a paint() (no pinta aquí, solo deja la solicitud en cola)
                delta--;
            }
        }
    }

    public void move(){
        ball.move();
        testBall.move();
    }

    public void detectColission(){
        Vector2D c1 = ball.center();
        Vector2D c2 = testBall.center();
        Vector2D n = Vector2D.subtract(c1, c2);
        double distance = Vector2D.length(n);

        if (distance <= DIAMETER && distance > 0) {
            Vector2D nUnitary = Vector2D.scalar(n, 1 / distance);

            double overlap = DIAMETER - distance;
            Vector2D push = Vector2D.scalar(nUnitary, overlap / 2);
            ball.translate(push.getX(), push.getY());
            testBall.translate(-push.getX(), -push.getY());

            Vector2D vR = Vector2D.subtract(ball.getVelocity(), testBall.getVelocity());
            double dot = Vector2D.dot(vR, nUnitary);
            if (dot < 0) {
                double J = -(1 + 0.50) * dot / (1 +50);
                ball.setVelocity(Vector2D.add(ball.getVelocity(), Vector2D.scalar(nUnitary, J)));
                testBall.setVelocity(Vector2D.subtract(testBall.getVelocity(), Vector2D.scalar(nUnitary,50*J)));
            }
        }
    }

}
