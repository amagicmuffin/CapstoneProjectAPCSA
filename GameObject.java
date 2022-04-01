
/**
 * Write a description of class GameObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameObject
{
    private String name = new String();
    
    public GameObject(String name)
    {
       this.name = name;
    }
    
    public GameObject() {}
    
    public String getName()
    {
        return name;
    }
    
    public boolean equals(GameObject go)
    {
        return go.getName().equals(this.name);
    }
}
