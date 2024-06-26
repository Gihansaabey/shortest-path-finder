//student name: Gihansa Abeygunawadena, studentId IIT :20220459 ,uow:w1953931
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private char[][] map; //represent the map
    private int numRows; // number of rows in the map
    private int numCols; //number of columns in the map
    private Point start; //point object to represent start node
    private Point finish; //point object to represent finish node

    public PathFinder(char[][] map, Point start, Point finish) {
        this.map = map;
        this.numRows = map.length;
        this.numCols = map[0].length;
        this.start = start;
        this.finish = finish;
    }

    //method to find the shortest path from the start point to the finish point
    public List<String> findShortestPath() {
        PriorityQueue<Node> openList = new PriorityQueue<>(); //to store nodes in order according to their total cost
        boolean[][] visitedNodes = new boolean[numRows][numCols]; //to store visited nodes
        List<String> pathSteps = new ArrayList<>(); //path from start to finish

        //adding the start node to the open list
        openList.add(new Node(start, 0, manhattanDistance(start, finish), null, null));

        while (!openList.isEmpty()) {  //loop iterates until the open list is empty
            Node currentNode = openList.poll();

            if (currentNode.position.equals(finish)) {  //checks if the current node is the finish node
                return recreatePath(currentNode, pathSteps);
            }

            visitedNodes[currentNode.position.y][currentNode.position.x] = true; // set the current node as visited

            for (Point neighbor : getNeighbors(currentNode.position)) { //gets the neighbour nodes of the current node
                if (!visitedNodes[neighbor.y][neighbor.x] && map[neighbor.y][neighbor.x] != '0') { //check if that node is not visited and is not a rock
                    int newCost = currentNode.gCost + 1;
                    int heuristic = manhattanDistance(neighbor, finish); //calculate heuristic value
                    String moveDirection = getMoveDirection(currentNode.position, neighbor);
                    Node neighborNode = new Node(neighbor, newCost, heuristic, currentNode, moveDirection);
                    openList.add(neighborNode);  //adding to the open list
                }
            }
        }

        return null;
    }

    //method to calculate the manhattan distance between two points
    private int manhattanDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    //method to return the list of valid neighbours for a node
    private List<Point> getNeighbors(Point p) {
        List<Point> neighborNodes = new ArrayList<>();
        int[] direc_x = {0, 0, -1, 1}; //directions to explore the neighbors
        int[] direc_y = {-1, 1, 0, 0};
        for (int i = 0; i < 4; i++) { //loop to iterate over each direction
            int nx = p.x + direc_x[i];
            int ny = p.y + direc_y[i];
            while (nx >= 0 && nx < numCols && ny >= 0 && ny < numRows && map[ny][nx] != '0') {
                neighborNodes.add(new Point(nx, ny));
                nx += direc_x[i];
                ny += direc_y[i];
            }
        }
        return neighborNodes;
    }

    //method to get the direction of the path
    private String getMoveDirection(Point from, Point to) {
        if (from.x == to.x) {
            return from.y < to.y ? "Move down to (" + (to.x + 1) + "," + (to.y + 1) + ")" : "Move up to (" + (to.x + 1) + "," + (to.y + 1) + ")";
        } else {
            return from.x < to.x ? "Move right to (" + (to.x + 1) + "," + (to.y + 1) + ")" : "Move left to (" + (to.x + 1) + "," + (to.y + 1) + ")";
        }
    }

    //method to recreate the path from the start to the finish point
    private List<String> recreatePath(Node node, List<String> pathSteps) {
        Node currentNode = node;
        while (currentNode != null) {
            if (currentNode.move != null) {
                pathSteps.add(0, currentNode.move);
            }
            currentNode = currentNode.parent;
        }
        return pathSteps;
    }

    //to set information about nodes in the map
    private static class Node implements Comparable<Node> {
        Point position; //position of the node
        int gCost; //cost to reach that node
        int hCost; //estimated cost
        Node parent;
        String move;

        Node(Point position, int gCost, int hCost, Node parent, String move) {
            this.position = position;
            this.gCost = gCost;
            this.hCost = hCost;
            this.parent = parent;
            this.move = move;
        }

        //return the total cost
        public int fCost() { //return the total cost
            return gCost + hCost;
        }

        //compare nodes based on their total cost
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.fCost(), other.fCost());
        }
    }
}

