import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** 
 * Sets up a GUI interface with 6 X 6 grid of 
 * buttons, a rest button and new game button
 * The Button are e labeled with numbers in the range 0-99, with each
 * number in that range occurring either 0 or 2 times.
 * When any button in the grid is pressed, the label 
 * at the top of the window should display the
 * row and column and value of the button that was pressed
 * 
 * @auther(Jonathan Masih , Saif Ullah, Trevor Collins)
 * @version Spring 2022
*/

// we have many interfaces implemented here and class that the GUI
public class Concentration implements Runnable, ActionListener, ChangeListener{
 
    private JLabel welcomeAndPressed = new JLabel("Welcome to Concentration!");
    //Shows what button was pressed

    //Reset Button
    JButton resetButton = new JButton("Reset"); 
    //New game button
    JButton newGameButton = new JButton("New Game"); 


    JButton [][] buttons = new JButton[6][6];
    
    
     
    //Lists to check which number have been added as buttons 
    ArrayList<Integer> alist = new ArrayList<Integer>(); 
    ArrayList<Integer> blist = new ArrayList<Integer>();
    
    //Random value to be added as a button
    int randValue;

 @Override
  public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton temp = (JButton) e.getSource();
        for(int i = 0; i < buttons.length; i++){
         for(int j  = 0; j< buttons.length; j++){
           if(buttons[i][j] == temp){
            welcomeAndPressed.setText("Pressed " + buttons[i][j].getText() + " at "+ "(" + i + "," + j + ")" );
           }
         }
        }
        if(temp == resetButton){
            welcomeAndPressed.setText("Welcome to Concentration!");
        }
    }
    

    /**
     * contains the executable code of the thread and creates an GUI
     */
    @Override
    public void run() {
        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

         // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        JFrame frame = new JFrame("Concentration");
        frame.setPreferredSize(new Dimension(500, 500));

         // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JPanel to add all the panels to then frame
        JPanel masterPanel = new JPanel(new BorderLayout());
       
       //JPanel for the welcome text and to show which button us being pressed
       JPanel welcomeAndPressedPanel = new JPanel();
       welcomeAndPressedPanel.add(welcomeAndPressed  , BorderLayout.CENTER);
      
       
       //JPanel For buttons 6X6 grid 
       JPanel buttonsPanel = new JPanel(new GridLayout(6,6));

       //JPanel for reset button and new game button
       JPanel newGameAndReset = new JPanel();
       
       newGameAndReset.add(newGameButton);
       newGameAndReset.add(resetButton);
        //Loops to add buttons to the grid and to the 2d array buttons
       for(int i = 0; i < 6 ; i++){
         for( int j = 0; j < 6; j++){
            buttons[i][j] = new JButton(""+ generatesNewValue());
            buttons[i][j].addActionListener(this);
            buttonsPanel.add(buttons[i][j]); } }

        masterPanel.add(welcomeAndPressedPanel , BorderLayout.NORTH);
        masterPanel.add(buttonsPanel, BorderLayout.CENTER);
        masterPanel.add(newGameAndReset, BorderLayout.SOUTH);
        
        
        frame.add(masterPanel);
        frame.pack();
        frame.setVisible(true);
        
    }
    /**
     * Checks if the random number has been added to the grid 
     * two times or not and returns a new value that is now added
     * as a button to grid yet
     * 
     * @return  returns a number that has not been add two times.
     */ 
    public int generatesNewValue(){
         int returnNum;
        while(true){
         randValue = (int)(Math.random()*(99-0+1)+0);
          if(!alist.contains(randValue)){
           alist.add(randValue);
           returnNum = randValue;
           break;
          }else if(!blist.contains(randValue)){
            blist.add(randValue);
            returnNum = randValue;
            break;
          }
        }
        return returnNum;
        }


    /** 
     * The main method is responsible for creating a thread that
        will construct and show the graphical user interface.
     * 
     */
     public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Concentration());
    }



}




