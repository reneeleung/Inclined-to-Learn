import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Scores extends JPanel{
  //Properties
  BufferedImage image = null;
  BufferedReader thefile = null;
  BufferedReader thefile2 = null;
  String strTemp;
  int intLine = 0;
  int intMaxRows;
  int intCount = 0;
  int intLeft;
  int intRight;
  String strHighscores[];
  String strLine = "";
  int intCount2 = 0;
  
  
  //Methods
  public void paintComponent(Graphics g){
    Graphics2D graph = (Graphics2D)g;
    graph.drawImage(image, 0, 0, null);
    try{
      thefile = new BufferedReader(new FileReader("highscores.txt"));
    }catch(FileNotFoundException e){
    }
    try{
      thefile2 = new BufferedReader(new FileReader("highscores.txt"));
    }catch(FileNotFoundException e){
    }
    intLine = 0;
    intCount = 0;
    intCount2 = 0;
    strLine = "";
    //Reading and counting lines from highscores file
    while(strLine != null){
      try{
        strLine = thefile.readLine();
        if(strLine != null){
          intLine++;
        }
      }catch(IOException e){
        System.out.println("Unable to read from file");
        strLine = "";
      }
    }
    try{
      thefile.close();
    }catch(IOException e){
    }
    strHighscores = new String[intLine];
    
    //Loading scores into arrays
    for(intCount = 0; intCount < intLine; intCount++){
      try{
        strHighscores[intCount] = thefile2.readLine();
      }catch(IOException e){
        strHighscores[intCount] = "";
      }
      
    }
    try{
      thefile2.close();
    }catch(IOException e){
    }
    //Bubble sorting scores
    intCount = 0;
    for(intCount2 = 0; intCount2 < intLine - 1; intCount2++){
      for(intCount = 0; intCount < intLine - 1; intCount++){
        intLeft = Integer.parseInt(strHighscores[intCount]);
        intRight = Integer.parseInt(strHighscores[intCount + 1]);
        if(intLeft < intRight){
          strTemp = strHighscores[intCount];
          strHighscores[intCount] = strHighscores[intCount + 1];
          strHighscores[intCount + 1] = strTemp;
        }
      }
    }
    //Loading scores onto graphics
    intCount2 = 0;
    if(intLine > 14){
      intLine = 14;
    }
    for(intCount2 = 0; intCount2 < intLine; intCount2++){
      graph.drawString(strHighscores[intCount2], 270, intCount2 * 30 + 130);
    }
  }
  
  //Constructor
  public Scores(){
    super();
    try{
      image = ImageIO.read(getClass().getResource("scoresbackground.png"));
    }catch(IOException e){
    }
    try{
      thefile = new BufferedReader(new FileReader("highscores.txt"));
    }catch(FileNotFoundException e){
    }
    try{
      thefile2 = new BufferedReader(new FileReader("highscores.txt"));
    }catch(FileNotFoundException e){
    }
  }
  
}