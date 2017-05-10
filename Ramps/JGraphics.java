import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class JGraphics extends JPanel{
  //Properties
  int intTriX = 100;
  int intTriY = 300;
  int intAngle = 45;
  int intCo = 0;
  int intFa = 0;
  int intMass = 25;
  double dblGravity = 9.81;
  double dblAcceleration;
  double dblTime;
  String strImage = "earth";
  int intRectDX = intTriX;
  int intRectDY = intTriY - (int)(200 * Math.sin((Math.toRadians(intAngle))));
  int intRectAX = intRectDX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(intAngle)));;
  int intRectAY = intRectDY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(intAngle)));
  int intRectCX = intRectDX + (int)(30 * Math.cos(Math.toRadians(intAngle)));;
  int intRectCY = intRectDY + (int)(30 * Math.sin(Math.toRadians(intAngle)));
  int intRectBX = intRectCX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(intAngle)));
  int intRectBY = intRectCY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(intAngle)));
  boolean blnanimation = false;
  boolean blnquiz = false;
  BufferedImage image = null;
  String strLoadQ[] = new String[120];
  int intExtraSin = 0;
  int intExtraCos = 1;
  
  //Methods
  public void paintComponent(Graphics g){
    Graphics2D graph = (Graphics2D)g;
    g.clearRect(0, 0, 640, 600);
    try{
      image = ImageIO.read(getClass().getResource(strImage+".png"));
    }catch(IOException e){
    }
    graph.drawImage(image, 0, 0, null);
    //Drawing of the ramp set up
    g.drawLine(intTriX, intTriY, intTriX + (int)(200 * Math.cos((Math.toRadians(intAngle)))), intTriY);
    g.drawLine(intTriX, intTriY, intTriX, intTriY - (int)(200 * Math.sin((Math.toRadians(intAngle)))));
    g.drawLine(intTriX, intTriY - (int)(200 * Math.sin((Math.toRadians(intAngle)))), intTriX + (int)(200 * Math.cos((Math.toRadians(intAngle)))), intTriY);
    g.drawString("Fnet = Fgx - Ff + Fa", 400, 200);
    g.drawString("Fnet = Fgx - Fn * £g + Fa", 400, 220);
    g.drawString("  ma = mgsin"+intAngle+" - (mgcos"+intAngle+")(£g) + Fa", 400, 240);
    g.drawString("   a = [mgsin"+intAngle+" - (mgcos"+intAngle+")(£g) + Fa] ¡Ò m", 400, 260);
    //Calculations
    dblAcceleration = (intMass * dblGravity * Math.sin((Math.toRadians(intAngle))) - dblGravity / 10 * intMass * Math.cos((Math.toRadians(intAngle))) * intCo + intFa) / intMass;
    dblAcceleration = Math.round(dblAcceleration * 100.0)/100.0;
    
    g.drawString("   a = "+dblAcceleration, 400, 280);
    g.drawString("   d = v1t + 0.5at^2", 400, 300);
    g.drawString("   t = ¡Ô(d ¡Ò 0.5 ¡Ò "+dblAcceleration+")", 400, 320);
    dblTime = Math.sqrt(200/0.5/dblAcceleration);
    dblTime = Math.round(dblTime * 100.0)/100.0;
    if(dblAcceleration < 0){
      dblTime = 0;
    }else if(dblAcceleration == 0){
      dblTime = 0;
    }
    g.drawString("   t = "+dblTime, 400, 340);
    //Drawing of the box
    g.drawLine(intRectDX, intRectDY, intRectAX, intRectAY);
    g.drawLine(intRectAX, intRectAY, intRectBX, intRectBY);
    g.drawLine(intRectBX, intRectBY, intRectCX, intRectCY);
    //Ending animation at the end of the ramp
    if(intRectCX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblTime) > intTriX + (int)(200 * Math.cos((Math.toRadians(intAngle)))) || intRectCY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblTime) > intTriY){
      blnanimation = false;
      intRectCX = intTriX + (int)(200 * Math.cos((Math.toRadians(intAngle))));
      intRectCY = intTriY;
      intRectDX = intRectCX - (int)(30 * Math.cos((Math.toRadians(intAngle))));
      intRectDY = intRectCY - (int)(30 * Math.sin((Math.toRadians(intAngle))));
      intRectAX = intRectDX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(intAngle)));;
      intRectAY = intRectDY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(intAngle)));;
      intRectBX = intRectCX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(intAngle)));;
      intRectBY = intRectCY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(intAngle)));;
    }
    //When start button is clicked and animation starts
    if(blnanimation == true){
      double dblAnimation = dblTime;
      //Rounding integers
      if(intAngle == 5 && dblGravity == 1.62){
        dblAnimation = 48.71;
      }else if(intAngle == 4 && dblGravity == 1.62){
        dblAnimation = 30;
      }else if(intAngle == 3 && dblGravity == 1.62){
        dblAnimation = 20;
      }else if(intAngle == 2 && dblGravity == 1.62){
        dblAnimation = 28;
      }
      if(((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation) >= 0.23 && ((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)< 0.90){
        if(intExtraCos == 0){
          intExtraCos = 1;
        }else{
          intExtraCos = 0;
        }
      }else if(((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation) >= 0.5){
        intExtraCos = 1;
      }
      if(((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation) >= 0.25 && ((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation) < 0.90){
        if(intExtraSin == 0){
          intExtraSin = 1;
        }else{
          intExtraSin = 0;
        }
      }else if(((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation) >= 0.5){
        intExtraSin = 1;
      }if(((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation) >= 0.60 && ((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)<= 0.40 && ((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation) >= 0.60 && ((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)<= 0.40){
        intRectAX = intRectAX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation))) + intExtraCos;
        intRectAY = intRectAY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
        intRectBX = intRectBX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraCos;
        intRectBY = intRectBY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
        intRectCX = intRectCX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraCos;
        intRectCY = intRectCY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
        intRectDX = intRectDX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraCos;
        intRectDY = intRectDY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
        
      }else if(((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation) >= 0.60 && ((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)<= 0.40){
        intRectAX = intRectAX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation))) + intExtraCos;
        intRectAY = intRectAY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
        intRectBX = intRectBX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraCos;
        intRectBY = intRectBY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
        intRectCX = intRectCX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraCos;
        intRectCY = intRectCY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
        intRectDX = intRectDX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraCos;
        intRectDY = intRectDY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
      }else if(((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation) >= 0.60 && ((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)<= 0.40){
        intRectAX = intRectAX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation) + intExtraCos;
        intRectAY = intRectAY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
        intRectBX = intRectBX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraCos;
        intRectBY = intRectBY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
        intRectCX = intRectCX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraCos;
        intRectCY = intRectCY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
        intRectDX = intRectDX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraCos;
        intRectDY = intRectDY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation + 0.5 - (((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)-(int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)))+ intExtraSin;
      }else{
        intRectAX = intRectAX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation) + intExtraCos;
        intRectAY = intRectAY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
        intRectBX = intRectBX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraCos;
        intRectBY = intRectBY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
        intRectCX = intRectCX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraCos;
        intRectCY = intRectCY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
        intRectDX = intRectDX + (int)((200 * (Math.cos((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraCos;
        intRectDY = intRectDY + (int)((200 * (Math.sin((Math.toRadians(intAngle)))))/dblAnimation)+ intExtraSin;
        
      }
    }
  }
  
  //Constructor
  public JGraphics(){
    super();
    try{
      image = ImageIO.read(getClass().getResource(strImage+".png"));
    }catch(IOException e){
    }
  }
}