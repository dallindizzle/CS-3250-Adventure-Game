import java.util.*;

public class Adventure {
    String[] inventory = {"brass lantern", "rope", "rations", "staff"};
    int locX = 0;
    int locY = 0;

    void showInventory() {
        System.out.println("You are carrying:");
        for (String s: inventory) {
            System.out.println(s);
        }
        sayLoc();
    }

    void move (String input) {
        String[] com = input.split(" +");

        if ( com.length == 1) {
            System.out.println("Tell me where to go");
            this.sayLoc();
            return;
        }

        String c = com[1];

        char[] cm = c.toCharArray();

        switch (cm[0]) {
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
                if (locX == 4) {
                    System.out.println("You cannot go that far south");
                    break;
                }
                locX = locX + 1;
                System.out.println("Moving south...");
                break;
            case 'e':
            case 'E':
                if (locY == 4) {
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
        System.out.println("You are at location " + locX + "," + locY);
    }





    public static void main(String[] args) {
        Adventure test = new Adventure();
        Scanner in = new Scanner(System.in);
        String choice = "";

        while (!choice.equals("quit")) {
            choice = in.nextLine();
            char[] ch = choice.toCharArray();
            switch (ch[0]) {
                case 'i':
                case 'I':
                    test.showInventory();
                    break;
                case 'g':
                case 'G':
                    test.move(choice);
                    break;
                case 'q':
                case 'Q':
                    test.sayLoc();
                    System.out.println("Wasn't that fun!");
                    choice = "quit";
                    break;
                default:
                    System.out.println("Invalid command: " + choice);
                    break;
            }
        }
        in.close();
        System.exit(0);
    }
}


