/* Hillary Ssemakula.
 * MainPanel: This class is the panel where the race simulation is displayed along with instructions and scores
 *            This panel also contains a window and different panels are displayed on it as the user sets up te race
 */ 

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
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
  private boolean status; //keeps track. stores true/false depending on whether race is over or not.
  private Integer numOpp; //stores number of race participants that user chooses (2-10)
  private Color usrColor; //stores color that user chooses from drop down menu
  private String usrName; //stores name that user enters at start
  public static JFrame window; //Static window varible, as panels change during setup they are placed here.
  
  public MainPanel() 
  {
    setPreferredSize(new Dimension(1000, 500));
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
    join = new JButton("JOIN RACE");
    cancel = new JButton("CANCEL");
    join.addActionListener(this);
    cancel.addActionListener(this);
    starterPanel1 = new StarterPanel1(join, cancel);
    reenter = new JButton("RE-ENTER");
    start = new JButton("START");
    reenter.addActionListener(this);
    start.addActionListener(this);
    starterPanel2 = new StarterPanel2(start, reenter);
    window.getContentPane().add(starterPanel1);
    
    pause = new JButton("PAUSE");
    resume = new JButton("RESUME");
    reset = new JButton("RESET");
    stop = new JButton("STOP");
    setLayout(new BorderLayout());
    JPanel holderPanel = new JPanel();
    holderPanel.setPreferredSize(new Dimension(1000,80));
    holderPanel.add(pause);
    holderPanel.add(resume);
    holderPanel.add(reset);
    holderPanel.add(stop);
    add(holderPanel, BorderLayout.NORTH);
    infoPanel = new JPanel();
    infoPanel.setPreferredSize(new Dimension(100,420));
    infoPanel.setBackground(Color.red);
    add(infoPanel, BorderLayout.WEST);
    scorePanel = new JPanel();
    scorePanel.setPreferredSize(new Dimension(200,420));
    scorePanel.setBackground(Color.blue);
    add(scorePanel, BorderLayout.EAST);
    timer = new Timer(200, this);
  }
  
  public void paintComponent(ActionEvent e)
  {
    
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
        window.getContentPane().add(starterPanel2);
        window.setSize(520, 240);
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
      window.setSize(1020, 540);
      window.validate();
      window.repaint();
    }  
  }
  
  //Main method: creates MainPanel object where race simulation is displayed
  public static void main(String[] args)
  {
    window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MainPanel p = new MainPanel();
    window.pack();
    window.setVisible(true);
  }  
  
}  
