public class Player extends GameObject
{
    public String id;
    public String name;

    public int x = 1; // renamed from px, fix
    public int y = 1; // renamed from py, fix
    public int dmg = 5;
    public int hp = 20;
    public int blk = 10;
    public int gold = 0;
    public int winCount = 0;

    public Player(String name)
    {
        this.id = generateID(name);
        this.name = name;
    }

    public void changeName(String name) {
        this.id = generateID(name);
        this.name = name;
    }

    private String generateID(String name) {
        try {
            return name.substring(0, 1).toUpperCase();
        } catch (Exception e) { // if first char is not toUpperCase-able
            return "@";
        }
    }
    
    public String toString()
    {
        return id;
    }
    
    public boolean equals(Player p)
    {
        if (this.id.equals(p.id))
            return true;
        else
            return false;
    }
}
