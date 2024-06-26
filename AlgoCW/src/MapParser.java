//student name: Gihansa Abeygunawadena, studentId IIT :20220459 ,uow:w1953931
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MapParser {
    public static IceMap parseMap(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath)); //read the file from the given path
        String line;
        int numRows = 0;
        int numCols = 0;
        while ((line = reader.readLine()) != null) {  //read the map file line by line
            numRows++;               //count number of rows in the map
            numCols = line.length();   //count number of columns in the map
        }
        reader.close();

        char[][] map = new char[numRows][numCols];  //initializing variables to store map data
        Set<Point> rocks = new HashSet<>();  //to store positions of the rocks
        Point start = null;
        Point finish = null;

        reader = new BufferedReader(new FileReader(filePath));  //read the file again and store the details in the variables
        int row = 0;
        while ((line = reader.readLine()) != null) {
            for (int col = 0; col < numCols; col++) {
                char charac = line.charAt(col);
                map[row][col] = charac;   //populating the mapGrid array
                if (charac == 'S') {          //if the character represents the starting point
                    start = new Point(col, row);
                } else if (charac == 'F') {    //if the character represents the finishing point
                    finish = new Point(col, row);
                } else if (charac == '0') {    //if the character represents a rock
                    rocks.add(new Point(col, row));
                }
            }
            row++;
        }
        reader.close();

        return new IceMap(map, start, finish, rocks);  //return ice map object with parsed map data
    }
}
