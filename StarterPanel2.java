import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class StarterPanel2 extends JPanel
{
  private JTextArea startIntro;
  
  public StarterPanel2(JButton start, JButton reenter)
  {
    setPreferredSize(new Dimension(500, 200));
    innitialize(start, reenter);
  } 
  
  public void innitialize(JButton start, JButton reenter)
  {
    JPanel[] holderPanels = new JPanel[2];
    for(int i = 0; i < holderPanels.length; i++)
    {  
      holderPanels[i] = new JPanel();
      if(i == 0) holderPanels[i].setPreferredSize(new Dimension(500, 150));
      else holderPanels[i].setPreferredSize(new Dimension(500, 50));
    }
    
    startIntro = new JTextArea("CLICK START TO RACE\n CLICK RESET TO MODIFY RACE\n", 5, 30);
    startIntro.setLineWrap(true);
    startIntro.setEditable(false);
    holderPanels[0].add(startIntro);
    
    holderPanels[1].add(start);
    holderPanels[1].add(reenter); 
    
    for(JPanel p: holderPanels) add(p);
  }
}  