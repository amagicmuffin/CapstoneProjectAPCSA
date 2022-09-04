public class Player extends GameObject
{
    private String id;

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
