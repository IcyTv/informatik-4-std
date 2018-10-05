import java.util.ArrayList;

/**
 * Write a description of class DieceShaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DieceShaker
{
    // instance variables - replace the example below with your own
    private ArrayList<Dice> dice;
    private int result;

    /**
     * Constructor for objects of class DieceShaker
     */
    public DieceShaker(int numberOfDice)
    {
        // initialise instance variables
        dice = new ArrayList<Dice>();
        result = 0;
        for(int i = 0; i < numberOfDice; i++){
            dice.add(new Dice(1, 6));
        }
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void throwDice()
    {
        int resultPriv = 0;
        for(Dice d: dice){
            resultPriv = d.throwDice();
            result += resultPriv;
        }
    }
    
    public int getResult(){
        return result;
    }
}
