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
public class Concentration implements Runnable, ActionListener, ChangeListener {

  private JLabel topGameLabel = new JLabel("Round 1 Red's Turn");
  // Shows what button was pressed

  // Number of rows in the grid of buttons
  final int ROWS = 2;
  // Number of rows in the grid of buttons
  final int COLS = 3;

  // Reset Button
  HiddenButton resetButton = new HiddenButton("Reset");
  // New game button
  HiddenButton newGameButton = new HiddenButton("New Game");
  // Button grid
  HiddenButton[][] buttons = new HiddenButton[ROWS][COLS];

  // arraylist to keep track of buttons pressed
  ArrayList<HiddenButton> pressed = new ArrayList<HiddenButton>();

  // Round counter
  private int round = 1;

  //Number of clicks
  private int clicks = 0;

  // Players
  private String playerRed = "Red";
  private String playerBlue = "Blue";
  private String currentPlayer;

  //player scores 
  int playerRedScore = 0;
  int playerBlueScore = 0;

  @Override
  public void stateChanged(ChangeEvent e) {
    // TODO Auto-generated method stub

  }

  /**
   * This method is called when a action is performed
   * on a button. Set's the label to the value of the button
   * that's pressed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
   
    //Second turn for the current player

    HiddenButton temp =  (HiddenButton) e.getSource();
    if(temp.returnMatchedStatus() == false ){
      clicks++;
    }

     if(clicks % 3 == 0 ){
      round++;
      checkMatchedButton();
     }

    //add button to the arraylist where the we check if two buttons are equal
    if(temp.returnMatchedStatus() == false){
     if (temp.getButtonValue() == resetButton.getButtonValue()) {
      restGame();
     }else if (temp.getButtonValue() == newGameButton.getButtonValue()) {
      newGame();
      }else if(clicks % 3 != 0){
      pressed.add(temp);
     }
     if(clicks % 3 != 0 ){
      temp.revealButton();
     }
     if(clicks == 1){
     topGameLabel.setText(currentPlayer + " player selects Again");
     }

     if(pressed.size() == 3){
      pressed.clear();
     }
    }
    
    if(checkEndGame() == true){
      if( playerRedScore < playerBlueScore ){
       topGameLabel.setText("Blue wins " +  playerBlueScore  + " matches to " + playerRedScore );
       return;
      }else{
       topGameLabel.setText("Red wins " +  playerRedScore  + " matches to " + playerBlueScore );
       return;
      }
     }  

    
  
  }
  public boolean checkEndGame(){
     for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        if(buttons[i][j].returnMatchedStatus() == false){
           return false;
        }
      }
   }
   return true;
  }


  
  public void checkMatchedButton(){
    if(pressed.get(0).getButtonValue().equals(pressed.get(1).getButtonValue())){
        pressed.get(0).revealButton();
        pressed.get(1).revealButton();
        pressed.get(0).setMatched();
        pressed.get(1).setMatched();
        if(currentPlayer == playerRed){
          pressed.get(0).setForeground(Color.RED);
          pressed.get(1).setForeground(Color.RED);
        }else{
          pressed.get(0).setForeground(Color.BLUE);
          pressed.get(1).setForeground(Color.BLUE);
        }    
        if(currentPlayer == playerRed){
          playerRedScore++;
        }else{
          playerBlueScore++;
        }
        
        topGameLabel.setText("Match for " + currentPlayer + " press any to continue");
       }else{
        pressed.get(0).hideButton();
        pressed.get(1).hideButton();    
        topGameLabel.setText("Mismatch for " + currentPlayer + " press any to continue");
        if( currentPlayer ==  playerRed){
          currentPlayer = playerBlue;
        }else{
          currentPlayer = playerRed;
        }

       }
       topGameLabel.setText("Round " + round +": " + currentPlayer + "'s"  + " Turn" );
       pressed.clear();
          clicks = 0;
    }
  
       
  public void restGame(){
    topGameLabel.setText("Round 1 Red's Turn");
      for (int i = 0; i < ROWS; i++) {
         for (int j = 0; j < COLS; j++) {
          buttons[i][j].hideButton();
          buttons[i][j].resetMatched();
          buttons[i][j].setForeground(Color.BLACK);
        }
      }

    pressed.clear();
    clicks = 0;
    playerRedScore = 0;
    playerBlueScore = 0;
    round = 1;
    currentPlayer = playerRed;
  }
   

  public void newGame(){
    topGameLabel.setText("Round 1 Red's Turn");
    ArrayList<Integer> alist = new ArrayList<Integer>();
    alist = listOfValuesForButtons();
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        buttons[i][j].setValue("" + alist.get(0));
        buttons[i][j].hideButton();
        buttons[i][j].resetMatched();
        buttons[i][j].setForeground(Color.BLACK);
        alist.remove(0);
      }
    }
    clicks = 0;
    pressed.clear();
   playerRedScore = 0;
    playerBlueScore = 0;
    round = 1;
    currentPlayer = playerRed;
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

    // JPanel to add all the panels to then frame
    JPanel masterPanel = new JPanel(new BorderLayout());

    // JPanel for the welcome text and to show which button us being pressed
    JPanel welcomeAndPressedPanel = new JPanel();
    welcomeAndPressedPanel.add(topGameLabel, BorderLayout.CENTER);

    // JPanel For buttons 6X6 grid
    JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
    // JPanel for reset button and new game button
    JPanel newGameAndReset = new JPanel();

    newGameAndReset.add(newGameButton);
    newGameAndReset.add(resetButton);
  
    // set the player at the begginging 
    currentPlayer =  playerRed;

    // Code to get random 18 pairs of random values from (0-99)
    ArrayList<Integer> alist = new ArrayList<Integer>();
    alist = listOfValuesForButtons();

    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        buttons[i][j] = new HiddenButton("" + alist.get(0));
        alist.remove(0);
        buttons[i][j].addActionListener(this);
        buttonsPanel.add(buttons[i][j]);
      }
    }
    resetButton.addActionListener(this);
    newGameButton.addActionListener(this);

    masterPanel.add(welcomeAndPressedPanel, BorderLayout.NORTH);
    masterPanel.add(buttonsPanel, BorderLayout.CENTER);
    masterPanel.add(newGameAndReset, BorderLayout.SOUTH);

    frame.add(masterPanel);
    frame.pack();
    frame.setVisible(true);

  }

  /**
   * Randomly genrates numbers 0-99 and then puts then
   * into a Arraylist
   * 
   * @return returns an arraylist of numbers for buttons
   */
  public ArrayList<Integer> listOfValuesForButtons() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    while (true) {
      int numberOfValues = (ROWS * COLS) / 2;
      int randValue = (int) (Math.random() * (99 - 0 + 1) + 0);
      if (list.size() == numberOfValues) {
        break;
      } else if (list.contains(randValue)) {
        randValue = (int) (Math.random() * (99 - 0 + 1) + 0);
      } else {
        list.add(randValue);
      }
    }
    list.addAll(list);
    Collections.shuffle(list);
    return list;
  }

  /**
   * The main method is responsible for creating a thread that
   * will construct and show the graphical user interface.
   * 
   */
  public static void main(String args[]) {
    javax.swing.SwingUtilities.invokeLater(new Concentration());
  }

}
