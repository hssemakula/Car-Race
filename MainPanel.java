//Hillary Ssemakula
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel implements ActionListener
{
   private RaceEvent event;
  private JButton join;
  private JButton cancel;
  private JButton start;
  private JButton reenter;
  private StarterPanel1 starterPanel1;
  private StarterPanel2 starterPanel2;
  private JPanel infoPanel;
  private JPanel scorePanel;
  private Timer timer;
  private boolean status;
  public static JFrame window;
  
  public MainPanel() 
  {
    setPreferredSize(new Dimension(1000, 700));
    innitialize();
  }
  
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
  }
  
  public void paintComponent(ActionEvent e)
  {
    
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == cancel) System.exit(0);
    if(e.getSource() == join)
    {
      window.remove(starterPanel1);
      window.getContentPane().add(starterPanel2);
      window.setSize(520, 240);
      window.validate();
    }
    if(e.getSource() == reenter)
    {
      window.remove(starterPanel2);
      window.getContentPane().add(starterPanel1);
      window.setSize(520, 390);
      window.validate();
    }
    if(e.getSource() == start)
    {
      window.remove(starterPanel2);
      window.getContentPane().add(this);
      window.setSize(1020, 740);
      window.validate();
      window.repaint();
    }  
  }
  
  public static void main(String[] args)
  {
    window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MainPanel p = new MainPanel();
    window.pack();
    window.setVisible(true);
  }  
  
}  
