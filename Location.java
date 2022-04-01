
/**
 * Write a description of class Location here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Location extends GameObject
{
    // instance variables - replace the example below with your own
    private String owner;

    /**
     * Constructor for objects of class Location
     */
    public Location()
    {
        owner = "";
    }
    
    // returns the previous owner, updates the owner
    public String updateOwner(Player newOwner)
    {
        String oldOwner = owner;
        owner = newOwner.getID();
        return oldOwner;
    }
    
    // note: the id's of the players will be capitalized
    public String toString()
    {
        if (owner.equals(""))
            return "X";
        else
            return owner.toLowerCase();
    }
}
