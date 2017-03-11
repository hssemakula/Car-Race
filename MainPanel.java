/* Hillary Ssemakula.
 * MainPanel: This class is the panel where the race simulation is displayed along with instructions and scores
 *            This panel also contains a window and different panels are displayed on it as the user sets up the race
 *            and clicks certain buttons
 */

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JTextArea;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel implements ActionListener
{
  private RaceEvent event; //RaceEvent object that is the general controlling class for all objects
  //All button variables to be displayed on panel are created
  private JButton join; //adds user's Car to race
  private JButton cancel; //exits
  private JButton start; //starts race
  private JButton reenter; //takes user back to configuration screen
  private JButton pause;   //pauses the simulation
  private JButton resume;  //resumes simulation after pause.
  private JButton reset;   //resets race takes user back to configuration screen
  private JButton stop;    //Stops race all together
  
  //The two panel variables shown during race set up are created
  private StarterPanel1 starterPanel1; //Contains user choices and selectons like name, color of car, displayed first
  private StarterPanel2 starterPanel2; //Shows user what choices they configured(color, name) displays options to start race or reconfigure options.
  
  //The two panel variables shown during race on either side of the main panel are created
  private JPanel infoPanel; //Panel displayed on the left of game simulation: shows manual
  private JPanel scorePanel;//Panel displayed on the right of game simulation: shows game statistics for all cars(speed, distance and path);
  
  private Timer timer;  //Timer variable to contain main timer of game that starts and stops simulation
  private Timer timer2;  //Timer variable to control the counter and at the beginning of the game.
  private boolean isOver; //keeps track. stores true/false depending on whether race is over or not.
  private Integer numOpp; //stores number of race participants that user chooses (2-10)
  private Color usrColor; //stores color that user chooses from drop down menu
  private String usrName; //stores name that user enters at start of the game
  public static JFrame window; //Static window varible, as panels change during setup they are placed here.
  private int startCounter;  //Counter displayed before the beginning of the game
  private JTextArea scoreBoard; //Text area that displays all statistics(speed, distance and path) of all cars as race happens
  
  public MainPanel()
  {
    this.setPreferredSize(new Dimension(1300, 500));
    innitialize(); //method innitializes all instance varibles
  }
  
  /* Method innitialize. innitialises all instance variables
   * creates all buttons and adds actionListeners to them.
   * Creates first Panel and second panel. adds needed buttons to them.
   * adds first panel to window. Adds all needed buttons to main Panel
   * adds information panel to left and score panel to right.
   * creates the two controlling timers
   * created outside constructor.can be called whenever MainPanel needs to be reset.
   */
  public void innitialize()
  {
    removeAll(); //removes all graphics like panels....this is useful when a user presses reset after a simulation
    join = new JButton("JOIN RACE");
    cancel = new JButton("CANCEL");
    join.addActionListener(this);
    cancel.addActionListener(this);
    starterPanel1 = new StarterPanel1(join, cancel); //Join and cancel buttons are added to the first panel
    reenter = new JButton("RE-ENTER");
    start = new JButton("START");
    reenter.addActionListener(this);
    start.addActionListener(this);
    startCounter = 0;
    isOver = false;
    window.getContentPane().add(starterPanel1); //first panel added to window
    
    //buttons to be added to the main panel are created
    pause = new JButton("PAUSE");
    resume = new JButton("RESUME");
    resume.setEnabled(false); //resume button starts out disabled and is only enabled when user clicks pause.
    reset = new JButton("RESET");
    stop = new JButton("STOP");
    stop.setEnabled(false); //All buttons start out disabled and are enabled when the race starts after the 1,2,3 counter
    pause.setEnabled(false);
    reset.setEnabled(false);
    pause.addActionListener(this);
    resume.addActionListener(this);
    reset.addActionListener(this);
    stop.addActionListener(this);
    setLayout(new BorderLayout()); //The main panel is set to have a borderlayout
    JPanel holderPanel = new JPanel();//This panel is just a place holder to format the layout of the buttons on the panel
    holderPanel.setPreferredSize(new Dimension(1000,40));
    holderPanel.setBackground(new Color(95,158,160));
    holderPanel.add(pause); //All buttons are added to holder panel which is then added to the north, this keeps the buttons
    holderPanel.add(resume);//in one line
    holderPanel.add(reset);
    holderPanel.add(stop);
    add(holderPanel, BorderLayout.NORTH);
    //infoPanel created. it holds a textarea that has the manual for the game.
    infoPanel = new JPanel(); 
    infoPanel.setPreferredSize(new Dimension(200,430));
    infoPanel.setBackground(new Color(70,130,180));
    setUpInfoPanel(); //helper method to setup the info panel and add the game instructions onto it
    add(infoPanel, BorderLayout.WEST); //info panel added to the west of the main panel
    //scorePanel created. it holds a textarea that displays the statistics of each car in one list.
    scorePanel = new JPanel();
    scorePanel.setPreferredSize(new Dimension(500,430));
    scorePanel.setBackground(new Color(70,130,180));
    setUpScorePanel(); //helper method to setup the score panel and add the score board text area on to it
    add(scorePanel, BorderLayout.EAST); //scorePanel is added to the east of the main panel
    timer = new Timer(50, this); //every 50ms this timer runs the game
    timer2 = new Timer(700, this);
    
    
    JPanel holderPanel2 = new JPanel(); //holder panel for the south JLabel
    holderPanel2.setPreferredSize(new Dimension(1000,30));
    holderPanel2.setBackground(new Color(95,158,160));
    holderPanel2.add(new JLabel("Copyright © Sarah Higgins, Hillary Ssemakula, Rodrigo Choque Cardenas 2017"));
    add(holderPanel2, BorderLayout.SOUTH);
  }
  
  //Method setUpInfoPanel. helper method for the infopanel when it is called, it sets up a text area and adds game instructions on to it
  public void setUpInfoPanel()
  {
    JPanel holderPanel1 = new JPanel(); //This panel is used purely for design. it makes the black strip on top of the game manual
    holderPanel1.setPreferredSize(new Dimension(200, 30));
    holderPanel1.setBackground(Color.black);//color set here
    infoPanel.add(holderPanel1);
    JPanel holderPanel2 = new JPanel(); //This panel is where the manual JTestArea is placed
    holderPanel2.setPreferredSize(new Dimension(200, 300));
    holderPanel2.setBackground(Color.white);
    infoPanel.add(holderPanel2); //holder panel added to left of main panel
    //TextArea created and game instructions added
    JTextArea manual = new JTextArea("\n\n             MANUAL\n PAUSE:  pauses race\n RESUME:  Resumes race after"+
                                     " \n pause\n RESET:  Restarts race\n"+
                                     " STOP:  Stops everything!\n (cancels race)", 10,17);
    holderPanel2.add(manual);//text area added to holder panel
    manual.setFont(new Font("dialog", Font.BOLD, 12)); //Font for manual set here
    manual.setEditable(false); //The text area is made uneditable
    manual.setLineWrap(true); //this helps the lines to wrap and not go out of view on the text area
  }
  
  /*Method setUpScorePanel. helper method for the scorePanel when it is called, it adds the scoreBoard text area to the scorePanel
   *the scoreBoard textarea is updated during the race to display all the cars' statistics
   */ 
  public void setUpScorePanel()
  {
    JPanel holderPanel1 = new JPanel(); //This panel is used purely for design. it makes the black strip on top of the game manual
    holderPanel1.setPreferredSize(new Dimension(500, 30)); 
    holderPanel1.setBackground(Color.black);//color set here
    scorePanel.add(holderPanel1);
    JPanel holderPanel2 = new JPanel();//second holder panel is created
    holderPanel2.setPreferredSize(new Dimension(500, 300));
    holderPanel2.setBackground(Color.white); 
    scorePanel.add(holderPanel2); //holder panel is added to scorePanel
    scoreBoard = new JTextArea("", 12,33); //scoreBoard textarea is created with no text on it.
    holderPanel2.add(scoreBoard); //text area added to holder panel
    scoreBoard.setEditable(false); //text area made uneditable
    scoreBoard.setFont(new Font("dialog", Font.BOLD, 12));
    scoreBoard.setLineWrap(true);
  }
  
  /* Method drawBanner. @param Graphics g. when passed a graphics pen. This method
   *  contrcucts a grid makes up a race flag. and follows it
   *  it with the label SAHIRO GRANDPRIX
   */ 
  public void drawBanner(Graphics g)
  {
    int x = 205; //starting coordinates for race flag
    int y = 45;
    int nextGrid = 0; //variable used to change grid pattern when pen goes to next line i.e from (white to black) to (black to white)
    //this loop creates a 5X5 grid of black and white squares that create the flag
    for(int j = 1; j<=5; j++){
      for(int i = 1; i<=5; i++)
      {
        //pattern changed depending on nextGrid's value, i.e what line the pen is on
        switch(nextGrid) {
          case 0:
            if(i%2 == 0)g.setColor(Color.black); //when i is even pen color is changed to black
            else g.setColor(Color.white); //else it is changed to white
            break;
          case 1:
            if(i%2 == 0)g.setColor(Color.white); //when i is even pen color is changed to white
            else g.setColor(Color.black); //else it is changed to black
            break;
        }
        g.fillRect(x,y,7,7); //squares are drawn here after setting pen color
        x += 7; //offsets x so that next square to drawn to the right of he other
      }
      y += 7; //after a line of squares, y is offset so that the next line of squares is drawn under the first line
      x = 205; //x is reset before new line of squares is drawn so that it starts from far left to right
      if(j%2 == 0) nextGrid = 0; //this conditon alternates the variable used to set the pen as it goes from line to line
      else nextGrid = 1;
    }
    g.setFont(new Font("dialog", Font.PLAIN, 20));
    g.setColor(Color.black); //pen is set back to black
    g.drawString("SAHIRO GRANDPRIX", 245,72); // SAHIRO GRANDPRIX String is wriiten
    g.fillRect(240, 78, 600, 2); //The line drawn under the title is actually achieved by this method
  }
  
  /* Method drawCounter. @param Graphics g. Once passed a graphics object
   *  this method draws the magnified value of the startCounter on the panel
   */ 
  public void drawCounter(Graphics g)
  {
    if(startCounter < 3) g.setColor(Color.blue); //if counter is less that 3 it is drawn in blue color
    else g.setColor(Color.red);  //else it is drawn in red color
    g.setFont(new Font("Dialog", Font.BOLD, 100)); //the font of the graphics object is magnified to 100
    g.drawString(""+startCounter, 450,240);  //Counter is drawn
  }
  
  /* @override. Method paintComponent. @param Graphics g.
   * paints the counter on the center region of panel as the race starts.
   * paints banner on top of center region of main panel
   * paints cars and Checkpoints on center region of panel as race goes on.
   * Draws coders' names when race is finished.
   */ 
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    drawBanner(g); //method draws SAHIRO GRAND PRIX banner.
    if(startCounter < 4) drawCounter(g); //if counter is less than 4, it is drawn on panel as race starts
    else
    {
      //if the game is not over, the raceEvent object's draw Method is called
      if(!isOver)event.draw((Graphics2D)g); 
      else
      {
        g.setColor(new Color(95,158,160));
        g.setFont(new Font("Dialog", Font.BOLD, 20));
        g.drawString("Hillary Ssemakula", 400,210);
        g.drawString("Rodrigo Choque Cardenas", 400,240); 
        g.drawString("Sarah Higgins", 400,270); 
      }
    }
  }
  
  /* @Override actionPerformed. @param ActionEvent.
   * handels all action events.
   */
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == cancel) System.exit(0); //exits when cancel button is clicked.
    /* When join is clicked if all fields are filled in with the right values, second panel is displayed
     * and first panel is removed from window. else error message is displayed.
     */
    if(e.getSource() == join)
    {
      if(starterPanel1.getOpp() == -1 || starterPanel1.getOpp() > 10 || starterPanel1.getOpp() < 2)
      {
        JOptionPane.showMessageDialog(this,"Please enter a valid number for number of participants\n"+
                                      "The race should have between  2 and 10 participants","INVALID DATA", 0);
      }
      else if(starterPanel1.getUsrName().equals(""))
      {
        JOptionPane.showMessageDialog(this,"Please enter a name","INVALID DATA", 0);
      }
      else{
        numOpp = starterPanel1.getOpp();
        usrColor = starterPanel1.getUsrColor();
        usrName = starterPanel1.getUsrName();
        window.remove(starterPanel1);
        starterPanel2 = new StarterPanel2(start, reenter, numOpp, starterPanel1.colorString(), usrName);
        window.getContentPane().add(starterPanel2);
        window.setSize(420, 240);
        window.validate();
      }
    }
    //when RE-ENTER is clicked, first panel is displayed again and second panel is removed from window
    if(e.getSource() == reenter)
    {
      window.remove(starterPanel2);
      window.getContentPane().add(starterPanel1);
      window.setSize(520, 390);
      window.validate();
    }
    //when START is clicked, this panel(Main Panel) is displayed on window and second panel is removed from window
    if(e.getSource() == start)
    {
      window.remove(starterPanel2);
      window.getContentPane().add(this);
      window.setSize(1350, 540); //--here
      window.validate();
      window.repaint();
      timer2.start();
    }
    
    if(e.getSource() == pause)
    {
      resume.setEnabled(true);
      pause.setEnabled(false);
      timer.stop();
    }
    if(e.getSource() == resume)
    {
      resume.setEnabled(false);
      pause.setEnabled(true);
      timer.start();
    }
    if(e.getSource() == timer2)
    {
      startCounter += 1;
      repaint();
      if(startCounter == 4)
      {
        timer2.stop();
        event = new RaceEvent(usrName, numOpp, usrColor);
        scoreBoard.setText(event.getContestants());
        timer.start();
        event.setStartTime(System.currentTimeMillis());
        stop.setEnabled(true);
        pause.setEnabled(true);
        reset.setEnabled(true);
      }
    }
    if(e.getSource() == timer)
    {
      if(event.race())
      {
        scoreBoard.setText(event.getContestants());
        repaint();
      }
      else{
        isOver = true;
        timer.stop();
        repaint();
        pause.setEnabled(false);
        stop.setEnabled(false);
        JOptionPane.showMessageDialog(this,"\tRACE CHAMPION\n"+event.getWinner(),"RACE ENDED", 1);
        JOptionPane.showMessageDialog(this,"\tRACE STATISTICS\n\n"+event.getContestants(),"RACE ENDED", 1);
        int option = JOptionPane.showConfirmDialog(this,"Would you like to start another simulation?","TRY AGAIN", 1);
        if(option == 0)
        {  
          window.remove(this);
          this.innitialize();
          window.getContentPane().add(starterPanel1);
          window.setSize(520, 370);
          window.validate();
          window.repaint();
        }
        else if(option == 1)
        {
          System.exit(0);
        }
      }
    }
    if(e.getSource() == reset)
    {
      window.remove(this);
      this.innitialize();
      window.getContentPane().add(starterPanel1);
      window.setSize(520, 370);
      window.validate();
      window.repaint();
    } 
    if(e.getSource() == stop)
    {
      timer.stop();
      JOptionPane.showMessageDialog(this,"Race Stopped by User\n\n"+event.getContestants(),"RACE ENDED", 0);
      System.exit(0);
    }  
  }
  
  //Main method: creates MainPanel object where race simulation is displayed
  public static void main(String[] args)
  {
    window = new JFrame();
    window.setTitle("SAHIRO GRAND PRIX");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MainPanel p = new MainPanel();
    window.pack();
    window.setResizable(false);
    window.setVisible(true);
  }
}
