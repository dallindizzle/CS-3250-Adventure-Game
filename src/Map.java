import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Map implements Serializable {
    private String mapString;
    private String itemFile;
    private char[][] mapArray;
    private String[][] itemMapArray;
    private int rows;
    private int cols;
    private int[] tileSize;

    private String plainsImgFile;
    private String mountainImgFile;
    private String forestImgFile;
    private String waterImgFile;
    private String goalImgFile;
    private String outImgFile;
    private String personImgFile;

    public Map() {
        mapString = "";
        rows = 0;
        cols = 0;
        tileSize = new int[2];
        itemFile = "";

        plainsImgFile = "";
        mountainImgFile = "";
        forestImgFile = "";
        waterImgFile = "";
        goalImgFile = "";
        outImgFile = "";
        personImgFile = "";
    }

    public void addMap(Scanner s) {
        // Get the size of the map by getting the first line of the txt file and splitting the rows and the columns
        String size = s.nextLine();
        String[] tokens = size.split(" ");
        rows = Integer.parseInt(tokens[0]);
        cols = Integer.parseInt(tokens[1]);

        // Get the map in a string
/*
        while (s.hasNextLine()) {
            mapString += s.nextLine();
        }
*/

        for (int i = 0; i < rows; i++) {
            mapString +=s.nextLine();
        }

        // Get size of the tiles for the GUI
        String tSize = s.nextLine();
        tokens = tSize.split(" ");
        tileSize[0] = Integer.parseInt(tokens[0]);
        tileSize[1] = Integer.parseInt(tokens[1]);

        // Get map items text file
        itemFile = s.nextLine();

        // Initialize the map array with the correct size
        mapArray = new char[rows][cols];

        // Get all of the file paths for the tiles
        while(s.hasNextLine()) {
            tokens = s.nextLine().split(";");
            switch (tokens[0]) {
                case ".":
                    plainsImgFile = tokens[2];
                    break;
                case "M":
                    mountainImgFile = tokens[2];
                    break;
                case "f":
                    forestImgFile = tokens[2];
                    break;
                case "~":
                    waterImgFile = tokens[2];
                    break;
                case "*":
                    goalImgFile = tokens[2];
                    break;
                case "-":
                    outImgFile = tokens[2];
                    break;
                case "1":
                    personImgFile = tokens[2];
                    break;
                default:
                    System.out.println("Invalid character in item file");
                    break;
            }
        }

        // Create a new Scanner to parse the item file
        try {
            s = new Scanner(new File(itemFile));
        } catch (FileNotFoundException x) {
            System.out.println("Item File open failed.");
            x.printStackTrace();
            System.exit(0);
        }

        itemMapArray = new String[rows][cols];
        for (int i = 0; i < itemMapArray.length; i++) {
            for (int z = 0; z < itemMapArray[i].length; z++) {
                itemMapArray[i][z] = "none";
            }
        }

        while(s.hasNextLine()) {
            tokens = s.nextLine().split(";");
            if (itemMapArray[Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])] == "none") {
                itemMapArray[Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])] = "";
            }
            itemMapArray[Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])] += tokens[2] + ";";
        }

        // Convert the map string into the map array
        int index = 0;
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                mapArray[i][j] = mapString.toCharArray()[index];
                index++;
            }
        }
    }

    public char[][] getMap() {
        return mapArray;
    }

    public int[] getSize() {
        int[] size = {rows, cols};
        return size;
    }

    public void printMap() {
        // Print the map
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                System.out.print(mapArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String getItem(int x, int y) {
        return itemMapArray[x][y];
    }

    public void removeItem(int x, int y, String item) {
        String[] tokens = itemMapArray[x][y].split(";");
        itemMapArray[x][y] = "";

        if (tokens.length > 1) {
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].equals(item)) {
                    continue;
                }
                itemMapArray[x][y] +=tokens[i] + ";";
            }
        }
        else {
            itemMapArray[x][y] = "none";
        }
    }

    public void addItem(int x, int y, String item) {
        if (itemMapArray[x][y].equals("none")) {itemMapArray[x][y] = item;}
        else {
            itemMapArray[x][y] += item + ";";
        }
    }
}
