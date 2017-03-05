// Hillary Ssemakula. Creates second panel displayed as user configures the race.
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class StarterPanel2 extends JPanel
{
  private JTextArea startIntro; //Text area to display top information on panel
  
  public StarterPanel2(JButton start, JButton reenter)
  {
    setPreferredSize(new Dimension(500, 200));
    innitialize(start, reenter); //innitialize method called to setup panel
  } 
  
  /*Method: innitialize. @params JButton start, JButton reenter
   * creates and adds text area to top of panel
   * adds start and reenter buttons to second panel
   */ 
  public void innitialize(JButton start, JButton reenter)
  {
    JPanel[] holderPanels = new JPanel[2]; //'holder panels' used to structure objects on panel.
    for(int i = 0; i < holderPanels.length; i++)
    {  
      holderPanels[i] = new JPanel();
      if(i == 0) holderPanels[i].setPreferredSize(new Dimension(500, 150)); //first holder panel made longer
      else holderPanels[i].setPreferredSize(new Dimension(500, 50)); //second holder panel
    }
    
    startIntro = new JTextArea("CLICK START TO RACE\n CLICK RESET TO MODIFY RACE\n", 5, 30);
    startIntro.setLineWrap(true);
    startIntro.setEditable(false);
    holderPanels[0].add(startIntro); //text area put on first 'holder panel'
    
    //buttons put on second 'holder panel'
    holderPanels[1].add(start);
    holderPanels[1].add(reenter); 
    
    for(JPanel p: holderPanels) add(p); //holder panels added to bigger panel(this panel)
  }
}  
