import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Adventure {
    public static void main(String[] args) {
        Scanner in = null;
        try {
            in = new Scanner(new File(args[0]));
        } catch (FileNotFoundException x) {
            System.out.println("File open failed.");
            x.printStackTrace();
            System.exit(0);
        }

        Map m = new Map();
        m.addMap(in);
        //m.printMap();

        GameChar test = new GameChar();
        test.setMap(m);
        in = new Scanner(System.in);
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


