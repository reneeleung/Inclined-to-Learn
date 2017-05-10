// Open with Windows
// This application is called ÒForces on Inclinced Surfaces
// It is an interactive program in which users can investigate how different factors affect forces and acceleration on ramps
// The intention of the game is to provide students with better understanding on the concept of ramps
// Version 1.0
// Created By Renee Leung
// Launched on April 29, 2016

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class guirenee implements ActionListener, ChangeListener, MouseListener{
  //Properties
  JFrame theframe; //main
  JFrame theframe2; //quiz
  JFrame theframe3; //highscore
  JFrame theframe4; //about
  JFrame theframe5; //help
  JGraphics thepanel;
  JPanel thequiz;
  JSlider angleslide;
  JSlider coslide;
  JSlider faslide;
  JSlider mslide;
  Timer thetimer;
  JMenuBar themenubar;
  JMenuBar planetbar;
  JMenu quizmenu;
  JMenu helpmenu;
  JMenu aboutmenu;
  JMenu planetmenu;
  JMenuItem quizitem;
  JMenuItem scoreitem;
  JMenuItem earthitem;
  JMenuItem sunitem;
  JMenuItem moonitem;
  JMenuItem mercuryitem;
  JMenuItem venusitem;
  JMenuItem marsitem;
  JMenuItem jupiteritem;
  JMenuItem saturnitem;
  JMenuItem uranusitem;
  JMenuItem neptuneitem;
  JButton startbutton;
  JTextField angletext;
  JTextField cotext;
  JTextField fatext;
  JTextField mtext;
  JLabel coefficientLabel;
  JLabel forceLabel;
  JLabel massLabel;
  Help thehelp;
  About theabout;
  Scores thescores;
  JLabel thequestion;
  JCheckBox[] theanswers = new JCheckBox[4];
  JPanel quizpanel;
  JButton thenextquestion;
  JLabel gameover;
  JLabel quizcomplete;
  JLabel numofwrong;
  JLabel scoreprint;
  int intWrong = 0;
  int intScore = 0;
  int intCorrectAns = 0;
  int intOther1 = 1;
  int intOther2 = 2;
  int intOther3 = 3;
  boolean blnProceed = true;
  String[] strLine = new String[4];
  String strLin = "";
  BufferedReader thefile = null;
  int intQuestionCount = 0;
  PrintWriter fileout = null;
  
  //Methods
  public void actionPerformed(ActionEvent evt){
    if(evt.getSource() == thetimer){
      thepanel.repaint();
      thequiz.repaint();
    }
    //Starting animation
    if(evt.getSource() == startbutton){
      if(thepanel.intRectCX + (int)((200 * (Math.cos((Math.toRadians(thepanel.intAngle)))))/thepanel.dblTime) < thepanel.intTriX + (int)(200 * Math.cos((Math.toRadians(thepanel.intAngle)))) && thepanel.intRectCY + (int)((200 * (Math.sin((Math.toRadians(thepanel.intAngle)))))/thepanel.dblTime) < thepanel.intTriY){
        thepanel.intExtraSin = 0;
        thepanel.intExtraCos = 1;
        thepanel.blnanimation = true;
      }else{
        thepanel.blnanimation = false;
      }
    }
    //Changing angle by text field
    if(evt.getSource() == angletext){
      if(Integer.parseInt(this.angletext.getText()) >= 0 && Integer.parseInt(this.angletext.getText()) <= 90){ 
        thepanel.intAngle = Integer.parseInt(this.angletext.getText());
        angleslide.setValue(Integer.parseInt(this.angletext.getText()));
        thepanel.intRectDX = thepanel.intTriX;
        thepanel.intRectDY = thepanel.intTriY - (int)(200 * Math.sin((Math.toRadians(thepanel.intAngle))));
        thepanel.intRectAX = thepanel.intRectDX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
        thepanel.intRectAY = thepanel.intRectDY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
        thepanel.intRectCX = thepanel.intRectDX + (int)(30 * Math.cos(Math.toRadians(thepanel.intAngle)));
        thepanel.intRectCY = thepanel.intRectDY + (int)(30 * Math.sin(Math.toRadians(thepanel.intAngle)));
        thepanel.intRectBX = thepanel.intRectCX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
        thepanel.intRectBY = thepanel.intRectCY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
      }else{
        angletext.setText(""+thepanel.intAngle);
      }
    }else if(evt.getSource() == cotext){
      if(Integer.parseInt(this.cotext.getText()) >= 0 && Integer.parseInt(this.cotext.getText()) <= 30){ 
        thepanel.intCo = Integer.parseInt(this.cotext.getText());
        coslide.setValue(Integer.parseInt(this.cotext.getText()));
      }else{
        cotext.setText(""+thepanel.intCo);
      }
    }else if(evt.getSource() == fatext){
      if(Integer.parseInt(this.fatext.getText()) >= 0 && Integer.parseInt(this.fatext.getText()) <= 500){ 
        thepanel.intFa = Integer.parseInt(this.fatext.getText());
        faslide.setValue(Integer.parseInt(this.fatext.getText()));
      }else{
        fatext.setText(""+thepanel.intFa);
      }
    }else if(evt.getSource() == mtext){
      if(Integer.parseInt(this.mtext.getText()) >= 0 && Integer.parseInt(this.mtext.getText()) <= 50){ 
        thepanel.intMass = Integer.parseInt(this.mtext.getText());
        mslide.setValue(Integer.parseInt(this.mtext.getText()));
      }else{
        mtext.setText(""+thepanel.intMass);
      }
    }
    //Changing the planet
    if(evt.getSource() == earthitem){
      thepanel.dblGravity = 9.81;
      thepanel.strImage = "earth";
    }else if(evt.getSource() == sunitem){
      thepanel.dblGravity = 274.13;
      thepanel.strImage = "sun";
    }else if(evt.getSource() == moonitem){
      thepanel.dblGravity = 1.62;
      thepanel.strImage = "moon";
    }else if(evt.getSource() == mercuryitem){
      thepanel.dblGravity = 3.59;
      thepanel.strImage = "mercury";
    }else if(evt.getSource() == venusitem){
      thepanel.dblGravity = 8.87;
      thepanel.strImage = "venus";
    }else if(evt.getSource() == marsitem){
      thepanel.dblGravity = 3.77;
      thepanel.strImage = "mars";
    }else if(evt.getSource() == jupiteritem){
      thepanel.dblGravity = 25.95;
      thepanel.strImage = "jupiter";
    }else if(evt.getSource() == saturnitem){
      thepanel.dblGravity = 11.08;
      thepanel.strImage = "saturn";
    }else if(evt.getSource() == uranusitem){
      thepanel.dblGravity = 10.67;
      thepanel.strImage = "uranus";
    }else if(evt.getSource() == neptuneitem){
      thepanel.dblGravity = 14.07;
      thepanel.strImage = "neptune";
    }
    if(evt.getSource() == quizitem || evt.getSource() == thenextquestion){
      //quiz screen
      try{
        thefile = new BufferedReader(new FileReader("quiz.txt"));
      }catch(FileNotFoundException e){
      }
      while(intWrong < 3 && intQuestionCount < 10 && blnProceed == true){
        int intReadLineCount;
        for(intReadLineCount = 0; intReadLineCount < intQuestionCount * 6; intReadLineCount++){
          try{
            strLin = thefile.readLine();
          }catch(IOException e){
          }
        }
        thequiz.removeAll();
        try{
          strLin = thefile.readLine();
        }catch(IOException e){
        }
        thequestion = new JLabel(strLin);
        try{
          strLine[0] = thefile.readLine();
        }catch(IOException e){
        }
        theanswers[0] = new JCheckBox(strLine[0]);
        try{
          strLine[1] = thefile.readLine();
        }catch(IOException e){
        }
        theanswers[1] = new JCheckBox(strLine[1]);
        try{
          strLine[2] = thefile.readLine();
        }catch(IOException e){
        }
        theanswers[2] = new JCheckBox(strLine[2]);
        try{
          strLine[3] = thefile.readLine();
        }catch(IOException e){
        }
        theanswers[3] = new JCheckBox(strLine[3]);
        try{
          strLin = thefile.readLine();
        }catch(IOException e){
        }
        numofwrong = new JLabel("Number of Wrong Answers: "+intWrong);
        scoreprint = new JLabel("Score: "+intScore);
        numofwrong.setSize(200, 50);
        numofwrong.setLocation(30, 350);
        scoreprint.setSize(200, 50);
        scoreprint.setLocation(30, 415);
        thequiz.add(numofwrong);
        thequiz.add(scoreprint);
        int intCount = 0;
        for(intCount = 0; intCount < 4; intCount++){
          if(strLine[intCount].equals(strLin)){
            intCorrectAns = intCount;
          }
        }
        thequestion.setSize(1000, 50);
        thequestion.setLocation(30, 50);
        thequiz.add(thequestion);
        intCount = 0;
        for(intCount = 0; intCount < 4; intCount++){
          theanswers[intCount].setSize(400, 60);
          theanswers[intCount].setLocation(30, intCount * 50 + 100);
          theanswers[intCount].addActionListener(this);
          thequiz.add(theanswers[intCount]);
        }
        if(intCorrectAns == 0){
          intOther1 = 1;
          intOther2 = 2;
          intOther3 = 3;
        }else if(intCorrectAns == 1){
          intOther1 = 0;
          intOther2 = 2;
          intOther3 = 3;
        }else if(intCorrectAns == 2){
          intOther1 = 0;
          intOther2 = 1;
          intOther3 = 3;
        }else if(intCorrectAns == 3){
          intOther1 = 0;
          intOther2 = 1;
          intOther3 = 2;
        }
        blnProceed = false;
        intQuestionCount++;
      }
      theframe2.setContentPane(thequiz);
      theframe2.pack();
      theframe2.setVisible(true);
      if(intWrong == 3){
        thequiz.removeAll();
        gameover = new JLabel("GAME OVER... You scored "+intScore);
        gameover.setSize(200, 200);
        gameover.setLocation(400, 200);
        thequiz.add(gameover);
        try{
          fileout = new PrintWriter(new FileWriter("highscores.txt", true));
        }catch(IOException e){
        }
        fileout.println(intScore);
        fileout.close();
      }else if(intQuestionCount == 10 && blnProceed == true){
        thequiz.removeAll();
        quizcomplete = new JLabel("Quiz completed! You scored "+intScore);
        quizcomplete.setSize(200, 200);
        quizcomplete.setLocation(400, 200);
        thequiz.add(quizcomplete);
        try{
          fileout = new PrintWriter(new FileWriter("highscores.txt", true));
        }catch(IOException e){
        }
        fileout.println(intScore);
        fileout.close();
      }
      
    }else if(evt.getSource() == scoreitem){
      //highscores screen
      theframe3.setContentPane(thescores);
      theframe3.pack();
      theframe3.setVisible(true);
    }
    //checking answers on quiz
    if(evt.getSource() == theanswers[0] || evt.getSource() == theanswers[1] || evt.getSource() == theanswers[2] || evt.getSource() == theanswers[3]){
      theanswers[0].setEnabled(false);
      theanswers[1].setEnabled(false);
      theanswers[2].setEnabled(false);
      theanswers[3].setEnabled(false);
      thenextquestion = new JButton("Next");
      thenextquestion.setSize(100, 30);
      thenextquestion.setLocation(350, 380);
      thenextquestion.addActionListener(this);
      theframe2.add(thenextquestion);
      theframe2.repaint();
      if(theanswers[intCorrectAns].isSelected() == true && theanswers[intOther1].isSelected() == false && theanswers[intOther2].isSelected() == false && theanswers[intOther3].isSelected() == false){
        intScore++;
        System.out.println(intScore+"Correct");
        blnProceed = true;
        
      }else{
        intWrong++;
        System.out.println(intWrong+"wrong");
        blnProceed = true;
        
      }
    }
  }
  //changing angle on slide
  public void stateChanged(ChangeEvent evt){
    if(evt.getSource() == angleslide){
      thepanel.intAngle = angleslide.getValue();
      angletext.setText(""+thepanel.intAngle);
      thepanel.intRectDX = thepanel.intTriX;
      thepanel.intRectDY = thepanel.intTriY - (int)(200 * Math.sin((Math.toRadians(thepanel.intAngle))));
      thepanel.intRectAX = thepanel.intRectDX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
      thepanel.intRectAY = thepanel.intRectDY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
      thepanel.intRectCX = thepanel.intRectDX + (int)(30 * Math.cos(Math.toRadians(thepanel.intAngle)));
      thepanel.intRectCY = thepanel.intRectDY + (int)(30 * Math.sin(Math.toRadians(thepanel.intAngle)));
      thepanel.intRectBX = thepanel.intRectCX + (int)(30 * Math.cos(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
      thepanel.intRectBY = thepanel.intRectCY - (int)(30 * Math.sin(Math.PI/2 - Math.toRadians(thepanel.intAngle)));
    }else if(evt.getSource() == coslide){
      thepanel.intCo = coslide.getValue();
      cotext.setText(""+coslide.getValue());
    }else if(evt.getSource() == faslide){
      thepanel.intFa = faslide.getValue();
      fatext.setText(""+thepanel.intFa);
    }else if(evt.getSource() == mslide){
      thepanel.intMass = mslide.getValue();
      mtext.setText(""+thepanel.intMass);
    }
  }
  public void mouseExited(MouseEvent evt){
  }
  public void mouseEntered(MouseEvent evt){
  }
  public void mouseReleased(MouseEvent evt){
  }
  public void mousePressed(MouseEvent evt){
  }
  public void mouseClicked(MouseEvent e){
    if(e.getSource() == helpmenu){
      //Help menu
      theframe5.setContentPane(thehelp);
      theframe5.pack();
      theframe5.setVisible(true);
    }
    if(e.getSource() == aboutmenu){
      //About menu
      theframe4.setContentPane(theabout);
      theframe4.pack();
      theframe4.setVisible(true);
      
    }
  }
  
  
  //Constructor
  public guirenee(){
    theframe = new JFrame("Forces on Inclined Surfaces");
    theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    theframe.setResizable(false);
    
    thepanel = new JGraphics();
    thepanel.setLayout(null);
    thepanel.setPreferredSize(new Dimension(640, 600));
    thepanel.addMouseListener(this);
    
    theframe2 = new JFrame("Quiz");
    theframe2.setVisible(false);
    theframe2.setResizable(false);
    thequiz = new JPanel();
    thequiz.setLayout(null);
    thequiz.setPreferredSize(new Dimension(1000, 600));
    thequiz.addMouseListener(this);
    
    theframe5 = new JFrame("Help");
    theframe5.setVisible(false);
    theframe5.setResizable(false);
    theframe5.setPreferredSize(new Dimension(640, 650));
    thehelp = new Help();
    thehelp.setLayout(null);
    
    theframe4 = new JFrame("About");
    theframe4.setVisible(false);
    theframe4.setResizable(false);
    theframe4.setPreferredSize(new Dimension(640, 650));
    theabout = new About();
    theabout.setLayout(null);
    
    theframe3 = new JFrame("Highscores");
    theframe3.setVisible(false);
    theframe3.setResizable(false);
    theframe3.setPreferredSize(new Dimension(640, 600));
    thescores = new Scores();
    thescores.setLayout(null);
    
    angleslide = new JSlider(1, 0, 90, 45);
    angleslide.setSize(50, 400);
    angleslide.setLocation(10, 100);
    angleslide.setPaintTicks(true);
    angleslide.setPaintLabels(true);
    angleslide.setMajorTickSpacing(10);
    angleslide.addChangeListener(this);
    thepanel.add(angleslide);
    
    angletext = new JTextField(""+thepanel.intAngle);
    angletext.setSize(50, 30);
    angletext.setLocation(8,510);
    angletext.addActionListener(this);
    thepanel.add(angletext);
    
    themenubar = new JMenuBar();
    themenubar.setSize(640, 50);
    themenubar.setLocation(0, 0);
    thepanel.add(themenubar);
    quizmenu = new JMenu("Quiz");
    quizmenu.setSize(250, 50);
    themenubar.add(quizmenu);
    helpmenu = new JMenu("Help");
    helpmenu.setSize(250, 50);
    helpmenu.addMouseListener(this);
    themenubar.add(helpmenu);
    aboutmenu = new JMenu("About");
    aboutmenu.setSize(250, 50);
    aboutmenu.addMouseListener(this);
    themenubar.add(aboutmenu);
    quizitem = new JMenuItem("Quiz");
    quizitem.addActionListener(this);
    quizmenu.add(quizitem);
    scoreitem = new JMenuItem("Highscores");
    scoreitem.addActionListener(this);
    quizmenu.add(scoreitem);
    
    startbutton = new JButton("Start");
    startbutton.setSize(100, 30);
    startbutton.setLocation(250, 100);
    startbutton.addActionListener(this);
    thepanel.add(startbutton);
    
    coslide = new JSlider(0, 30, 0);
    coslide.setSize(200, 30);
    coslide.setLocation(320, 380);
    coslide.setPaintTicks(true);
    coslide.setMajorTickSpacing(5);
    coslide.addChangeListener(this);
    thepanel.add(coslide);
    
    coefficientLabel = new JLabel("Coefficient of friction in tenths (£g):");
    coefficientLabel.setSize(200, 30);
    coefficientLabel.setLocation(100, 380);
    thepanel.add(coefficientLabel);
    
    cotext = new JTextField(""+thepanel.intCo);
    cotext.setSize(50, 30);
    cotext.setLocation(535,380);
    cotext.addActionListener(this);
    thepanel.add(cotext);
    
    faslide = new JSlider(0, 500, 0);
    faslide.setSize(200, 30);
    faslide.setLocation(320, 450);
    faslide.setPaintTicks(true);
    faslide.setMajorTickSpacing(50);
    faslide.addChangeListener(this);
    thepanel.add(faslide);
    
    forceLabel = new JLabel("Force Applied (Down) in Newtons:");
    forceLabel.setSize(215, 30);
    forceLabel.setLocation(100, 450);
    thepanel.add(forceLabel);
    
    fatext = new JTextField(""+thepanel.intFa);
    fatext.setSize(50, 30);
    fatext.setLocation(535,450);
    fatext.addActionListener(this);
    thepanel.add(fatext);
    
    mslide = new JSlider(0, 50, 25);
    mslide.setSize(200, 30);
    mslide.setLocation(320, 520);
    mslide.setPaintTicks(true);
    mslide.setMajorTickSpacing(10);
    mslide.addChangeListener(this);
    thepanel.add(mslide);
    
    massLabel = new JLabel("Mass of object in kilograms:");
    massLabel.setSize(200, 30);
    massLabel.setLocation(100, 520);
    thepanel.add(massLabel);
    
    mtext = new JTextField(""+thepanel.intMass);
    mtext.setSize(50, 30);
    mtext.setLocation(535,520);
    mtext.addActionListener(this);
    thepanel.add(mtext);
    
    planetbar = new JMenuBar();
    planetbar.setSize(110, 40);
    planetbar.setLocation(100, 320);
    thepanel.add(planetbar);
    planetmenu = new JMenu("Choose a Planet");
    planetmenu.setSize(50, 50);
    planetbar.add(planetmenu);
    
    earthitem = new JMenuItem("Earth");
    earthitem.addActionListener(this);
    planetmenu.add(earthitem);
    sunitem = new JMenuItem("Sun");
    sunitem.addActionListener(this);
    planetmenu.add(sunitem);
    moonitem = new JMenuItem("Moon");
    moonitem.addActionListener(this);
    planetmenu.add(moonitem);
    mercuryitem = new JMenuItem("Mercury");
    mercuryitem.addActionListener(this);
    planetmenu.add(mercuryitem);
    venusitem = new JMenuItem("Venus");
    venusitem.addActionListener(this);
    planetmenu.add(venusitem);
    marsitem = new JMenuItem("Mars");
    marsitem.addActionListener(this);
    planetmenu.add(marsitem);
    jupiteritem = new JMenuItem("Jupiter");
    jupiteritem.addActionListener(this);
    planetmenu.add(jupiteritem);
    saturnitem = new JMenuItem("Saturn");
    saturnitem.addActionListener(this);
    planetmenu.add(saturnitem);
    uranusitem = new JMenuItem("Uranus");
    uranusitem.addActionListener(this);
    planetmenu.add(uranusitem);
    neptuneitem = new JMenuItem("Neptune");
    neptuneitem.addActionListener(this);
    planetmenu.add(neptuneitem);
    
    theframe.setContentPane(thepanel);
    theframe.pack();
    theframe.setVisible(true);
    
    thetimer = new Timer(1000/30, this);
    thetimer.start();
  }
  
  //Main method
  public static void main(String[] args){
    guirenee gui = new guirenee();
  }
}