public class Location extends GameObject
{
    private boolean known;

    public Location()
    {
        known = false;
    }
    
    public void setKnown(boolean known)
    {
        this.known = known;
    }
    
    public boolean getKnown() { 
        return known; 
    }
    
    // note: the id's of the players will be capitalized
    public String toString()
    {
        if (known)
            return "X";
        else
            return "?";
    }
}
