import java.util.Arrays;
/**
 * Write a description of class Map here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Map
{
    private GameObject[][] map;

    /**
     * Constructor for objects of class Map
     */
    public Map(int x, int y)
    {
        map = new GameObject[y][x];
    }
    
    public String toString()
    {
        String str = "";
        
        for (GameObject[] row : map)
        {
            for (GameObject go : row)
            {
                if (go != null)
                    str += " " + go.toString() + " ";
                else
                    str += " . ";
            }
            str += "\n";
        }
        
        return str;
    }
    
    public int getY() { return map.length; }
    public int getX() { return map[0].length; }
    
    // go must be type GameObject, or a child type
    public GameObject placeObject (int x, int y, GameObject go)
    {
        if ((y >= 0 && y < map.length) && (x >= 0 && x < map[0].length))
        {
            GameObject temp = map[y][x];
            map[y][x] = go;
            return temp;
        }
        return null;
    }
    
    // for 2d arrays it is [row][column], which becomes [y][x] in this context
    public GameObject removeObject (int x, int y)
    {
        if ((y >= 0 && y < map.length) && (x >= 0 && x < map[0].length))
        {
            GameObject temp = map[y][x];
            map[y][x] = null;
            return temp;
        }
        return null;
    }
}
