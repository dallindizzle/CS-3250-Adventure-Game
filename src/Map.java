import java.util.Scanner;

public class Map {
    private String mapString;
    private char[][] mapArray;
    private int rows;
    private int cols;

    public Map() {
        mapString = "";
        rows = 0;
        cols = 0;
    }

    public void addMap(Scanner s) {
        // Get the size of the map by getting the first line of the txt file and splitting the rows and the columns
        String size = s.nextLine();
        String[] tokens = size.split(" ");
        rows = Integer.parseInt(tokens[0]);
        cols = Integer.parseInt(tokens[1]);

        // Get the map in a string
        while (s.hasNextLine()) {
            mapString += s.nextLine();
        }

        // Initialize the map array with the correct size
        mapArray = new char[rows][cols];

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
}
