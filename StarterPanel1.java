//Hillary Ssemakula
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class StarterPanel1 extends JPanel
{
  private JTextArea intro;
  private JLabel numOpp;
  private JLabel usrColor;
  private JLabel usrName;
  private JTextField numOppTxt;
  private JTextField colorTxt;
  private JTextField nameTxt;
  
  public StarterPanel1(JButton join, JButton cancel) 
  {
    setPreferredSize(new Dimension(500, 350));
    innitialize(join, cancel);
  }
  
  public void innitialize(JButton join, JButton cancel)
  {
    JPanel[] holderPanels = new JPanel[5];
    for(int i = 0; i < holderPanels.length; i++)
    {  
      holderPanels[i] = new JPanel();
      if(i == 0) holderPanels[i].setPreferredSize(new Dimension(500, 170));
      else holderPanels[i].setPreferredSize(new Dimension(500, 35));
    }
    
    intro = new JTextArea("Welcome to the SRH Car Simulation\n Choose the number of opponents\n"+
                          "Choose a color for a car and provide your name\n", 7, 30);
    intro.setLineWrap(true);
    intro.setEditable(false);
    holderPanels[0].add(intro);
    
    numOpp = new JLabel("CHOOSE NUMBER OF OPPONENTS: ");
    numOppTxt = new JTextField(10);
    holderPanels[1].add(numOpp); holderPanels[1].add(numOppTxt);
    usrColor = new JLabel("CHOOSE COLOR OF CAR: ");
    colorTxt = new JTextField(10);
    holderPanels[2].add(usrColor); holderPanels[2].add(colorTxt);
    usrName = new JLabel("CHOOSE NAME OF CAR: ");
    nameTxt = new JTextField(10);
    holderPanels[3].add(usrName); holderPanels[3].add(nameTxt);
    
    holderPanels[4].add(join);
    holderPanels[4].add(cancel); 
    
    for(JPanel p: holderPanels) add(p);
  }
  
  public Integer getOpp()
  {
    return 0;
  }
  
  public Color getUserColor()
  {
    return null;
  }
  
  public String getUsrName()
  {
    return "";
  }  
}
