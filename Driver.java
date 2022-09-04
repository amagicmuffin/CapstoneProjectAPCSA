import java.util.Scanner;
// TODO put long userinput blocks like wasd into their own functions
public class Driver {
    // ansi isn't supported on windows, except git bash.
    // ansi supported mostly everywhere else, including replit
    // toggle this as needed
    public static final boolean USE_ANSI_CODES = true;

    public static Player p;
    public static Map map = new Map(12, 12); // creates a map 12 wide 12 high (12x, 12y)

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Text.useANSI = USE_ANSI_CODES;

        String name = intro();

        p = new Player(name.substring(0,1));

        populateMap();

        String input = new String();
        while (!input.equalsIgnoreCase("q")) {
            display(map);

            do { // get user input
                input = scan.nextLine();
            }
            while (input.equals(""));

            if (input.equalsIgnoreCase("q")) { // user input - quit game
                continue;
            } else if (input.equalsIgnoreCase("jeff bezos")) { // user input - cheat code
                p.gold += 9999;
            } else if (input.equalsIgnoreCase("saitama")) { // user input
                p.hp = 9999;
                p.dmg = 999;
                p.blk = 999;
            } else if (input.equalsIgnoreCase("b")) { // user input - buy item
                Text.cls();
                System.out.println("Welcome to the Buff Shack!\nYou can purchase stat buffs for gold here, such as:\n" +
                        "(A) 10 HP - 25 gold\t\t\t(D) 40 HP - 80 gold\n" +
                        "(B) 5 DMG - 30 gold\t\t\t(E) 20 DMG - 90 gold\n" +
                        "(C) 5 BLK - 15 gold\t\t\t(F) 20 BLK - 45 gold\n\n" +
                        "Which item letter would you like to purchase?\n");

                // player input
                do {
                    input = scan.nextLine();
                }
                while (input.equals(""));

                char c = input.charAt(0);

                switch (c) {
                    case 'A':
                    case 'a':
                        if (p.gold >= 25) {
                            p.hp += 10;
                            p.gold -= 25;
                            System.out.println("\nThank you for your purchase! Your HP is now " + p.hp);
                        } else {
                            System.out.println("\nGet some money and then we'll bargain.");
                        }
                        break;
                    case 'B':
                    case 'b':
                        if (p.gold >= 30) {
                            p.dmg += 5;
                            p.gold -= 30;
                            System.out.println("\nThank you for your purchase! Your DMG is now " + p.dmg);
                        } else {
                            System.out.println("\nGet some money and then we'll bargain.");
                        }
                        break;
                    case 'C':
                    case 'c':
                        if (p.gold >= 15) {
                            p.blk += 5;
                            p.gold -= 15;
                            System.out.println("\nThank you for your purchase! Your BLK is now " + p.blk);
                        } else {
                            System.out.println("\nGet some money and then we'll bargain.");
                        }
                        break;
                    case 'D':
                    case 'd':
                        if (p.gold >= 80) {
                            p.hp += 40;
                            p.gold -= 80;
                            System.out.println("\nThank you for your purchase! Your HP is now " + p.hp);
                        } else {
                            System.out.println("\nGet some money and then we'll bargain.");
                        }
                        break;
                    case 'E':
                    case 'e':
                        if (p.gold >= 90) {
                            p.dmg += 20;
                            p.gold -= 90;
                            System.out.println("\nThank you for your purchase! Your DMG is now " + p.dmg);
                        } else {
                            System.out.println("\nGet some money and then we'll bargain.");
                        }
                        break;
                    case 'F':
                    case 'f':
                        if (p.gold >= 45) {
                            p.blk += 20;
                            p.gold -= 45;
                            System.out.println("\nThank you for your purchase! Your BLK is now " + p.blk);
                        } else {
                            System.out.println("\nGet some money and then we'll bargain.");
                        }
                        break;
                }

                Text.wait(3);
            } else if (input.equalsIgnoreCase("w")) { // user input
                if (p.y > 0) {
                    map.removeObject(p.x, p.y);
                    if (map.placeObject(p.x, --p.y, p) != null) {
                        String upgrade = cutscene(p.hp, p.dmg, p.blk, p.gold, p.winCount);
                        int i = (int) (Math.random() * 10 + 5);
                        p.gold += i;
                        p.winCount++;
                        System.out.println("You got " + i + " p.gold!");

                        if (upgrade.equalsIgnoreCase("H")) {
                            p.hp += 10;
                            System.out.println("Max HP increased to " + p.hp + ".\n");
                        } else if (upgrade.equalsIgnoreCase("a")) {
                            p.dmg += 3;
                            System.out.println("Damage increased to " + p.dmg + ".\n");
                        } else if (upgrade.equalsIgnoreCase("B")) {
                            p.blk += 6;
                            System.out.println("Block increased to " + p.blk + ".\n");
                        }

                        Text.wait(1);
                    }
                }
            } else if (input.equalsIgnoreCase("s")) {
                if (p.y < map.getY() - 1) {
                    map.removeObject(p.x, p.y);
                    if (map.placeObject(p.x, ++p.y, p) != null) {
                        String upgrade = cutscene(p.hp, p.dmg, p.blk, p.gold, p.winCount);
                        int i = (int) (Math.random() * 10 + 5);
                        p.gold += i;
                        p.winCount++;
                        System.out.println("You got " + i + " p.gold!");

                        if (upgrade.equalsIgnoreCase("H")) {
                            p.hp += 10;
                            System.out.println("Max HP increased to " + p.hp + ".\n");
                        } else if (upgrade.equalsIgnoreCase("a")) {
                            p.dmg += 3;
                            System.out.println("Damage increased to " + p.dmg + ".\n");
                        } else if (upgrade.equalsIgnoreCase("B")) {
                            p.blk += 6;
                            System.out.println("Block increased to " + p.blk + ".\n");
                        }

                        Text.wait(1);
                    }
                }
            } else if (input.equalsIgnoreCase("d")) {
                if (p.x < map.getX() - 1) {
                    map.removeObject(p.x, p.y);
                    if (map.placeObject(++p.x, p.y, p) != null) {
                        if (p.x == 11 && p.y == 11) {
                            bossfight(p.hp, p.dmg, p.blk);
                        } else {
                            String upgrade = cutscene(p.hp, p.dmg, p.blk, p.gold, p.winCount);
                            int i = (int) (Math.random() * 10 + 5);
                            p.gold += i;
                            p.winCount++;
                            System.out.println("You got " + i + " p.gold!");

                            if (upgrade.equalsIgnoreCase("H")) {
                                p.hp += 10;
                                System.out.println("Max HP increased to " + p.hp + ".\n");
                            } else if (upgrade.equalsIgnoreCase("a")) {
                                p.dmg += 3;
                                System.out.println("Damage increased to " + p.dmg + ".\n");
                            } else if (upgrade.equalsIgnoreCase("B")) {
                                p.blk += 6;
                                System.out.println("Block increased to " + p.blk + ".\n");
                            }

                            Text.wait(1);
                        }
                    }
                }
            } else if (input.equalsIgnoreCase("a")) {
                if (p.x > 0) {
                    map.removeObject(p.x, p.y);
                    if (map.placeObject(--p.x, p.y, p) != null) {
                        String upgrade = cutscene(p.hp, p.dmg, p.blk, p.gold, p.winCount);
                        int i = (int) (Math.random() * 10 + 5);
                        p.gold += i;
                        p.winCount++;
                        System.out.println("You got " + i + " p.gold!");

                        if (upgrade.equalsIgnoreCase("H")) {
                            p.hp += 10;
                            System.out.println("Max HP increased to " + p.hp + ".\n");
                        } else if (upgrade.equalsIgnoreCase("a")) {
                            p.dmg += 3;
                            System.out.println("Damage increased to " + p.dmg + ".\n");
                        } else if (upgrade.equalsIgnoreCase("B")) {
                            p.blk += 6;
                            System.out.println("Block increased to " + p.blk + ".\n");
                        }

                        Text.wait(1);
                    }
                }
            }

            // Debug statements at bottom of game loop 
            // so they get pushed up when a new map is printed
            System.out.println("DEBUG: p.x > " + p.x);
            System.out.println("DEBUG: p.y > " + p.y);
        } // game loop
    } // main function

    private static void populateMap() {
        map.placeObject(p.x, p.y, p); // places the player at almost the top-left corner

        for (int i = 0; i < 3; i++) // places locations in the top-left quadrant (except for where the player is)
        {
            map.placeObject((int) (Math.random() * 4) + 2, (int) (Math.random() * 4) + 2, new Location());
        }
        for (int i = 0; i < 3; i++) // places locations in the top-right quadrant
        {
            map.placeObject((int) (Math.random() * 6) + 6, (int) (Math.random() * 6), new Location());
        }
        for (int i = 0; i < 3; i++) // places locations in the bottom-left quadrant
        {
            map.placeObject((int) (Math.random() * 6), (int) (Math.random() * 6) + 6, new Location());
        }
        for (int i = 0; i < 3; i++) // places locations in the bottom-right quadrant except for the corner
        {
            map.placeObject((int) (Math.random() * 6) + 5, (int) (Math.random() * 6) + 5, new Location());
        }

        map.placeObject(11, 11, new Location());
    }
    
    private static void display(Map map) {
        Text.cls();
        System.out.println(map);

        System.out.println("\n\nTake an action:\n-----------------------");
        System.out.println("M - move\nQ - quit\nB - buy\n");
        System.out.println("Gold: " + p.gold + "\t\tHP: " + p.hp);
        System.out.println("Damage: " + p.dmg + "\tBlock: " + p.blk);
        System.out.println();
    }

    private static void bossfight(int pHP, int pDMG, int pBLK) {
        Scanner scan = new Scanner(System.in);

        String[] adjectives = {"Disgraced", "Warrior", "Dragon", "Sinister", "Detestable"};
        String enemy = "Draucabra the" + adjectives[(int) (Math.random() * 5)];

        Text.cls();
        System.out.println("BOSS FIGHT: " + enemy);

        int eHP = 150;
        int eDMG = 30;

        while (pHP != 0 && eHP != 0) {
            // display stats
            System.out.println("Player Stats:\t\t\tBoss Stats:\n" +
                    "HP: " + pHP + "\t\t\tHP: " + eHP + "\n" +
                    "DMG: " + pDMG + "\t\t\tDMG: " + eDMG + "\n" +
                    "BLK: " + pBLK);

            // player's options
            System.out.println("\nA - Attack\tB - Block\nQ - Quit the game (NOT just this fight, the ENTIRE game)\n");

            // player turn input
            String input = new String();
            do {
                input = scan.nextLine();
            }
            while (input.equals(""));

            Text.cls();
            boolean blocking = false;
            // player turn
            if (input.equalsIgnoreCase("Q")) {
                System.exit(0);
            } else if (input.equalsIgnoreCase("A")) // attack the enemy
            {
                eHP -= pDMG;
                if (eHP < 0) {
                    eHP = 0;
                }
                System.out.println("You did " + pDMG + " damage! The enemy is now " + eHP + " HP.");
            } else if (input.equalsIgnoreCase("B")) // block enemy's attack
            {
                System.out.println("Blocking for " + pBLK + " damage.");
                blocking = true;
            }
            System.out.println();

            // enemy turn
            int action = (int) (Math.random() * 4);
            if (action > 2 && eHP > 0) // attack the player
            {
                int d = eDMG;
                if (blocking) {
                    d -= pBLK;
                }
                if (d < 0) {
                    d = 0;
                }
                pHP -= d;
                if (pHP < 0) {
                    pHP = 0;
                }

                System.out.println(enemy + " attacked you and you lost " + d + " HP!");
            } else if (action == 0 && eHP > 0) // does nothing
            {
                System.out.println(enemy + " glares at you menacingly.");
            } else if (action == 1 && eHP > 0) // does nothing
            {
                System.out.println(enemy + " missed his attack.");
            }
            System.out.println();
        }

        if (eHP == 0) {
            Text.slowPrint("\nHow...");
            Text.wait(2000);

            Text.slowPrint("\nhow is this possible!?");
            Text.wait(2000);

            Text.slowPrint("\nHow was I defeated?");
            Text.wait(2000);

            Text.slowPrint("\nI am a god, how was I defeated by a mortal like...");
            Text.wait(2000);

            Text.slowPrint("\n.");
            Text.wait(2000);

            Text.slowPrint("\n.");
            Text.wait(2000);

            Text.slowPrint("\n.");
            Text.wait(2000);

            Text.slowPrint("\nImpressive, you managed to defeat Draucabra.");
            Text.wait(4000);

            Text.slowPrint("\nFor all of our sakes', thank you, brave warrior.");
            Text.wait(4000);

            System.out.println("-----------------------------\n      THE END. You won!      \n-----------------------------");
            System.exit(0);
        } else {
            Text.slowPrint("You fought well, I'll give you that.");
            Text.wait(2000);

            Text.slowPrint("\nFew challengers have been as skilled as you.");
            Text.wait(2000);

            Text.slowPrint("\nUnfortunately, you are yet to surpass a god like me.");
            Text.wait(5000);

            System.out.println("-----------------------------\n      THE END. You lost.      \n-----------------------------");

            System.exit(0);
        }
    }

    private static String cutscene(int pHP, int pDMG, int pBLK, int pG, int winCount) {
        Scanner scan = new Scanner(System.in);

        String[] enemies = {"Goblin", "Skeleton", "Bandit", "Thief", "Gremlin", "Zombie", "Spider"};
        String[] adjectives = {"Mad", "Bloodthirsty", "Starving", "Rampaging", "Sneaky", "Short", "Tall", "Fat", "Hungry"};

        int eHP = (int) (Math.random() * 5) + (winCount * 4) + 10; // [10-14] default
        int eDMG = (int) (Math.random() * 3) + (winCount * 2) + 5; // [5-7] default

        String enemy = enemies[(int) (Math.random() * 7)];
        int r = (int) (Math.random() * 10);
        if (r == 9) {
            enemy = "Golden " + enemy; // rip if this is your first fight
            eHP += 12;
            eDMG += 5;
        } else {
            enemy = adjectives[r] + " " + enemy;
        }

        Text.cls();
        System.out.println("A " + enemy + " approaches you! What will you do?\n");

        while (pHP != 0 && eHP != 0) {
            // display stats
            System.out.println("Player Stats:\t\tEnemy Stats:\n" +
                    "HP: " + pHP + "\t\t\tHP: " + eHP + "\n" +
                    "DMG: " + pDMG + "\t\t\tDMG: " + eDMG + "\n" +
                    "BLK: " + pBLK);

            // player's options
            System.out.println("\nA - Attack\tB - Block\nQ - Quit the game (NOT just this fight, the ENTIRE game)\n");

            // player turn input
            String input = new String();
            do {
                input = scan.nextLine();
            }
            while (input.equals(""));

            Text.cls();
            boolean blocking = false;
            // player turn
            if (input.equalsIgnoreCase("Q")) {
                System.exit(0);
            } else if (input.equalsIgnoreCase("A")) // attack the enemy
            {
                eHP -= pDMG;
                if (eHP < 0) {
                    eHP = 0;
                }
                System.out.println("You did " + pDMG + " damage! The enemy is now " + eHP + " HP.");
            } else if (input.equalsIgnoreCase("B")) // block enemy's attack
            {
                System.out.println("Blocking for " + pBLK + " damage.");
                blocking = true;
            }
            System.out.println();

            // enemy turn
            int action = (int) (Math.random() * 2);
            if (action == 1 && eHP > 0) // attack the player
            {
                int d = eDMG;
                if (blocking) {
                    d -= pBLK;
                }
                if (d < 0) {
                    d = 0;
                }
                pHP -= d;
                if (pHP < 0) {
                    pHP = 0;
                }

                System.out.println("The " + enemy + " attacked you and you lost " + d + " HP!");
            } else if (action == 0 && eHP > 0) // does nothing
            {
                System.out.println("The " + enemy + " glares at you menacingly.");
            }
            System.out.println();
        }

        if (eHP == 0) {
            System.out.println("You win! You can choose to increase any one of your stats:\n");
            System.out.print("Type A for +3 attack\nType B for +6 block\nType H for +10 HP\nWhich stat would you like?");

            String in = new String();
            do {
                in = scan.next();
            }
            while (!(in.equalsIgnoreCase("A") || in.equalsIgnoreCase("B") || in.equalsIgnoreCase("H")));

            return in;
        } else {
            System.out.println("You died. It seems you still lack strength.");

            System.out.println("-----------------------------\n      THE END. You lost.      \n-----------------------------");
            System.exit(0);
        }

        return "ERROR";
    }

    /**
     * long text dialogue as an introduction to the game
     */
    private static String intro() {
        Scanner scan = new Scanner(System.in);

        Text.cls();
        Text.slowPrint("Hello,");
        Text.wait(500);
        Text.slowPrint(" traveler.");
        Text.wait(1000);
        Text.slowPrint(" By what name should I call you?\n");
        Text.slowPrint("> ");
        String name;
        
        do {
            name = scan.nextLine();
        }
        while (name.equals(""));

        Text.wait(1000);
        Text.slowPrint("\nWell hello there, " + name + ".");
        Text.wait(2000);

        Text.slowPrint("\nThis land has been long abandoned for over 200 years.");
        Text.wait(2000);

        Text.slowPrint("\nAll sorts of monsters and evil beings lurk behind every corner.");
        Text.wait(2000);

        Text.slowPrint("\nThe beast who rules over them all,");
        Text.wait(500);
        Text.slowPrint(" Draucabra,");
        Text.wait(500);
        Text.slowPrint(" is said to possess a seemingly infinite amount of riches.");
        Text.wait(2000);

        Text.slowPrint("\nHowever, no one has ever been able to defeat him.");
        Text.wait(2000);

        Text.slowPrint("\nI wonder,");
        Text.wait(500);
        Text.slowPrint(" will you be strong enough to take on the challenge?");
        Text.wait(2000);

        Text.slowPrint("\nOnly time will tell.");
        Text.wait(2000);

        System.out.print("\n\nPress enter to continue\n> ");
        scan.nextLine();
        Text.cls();

        System.out.println("INSTRUCTIONS:\n--------------------\n" +
                "Move across the map and encounter enemies at question marks\n" +
                "Fight enemies and gain gold and stat buffs upon victory\n" +
                "All enemies have a 50% chance to attack\n" +
                "Use the console to interact with the game as instructed\n\n" +
                "Good luck!");

        System.out.print("\n--------------------\nPress enter to continue\n> ");
        scan.nextLine();

        return name;
    }
}
