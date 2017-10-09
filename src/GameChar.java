public class GameChar {
    private String[] inventory = {"brass lantern", "rope", "rations", "staff"};
    private int locX;
    private int locY;
    private int sight;
    private Map m;

    public GameChar() {
        locX = 0;
        locY = 0;
        sight = 1; // Default sight value. Determines how many tiles the char can see in each directions
        m = null;
    }

    void showInventory() {
        System.out.println("You are carrying:");
        for (String s: inventory) {
            System.out.println(s);
        }
        sayLoc();
    }

    void move (String input) {
        //Split input to get the direction
        String[] com = input.split(" +");

        // Check if there is a direction to go
        if ( com.length == 1) {
            System.out.println("Tell me where to go");
            this.sayLoc();
            return;
        }

        // Grab the first char of the direction input
        char dir = com[1].charAt(0);

        // Switch that looks at the first char of the direction input and moves the character if legal
        switch (dir) {
            case 'n':
            case 'N':
                if (locX == 0) {
                    System.out.println("You cannot go that far north");
                    break;
                }
                locX = locX - 1;
                System.out.println("Moving north...");
                break;
            case 's':
            case 'S':
                if (locX == m.getSize()[0] - 1) {
                    System.out.println("You cannot go that far south");
                    break;
                }
                locX = locX + 1;
                System.out.println("Moving south...");
                break;
            case 'e':
            case 'E':
                if (locY == m.getSize()[1] - 1) {
                    System.out.println("You cannot go that far east");
                    break;
                }
                locY = locY + 1;
                System.out.println("Moving east...");
                break;
            case 'w':
            case 'W':
                if (locY == 0) {
                    System.out.println("You cannot go that far west");
                    break;
                }
                locY = locY - 1;
                System.out.println("Moving west...");
                break;
            default:
                System.out.println("You cannot go that way");
                break;
        }
        sayLoc();
    }

    void sayLoc() {
        System.out.println("You are at location " + locX + "," + locY + " in terrain " + m.getMap()[locX][locY]);

        for (int i = locX - sight; i < locX + sight + 1; i++) {
            for (int j = locY - sight; j < locY + sight + 1; j++) {
                if (i < 0 || i >= m.getSize()[0] || j < 0 || j >= m.getSize()[1]) {
                    System.out.print("X ");
                    continue;
                }
                System.out.print(m.getMap()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setMap(Map v) {
        m = v;
    }
}
