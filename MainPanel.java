/* Hillary Ssemakula.
 * MainPanel: This class is the panel where the race simulation is displayed along with instructions and scores
 *            This panel also contains a window and different panels are displayed on it as the user sets up te race
 */

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
  private RaceEvent event; //RaceEvent object that moves and draws cars.
  //All button variables to be displayed on panel are created
  private JButton join;
  private JButton cancel;
  private JButton start;
  private JButton reenter;
  private JButton pause;
  private JButton resume;
  private JButton reset;
  private JButton stop;
  
  //The two panel variables shown during race set up are created
  private StarterPanel1 starterPanel1;
  private StarterPanel2 starterPanel2;
  
  //The two panel variables shown during race on either side of the main panel are created
  private JPanel infoPanel;
  private JPanel scorePanel;
  
  //Timer variable contols movement of cars every 200ms
  private Timer timer;
  private Timer timer2;
  private boolean isOver; //keeps track. stores true/false depending on whether race is over or not.
  private Integer numOpp; //stores number of race participants that user chooses (2-10)
  private Color usrColor; //stores color that user chooses from drop down menu
  private String usrName; //stores name that user enters at start
  public static JFrame window; //Static window varible, as panels change during setup they are placed here.
  private int startCounter;
  private JTextArea scoreBoard;
  
  public MainPanel()
  {
    this.setPreferredSize(new Dimension(1020, 500));
    innitialize(); //method innitializes all instance varibles
  }
  
  /* Method innitialize. innitialises all instance variables
   * creates all buttons and adds actionListeners to them.
   * Creates first Panel and second panel. adds needed buttons to them.
   * adds first panel to window. Adds all needed buttons to main Panel
   * adds information panel to left and score panel to right.
   * creates controlling timer
   * created outside constructor. To be called whenever MainPanel needs to be reset.
   */
  public void innitialize()
  {
    removeAll();
    join = new JButton("JOIN RACE");
    cancel = new JButton("CANCEL");
    join.addActionListener(this);
    cancel.addActionListener(this);
    starterPanel1 = new StarterPanel1(join, cancel);
    reenter = new JButton("RE-ENTER");
    start = new JButton("START");
    reenter.addActionListener(this);
    start.addActionListener(this);
    startCounter = 0;
    isOver = false;
    window.getContentPane().add(starterPanel1);
    
    pause = new JButton("PAUSE");
    resume = new JButton("RESUME");
    resume.setEnabled(false);
    reset = new JButton("RESET");
    stop = new JButton("STOP");
    stop.setEnabled(false);
    pause.addActionListener(this);
    resume.addActionListener(this);
    reset.addActionListener(this);
    stop.addActionListener(this);
    setLayout(new BorderLayout());
    JPanel holderPanel = new JPanel();
    holderPanel.setPreferredSize(new Dimension(1000,40));
    holderPanel.setBackground(new Color(95,158,160));
    holderPanel.add(pause);
    holderPanel.add(resume);
    holderPanel.add(reset);
    holderPanel.add(stop);
    add(holderPanel, BorderLayout.NORTH);
    infoPanel = new JPanel();
    infoPanel.setPreferredSize(new Dimension(200,430));
    infoPanel.setBackground(new Color(70,130,180));
    setUpInfoPanel();
    add(infoPanel, BorderLayout.WEST);
    scorePanel = new JPanel();
    scorePanel.setPreferredSize(new Dimension(280,430));
    scorePanel.setBackground(new Color(70,130,180));
    setUpScorePanel();
    add(scorePanel, BorderLayout.EAST);
    timer = new Timer(50, this);
    timer2 = new Timer(700, this);
    
    
    JPanel holderPanel2 = new JPanel();
    holderPanel2.setPreferredSize(new Dimension(1000,30));
    holderPanel2.setBackground(new Color(95,158,160));
    holderPanel2.add(new JLabel("Copyright © Sarah Higgins, Hillary Ssemakula, Rodrigo Choque Cardenas 2017"));
    add(holderPanel2, BorderLayout.SOUTH);
  }
  
  public void setUpInfoPanel()
  {
    JPanel holderPanel1 = new JPanel();
    holderPanel1.setPreferredSize(new Dimension(200, 30));
    holderPanel1.setBackground(Color.black);
    infoPanel.add(holderPanel1);
    JPanel holderPanel2 = new JPanel();
    holderPanel2.setPreferredSize(new Dimension(200, 300));
    holderPanel2.setBackground(Color.white);
    infoPanel.add(holderPanel2);
    JTextArea manual = new JTextArea("\n\n             MANUAL\n PAUSE:  pauses race\n RESUME:  Resumes race after"+
                                     " \n pause\n RESET:  Restarts race\n"+
                                     " STOP:  Stops everything!\n (cancels race)", 10,17);
    holderPanel2.add(manual);
    manual.setFont(new Font("dialog", Font.BOLD, 12));
    manual.setEditable(false);
    manual.setLineWrap(true);
  }
  
  public void setUpScorePanel()
  {
    JPanel holderPanel1 = new JPanel();
    holderPanel1.setPreferredSize(new Dimension(280, 30));
    holderPanel1.setBackground(Color.black);
    scorePanel.add(holderPanel1);
    JPanel holderPanel2 = new JPanel();
    holderPanel2.setPreferredSize(new Dimension(280, 300));
    holderPanel2.setBackground(Color.white);
    scorePanel.add(holderPanel2);
    scoreBoard = new JTextArea("", 10,23);
    holderPanel2.add(scoreBoard);
    scoreBoard.setEditable(false);
    scoreBoard.setFont(new Font("dialog", Font.BOLD, 12));
    scoreBoard.setLineWrap(true);
  }
  
  public void drawBanner(Graphics g)
  {
    int x = 205;
    int y = 45;
    int nextGrid = 0;
    for(int j = 1; j<=5; j++){
      for(int i = 1; i<=5; i++)
      {
        switch(nextGrid) {
          case 0:
            if(i%2 == 0)g.setColor(Color.black);
            else g.setColor(Color.white);
            break;
          case 1:
            if(i%2 == 0)g.setColor(Color.white);
            else g.setColor(Color.black);
            break;
        }
        g.fillRect(x,y,7,7);
        x += 7;
      }
      y += 7;
      x = 205;
      if(j%2 == 0) nextGrid = 0;
      else nextGrid = 1;
    }
    g.setFont(new Font("dialog", Font.PLAIN, 20));
    g.setColor(Color.black);
    g.drawString("SAHIRO GRANDPRIX", 245,72);
    g.fillRect(240, 78, 600, 2);
  }
  
  public void drawCounter(Graphics g)
  {
    if(startCounter < 3) g.setColor(Color.blue);
    else g.setColor(Color.red);
    g.setFont(new Font("Dialog", Font.BOLD, 100));
    g.drawString(""+startCounter, 450,240);
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    //Graphics2D g2 = (Graphics2D)g;
    drawBanner(g);
    if(startCounter < 4) drawCounter(g);
    else
    {
      if(!isOver)event.draw((Graphics2D)g);
      else
      {
        g.drawString(event.getWinner() + event.getContestants(), 210, 100);
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
      window.setSize(1100, 540);
      window.validate();
      window.repaint();
      timer2.start();
    }
    
    if(e.getSource() == pause)
    {
      resume.setEnabled(true);
      pause.setEnabled(false);
    }
    if(e.getSource() == resume)
    {
      resume.setEnabled(false);
      pause.setEnabled(true);
    }
    if(e.getSource() == timer2)
    {
      startCounter += 1;
      repaint();
      if(startCounter == 4)
      {
        timer2.stop();
        event = new RaceEvent(usrName, numOpp);
        scoreBoard.setText(event.getContestants());
        timer.start();
        stop.setEnabled(true);
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
      JOptionPane.showMessageDialog(this,"Race Stopped by User\n\n"+event.getContestants(),"RACE ENDED", 1);
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
  
  //Individual tester code for panel
  //public static void main(String[] args)
  //{
  
  //  JFrame window = new JFrame();
  // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //MainPanel m = new MainPanel();
  //window.add(m);
  //window.pack();
  //window.setVisible(true);
  // }
  
}
