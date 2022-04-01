
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends GameObject
{
    // instance variables - replace the example below with your own
    private String id;

    /**
     * Constructor for objects of class Player
     */
    public Player(String id)
    {
        this.id = id;
    }
    
    public String toString()
    {
        return id.toUpperCase();
    }
    
    public String getID()
    {
        return id.toUpperCase();
    }
    
    public boolean equals(Player p)
    {
        if (this.id.equals(p.getID()))
            return true;
        else
            return false;
    }
}
