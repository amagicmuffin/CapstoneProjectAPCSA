
/**
 * Write a description of class Location here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Location extends GameObject
{
    // instance variables - replace the example below with your own
    private boolean known;

    /**
     * Constructor for objects of class Location
     */
    public Location()
    {
        known = false;
    }
    
    public void setKnown(boolean known)
    {
        this.known = known;
    }
    
    public boolean getKnown() { return known; }
    
    // note: the id's of the players will be capitalized
    public String toString()
    {
        if (known)
            return "X";
        else
            return "?";
    }
}
