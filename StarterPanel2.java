/*Hillary Ssemakula. Creates second panel displayed as user configures the race. It shows the user what name they type
 * What color they choose and how many participants they want in the race. It gives the user the option to start the race
 * or go back and change the details.
 */ 

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

public class StarterPanel2 extends JPanel
{
  private JTextArea startIntro; //Text area to display top information(user's choices) on panel
  
  //Constructor: sets size of panel nd calls the innitialize method. @params JButton start, JButton reenter,
  //Integer participants, String color, String name. parameters required by the innitilize method.
  public StarterPanel2(JButton start, JButton reenter, Integer participants, String color, String name)
  {
    setPreferredSize(new Dimension(400, 200));
    innitialize(start, reenter, participants, color, name); //innitialize method called to setup panel
  } 
  
  /*Method: innitialize. @params JButton start, JButton reenter, Integer participants, String color, String name
   * creates and adds text area to top of panel
   * adds the users chosen details to text area(participants, color, name)
   * adds start and reenter buttons to second panel
   */ 
  public void innitialize(JButton start, JButton reenter, Integer participants, String color, String name)
  {
    removeAll();
    setBackground(new Color(95,158,160));
    JPanel[] holderPanels = new JPanel[2]; //'holder panels' used to structure objects on panel.
    for(int i = 0; i < holderPanels.length; i++)
    {  
      holderPanels[i] = new JPanel();
      if(i == 0) {holderPanels[i].setPreferredSize(new Dimension(500, 150)); holderPanels[0].setBackground(new Color(70,130,180)); }//first holder panel made longer
      else holderPanels[i].setPreferredSize(new Dimension(500, 50)); //second holder panel
    }
    
    //Text area that shows what the user selected is created here. uses the name, color and participants parameters passed  to do this
    startIntro = new JTextArea("         CLICK START TO RACE\n         CLICK RE-ENTER TO MODIFY RACE\n"+
                                 "\n         RACE CONFIGURATION\n         Number of participants: "+participants+
                               "\n         Color of your Car: "+color+"\n         Your name: "+name, 5, 30);
    startIntro.setForeground(new Color(0,100,0));
    startIntro.setLineWrap(true);
    startIntro.setEditable(false);
    startIntro.setFont(new Font("dialog", Font.BOLD, 15));
    holderPanels[0].add(startIntro); //text area put on first 'holder panel'
    
    //buttons put on second 'holder panel'
    holderPanels[1].add(start);
    holderPanels[1].add(reenter); 
    
    for(JPanel p: holderPanels) add(p); //holder panels added to bigger panel(this panel)
  }
}  
