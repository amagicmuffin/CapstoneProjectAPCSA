import java.util.Scanner;
/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver
{
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        Map map = new Map(4, 5); // creates a map 4 wide 5 high
        
        Player p1 = new Player("A");
        int p1x = 0;
        int p1y = 2;
        
        Location castle = new Location();
        
        map.placeObject(p1x, p1y, p1); // places objects (starts counting from 0)
        map.placeObject(2, 3, castle);
        
        String input = new String();
        while (!input.equalsIgnoreCase("q"))
        {   
            display(map);
            
            do { input = scan.nextLine(); }
            while (input.equals(""));
            
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("w"))            
                continue;
            else if (input.equalsIgnoreCase("m"))
            {
                System.out.print("\nWhich direction? (U/D/L/R for up/down/left/right) ");
                do { input = scan.next(); }
                while (input.length() != 1);
                
                char c = input.charAt(0);
                
                switch (c)
                {
                    case 'U': case 'u':
                        if (p1y > 0)
                        {
                            map.removeObject(p1x, p1y);
                            System.out.println(map.placeObject(p1x, --p1y, p1));
                        }
                        break;
                    case 'D': case 'd':
                        if (p1y < map.getY() - 1)
                        {
                            map.removeObject(p1x, p1y);
                            System.out.println(map.placeObject(p1x, ++p1y, p1));
                        }
                        break;
                    case 'R': case 'r':
                        if (p1x < map.getX() - 1)
                        {
                            map.removeObject(p1x, p1y);
                            if (map.placeObject(++p1x, p1y, p1) != null)
                            {
                                cutscene();
                            }
                        }
                        break;
                    case 'L': case 'l':
                        if (p1x > 0)
                        {
                            map.removeObject(p1x, p1y);
                            System.out.println(map.placeObject(--p1x, p1y, p1));
                        }
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        }
    }
    
    private static void display(Map map)
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(map);
            
        System.out.println("\n\nTake an action:\n-----------------------");
        System.out.println("M - move\nQ - quit\nW - wait");
    }
    
    private static void cutscene()
    {
        String[] enemies = {"Goblin","Skeleton","Bandit","Thief","Gremlin","Zombie","Spider"};
        String[] adjectives = {"Mad","Bloodthirsty","Starving","Rampaging","Sneaky", "Short", "Tall", "Fat", "Hungry"};
        
        String enemy = enemies[(int) (Math.random() * 7)];
        int r = (int) (Math.random() * 10);
        if(r == 9)
            enemy = "Golden " + enemy;
        else
            enemy = adjectives[r] + " " + enemy;
            
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("A " + enemy + " approaches you!");
        
    }
}
