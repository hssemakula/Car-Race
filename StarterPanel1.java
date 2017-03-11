/* Hillary Ssemakula. StarterPanel1 class. creates first panel user sees when they start game.
 * Contains form used to collect certain configurations for a race as determined by the user
 * such as number of participants, name and color of user's car.
 */
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class StarterPanel1 extends JPanel
{
  private JTextArea intro; //TextArea variable to display intoduction
  //All labels that request information from users are created.
  private JLabel numOpp;
  private JLabel usrColor;
  private JLabel usrName;
  
  //All textfield variables that will collect information are made
  private JTextField numOppTxt;
  private JTextField nameTxt;
  //String array to store selectable color choices
  private String[] choices;
  //JComboBox that will use array above
  private JComboBox<String> listChoices;
  
  //Constructor: sets size of panel and calls the innitilize method. @param JButton join, JButton cancel.
  //parameters required by innitialize method.
  public StarterPanel1(JButton join, JButton cancel) 
  {
    setPreferredSize(new Dimension(500, 350));
    innitialize(join, cancel); //innitialize method called to set up panel
  }
  
  /* Method: innitialize. @params JButton join, JButton cancel
   * adds the join and cancel buttons to panel.
   * creates color choices(String values) to be displayed by jcombobox 
   * adds intro textArea, all jlabels, textfields and JCombobox to Panel.
   */ 
  public void innitialize(JButton join, JButton cancel)
  {
    removeAll();
    setBackground(new Color(95,158,160));
    choices = new String[]{"Black", "Blue","Cyan","Gray","Green","Magenta","Orange",
      "Pink","Red","White", "Yellow"};
    listChoices = new JComboBox<String>(choices);
    
    JPanel[] holderPanels = new JPanel[5]; //this array contains 'holder panels used purely for organization of other objects on bigger panel
    for(int i = 0; i < holderPanels.length; i++)
    {  
      holderPanels[i] = new JPanel();
      if(i == 0) { holderPanels[i].setPreferredSize(new Dimension(500, 170)); holderPanels[0].setBackground(new Color(70,130,180)); }
      else holderPanels[i].setPreferredSize(new Dimension(500, 35));
    }
    //Introduction text added to text area
    intro = new JTextArea("         WELCOME TO THE SAHIRO GRANDPRIX\n\n       Choose the number of opponents\n"+
                          "     Choose a color for a car and provide your name\n", 7, 30);
    intro.setLineWrap(true);
    intro.setEditable(false);
    intro.setForeground(new Color(0,0,128));
    intro.setFont(new Font("dialog", Font.PLAIN, 18));
    JScrollPane scroller = new JScrollPane(intro, 22, 32);
    holderPanels[0].add(scroller); //JScrollPane with text area is added to first holder panel
    
    //In this block of code, every label and its accompanying textfield are created and added on to a holder panel
    numOpp = new JLabel("CHOOSE NUMBER OF CARS IN RACE(2-10): ");
    numOppTxt = new JTextField(10);
    holderPanels[1].add(numOpp); holderPanels[1].add(numOppTxt);
    usrColor = new JLabel("CHOOSE COLOR OF CAR: ");
    holderPanels[2].add(usrColor); holderPanels[2].add(listChoices);
    usrName = new JLabel("CHOOSE NAME OF CAR: ");
    nameTxt = new JTextField(10);
    holderPanels[3].add(usrName); holderPanels[3].add(nameTxt);
    
    holderPanels[4].add(join);
    holderPanels[4].add(cancel); 
    //all objects placed on 'holder panels' are then placed on the bigger panel
    for(JPanel p: holderPanels) add(p); 
  }
  
  /* Method getOpp: returns integer value representing the number of participants that the user wants.
   *                this number is extracted from the numOppTxt text field.
   */
  public Integer getOpp()
  {
    int num;
    //try-catch harness extracts digit if actual digit is entered and sets num to it, else num is set to -1
    try
    {
      num = Integer.parseInt(numOppTxt.getText()); 
    }
    catch(Exception e)
    {
      num = -1;
    }
    return num;
  }

  /* Method getUsrColor: returns Color Object corresponding to color choice selected on drop down by user
   * this color choice is extracted as a string from the listChoices jcombobox's selected choice
   */
  public Color getUsrColor()
  {
    String colorChoice = (String)listChoices.getSelectedItem(); //string value of selected choice stored
    //corresponding Color object return i.e Color.black for "Black"
    if(colorChoice.equals("Black")) return Color.black; 
    else if(colorChoice.equals("Blue")) return Color.blue;
    else if(colorChoice.equals("Cyan")) return Color.cyan;
    else if(colorChoice.equals("Gray")) return Color.gray;
    else if(colorChoice.equals("Green")) return Color.green;
    else if(colorChoice.equals("Magenta")) return Color.magenta;
    else if(colorChoice.equals("Orange")) return Color.orange;
    else if(colorChoice.equals("Pink")) return Color.pink;
    else if(colorChoice.equals("Red")) return Color.red;
    else if(colorChoice.equals("White")) return Color.white; 
    else if(colorChoice.equals("Yellow")) return Color.yellow;
    else return null;
  }
  
  //method colorString returns string value of the user's chosen color
  public String colorString(){ return  (String)listChoices.getSelectedItem();}
  
  //method getUsrName returns string that user inserts in the nameTxt Text Field.
  public String getUsrName()
  {
    return nameTxt.getText();
  } 
  
}
