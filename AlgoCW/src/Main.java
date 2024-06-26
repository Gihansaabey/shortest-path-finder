//student name: Gihansa Abeygunawadena, studentId IIT :20220459 ,uow:w1953931
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String mapFilePath = "path/to/the/mapfile.txt"; //path to the map file
            IceMap iceMap = MapParser.parseMap(mapFilePath);

            //creating an instance of PathFinder class
            PathFinder pathFinder = new PathFinder(iceMap.getMap(), iceMap.getStart(), iceMap.getFinish());

            //calls the method to find the shortest path
            //assign the path to a list of Strings
            List<String> shortestPathSteps = pathFinder.findShortestPath();

            //getting the height and width of the map
            int height=iceMap.getMap().length;
            int width= iceMap.getMap()[0].length;
            System.out.println("Height of the map: " + height);
            System.out.println("Width of the map: " + width);

            // getting the coordinates of starting and finishing points
            System.out.println("Start point: (" + (iceMap.getStart().x + 1) + ", " + (iceMap.getStart().y + 1) + ")");
            System.out.println("Finish point: (" + (iceMap.getFinish().x + 1) + ", " + (iceMap.getFinish().y + 1) + ")");


            if (shortestPathSteps != null) { //checks if the shortest path was found
                System.out.println("\nShortest Path Found"); //If the shortest path was found , print this message

                //print the start node
                System.out.println("1. Start at (" + (iceMap.getStart().x + 1) + ", " + (iceMap.getStart().y + 1) + ")");
                int step = 2;
                for (int i = 0; i < shortestPathSteps.size(); i++) { //iterate through the shortest path found
                    System.out.println((step) + ". " + shortestPathSteps.get(i)); //print the steps
                    step++;
                }
                System.out.println(step + ". Done!");

            } else {
                System.out.println("\nNo path found"); //if no shortest path was found
            }
        } catch (IOException e) { //if any error happens while reading the map file
            System.err.println("Error happened while reading the map file: " + e.getMessage());
        }
    }
}
