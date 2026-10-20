package presentation;

import java.awt.*;
import javax.swing.*;

//Esta clase es la encargada de mostrar la venta
public class GameFrame extends JFrame {
    private static final Image img = Toolkit.getDefaultToolkit().getImage("resources\\assets\\images\\icon.png");
    //El panel principal donde se va a mostrar la fisica de la pelota
    private GamePanel panel;

    public GameFrame(){
        panel = new GamePanel();
        this.add(panel);                                    //Se añade el panel al frame
        this.setTitle("Rebote"); 
        this.setResizable(false);                           //No permite modificar el tamaño de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setBackground(Color.BLACK);
        this.pack();                                        // Toms las dimensiones del panel que se añadio 
        this.setIconImage(img);
        this.setLocationRelativeTo(null);                   //Centra la ventana
        this.setVisible(true);  //Inicio hilo EDT
    }

}
