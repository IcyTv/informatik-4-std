
/**
 * Write a description of class Dice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dice
{
    // instance variables - replace the example below with your own
    private int min, max;

    /**
     * Constructor for objects of class Dice
     */
    public Dice(int min, int max)
    {
        // initialise instance variables
        this.min = min;
        this.max = max;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int throwDice()
    {
        // put your code here
        return min + (int)(Math.random() * (max - min) + 1);
    }
}
