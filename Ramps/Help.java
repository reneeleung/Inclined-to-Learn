import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Help extends JPanel{
  //Properties
  BufferedImage image = null;
  
  
  //Methods
  public void paintComponent(Graphics g){
    Graphics2D graph = (Graphics2D)g;
    graph.drawImage(image, 0, 0, null);
  }
  
  //Constructor
  public Help(){
    super();
    try{
      image = ImageIO.read(getClass().getResource("help.png"));
    }catch(IOException e){
    }
  }
  
}