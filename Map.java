import java.util.Arrays;

public class Map
{
    private GameObject[][] map;

    public Map(int x, int y)
    {
        map = new GameObject[y][x];
    }

    /** returns a pretty map with "." in place of empty tiles */
    public String toString()
    {
        String ans = "";
        
        for (GameObject[] row : map)
        {
            for (GameObject go : row)
            {
                if (go != null)
                    ans += " " + go.toString() + " ";
                else
                    ans += " . ";
            }
            ans += "\n";
        }
        
        return ans;
    }

    // TODO probably rename these
    public int getY() { 
        return map.length; 
    }
    
    public int getX() { 
        return map[0].length; 
    }
    
    public GameObject placeObject(int x, int y, GameObject go)
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
    public GameObject removeObject(int x, int y)
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
