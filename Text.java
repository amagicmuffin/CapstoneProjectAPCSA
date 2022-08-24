import java.lang.Thread;

class Text {
    public static boolean useANSI = true;  // enable ANSI features like color and fast screen clear
    public static int linesClearedNoANSI = 50;  // lines that .cls() inserts if useANSI is false
    public static final int PRINT_WAIT = 30; // time between slowPrint() characters in milliseconds
    
    // ANSI escape codes that turn letters after it a color
    public static final String[][] ANSI_COLORS = {
            {"red", "\033[38;5;203m"},
            {"orange", "\033[38;5;215m"},
            {"yellow", "\033[38;5;228m"},
            {"green", "\033[38;5;48m"},
            {"cyan", "\u001b[36m"},
            {"blue", "\033[38;5;74m"},
            {"purple", "\033[38;5;5m"},
            {"pink", "\033[38;5;219m"},
            {"white", "\033[38;5;15m"},
            {"gray", "\033[38;5;242m"},
            {"brown", "\033[38;5;58m"},
            {"black", "\u001b[30m"},
        }; 

    public static String currentDefaultColor = "white";

    /** prints out a white string slowly, one char at a time. white is the default color. */
    public static void slowPrint(String str) {
        slowPrint(str, currentDefaultColor);
    }
    
    /** prints out a colored string slowly, one char at a time */
    public static void slowPrint(String str, String color) {
        if (useANSI) System.out.print(ansiColor(color));
        
        try {
            for(int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
                Thread.sleep(PRINT_WAIT);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /** returns the ANSI escape code that turns letters after it a color */
    public static String ansiColor(String color) {
        for(String[] colorDict : ANSI_COLORS) {
            if(colorDict[0].equals(color)) {
                return colorDict[1];
            }
        }

        // this should probably throw an error but i don't feel like dealing with modding every method header
        return "INVALID COLOR";
    }

    /** clear screen */
    public static void cls() {
        if (useANSI) {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
        } else {
            for (int i = 0; i < linesClearedNoANSI; i++) System.out.print("\n");
        }
    }

    /** waits ms milliseconds. 
     * just a wrapper for Thread.sleep() so you don't have to deal with all the prereqs yourself.
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
