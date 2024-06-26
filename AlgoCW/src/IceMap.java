//student name: Gihansa Abeygunawadena, studentId IIT :20220459 ,uow:w1953931
import java.awt.Point;
import java.util.Set;

public class IceMap {
    private char[][] map; //to save the layout of the map
    private Point start; //start point of the map
    private Point finish;  //finish point of the map
    private Set<Point> rocks; //set of point objects to store rocks

    public IceMap(char[][] map, Point start, Point finish, Set<Point> rocks) {
        this.map = map;
        this.start = start;
        this.finish = finish;
        this.rocks = rocks;
    }

    // Getter methods to return values of instance variables
    public char[][] getMap() {
        return map;
    }
    public Point getStart() {
        return start;
    }

    public Point getFinish() {
        return finish;
    }

    public Set<Point> getRocks() {
        return rocks;
    }
}

