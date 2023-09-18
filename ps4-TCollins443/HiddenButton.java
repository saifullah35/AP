import javax.swing.*;

/**
 * This class covers up the values of the buttons 
 * with question marks, then implements methods
 * that when called will reveal the button value.
 */
public class HiddenButton extends JButton{
    private String buttonValue;
    private boolean isMatched = false;
   
    public HiddenButton(String buttonValue){
        super("?");
       this.buttonValue = buttonValue;
       boolean isMatched = false;
      if( buttonValue.equals("Reset") || buttonValue.equals("New Game") ){
          this.revealButton();
      }
    }

    /**
     * This method returns the value of the button when called.
     * @return The value of the button.
     */
    public String getButtonValue(){
     return buttonValue;
    }

    /**
     *  Method when called by newGame will set the value of 
     * the buttons on the grid.
     * @param value The value the button will be set to.
     */
    public void setValue(String value){
    this.buttonValue =  value;
    }


    /**
     * This method when called displays a "?" over the button
     * values on the screen.
     */
    public void hideButton(){
        this.setText("?");
    }

    /**
     * Method when called will reveal the value of the button on the screen.
     */
    public void revealButton(){
        this.setText("" + buttonValue );
    }
    
    public void setMatched(){
        isMatched = true;
    }
    public void resetMatched(){
      isMatched = false;

    }
    public boolean returnMatchedStatus(){
        if(isMatched){
           return true;
        }else{
            return false;
        }
    }
 
 
}
