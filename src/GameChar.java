public class GameChar {
    private String[] inventory;
    private int locX;
    private int locY;
    private int sight;
    private Map m;

    public GameChar() {
        locX = 2;
        locY = 5;
        sight = 1; // Default sight value. Determines how many tiles the char can see in each directions
        m = null;
        inventory = new String[10];
        inventory[0] = "brass lantern";
        inventory[1] = "rope";
        inventory[2] = "rations";
        inventory[3] = "staff";
    }

    void showInventory() {
        System.out.println("You are carrying:");
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {continue;}
            System.out.println(inventory[i]);
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
        findItem();
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

    public void findItem() {
        String item = m.getItem(locX, locY);

        if (item != "none") {
            String[] tokens = item.split(";");
            System.out.println("You have found ");
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i]);
            }
        }
        else {
            System.out.println("You have found nothing");
        }
        System.out.println();
    }

    public void takeItem(String com) {
        String[] tokens = com.split(" ", 2);
        String item = tokens[1];

        // Make sure that item is there
        tokens = m.getItem(locX, locY).split(";");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(item)) {
                for (int z = 0; z < inventory.length; z++) {
                    if (inventory[z] == null) {
                        inventory[z] = item;
                        m.removeItem(locX, locY, item);
                        System.out.println(item + " has been added to your inventory");
                        return;
                    }
                }
                System.out.println("Your inventory is too full");
                return;
            }
        }
        System.out.println("You are not by an item named " + item);
        return;
    }

    public void dropItem(String com) {
        String[] tokens = com.split(" ", 2);
        String item = tokens[1];

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {continue;}
            if (inventory[i].equals(item)) {
                inventory[i] = null;
                m.addItem(locX, locY, item);
                System.out.println("You have dropped " + item + " at " + locX + ", " + locY );
                return;
            }
        }
        System.out.println("You do not have " + item);
    }
}
