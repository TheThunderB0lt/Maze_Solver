import java.util.*;

import javax.swing.JFrame; // for GUI
import java.awt.Color; // for colors
import java.awt.Graphics; // for graphics -> boundaries, lines

// We'll find the shortest path in the 2D grid by using DFS traversal(Graph)
public class mazeSolverProject extends JFrame {
    // 0 -> The path we traverse
    // 1 -> Obstacle
    // 9 -> is the destination
    private int[][] maze = 
    {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
        {1, 0, 0, 0, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 0, 1, 0, 0, 0, 0, 1},
        {1, 1, 0, 0, 0, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 0, 0, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 1, 0, 0, 1, 1, 0, 9, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    }; // 2D grid for performing maze solver game
    public List<Integer> path = new ArrayList<>(); // To store coordinates of the paths which will lead me to the destination

    public mazeSolverProject() {
        setTitle("MAZE SOLVER"); //Title of the window
        setSize(480, 480); // size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // As soon as I press X(Cloase button) it'll close the window
        depthfirst(maze, 1, 1, path);
        System.out.println(path);
    }

    public static boolean depthfirst(int[][] grid, int x, int y, List<Integer> path) {
        if(grid[y][x] == 9) { // Destination point ->Base case
            path.add(x);
            path.add(y);
            return true; // pass throug this x,y -> end point
        }
        if(grid[y][x] == 0) { // traverse through that path
            grid[y][x] = 2; // marked as visited
            int dx = -1, dy = 0; // upwards
            if(depthfirst(grid, x+dx, y+dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
            dx = 1; dy = 0; // downwards
            if(depthfirst(grid, x+dx, y+dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
            dx = 0; dy = -1; // left side
            if(depthfirst(grid, x+dx, y+dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
            dx = 0; dy = 1; // right side
            if(depthfirst(grid, x+dx, y+dy, path)) {
                path.add(x);
                path.add(y);
                return true;
            }
        }
        return false; // any of these 4 direction is not possibel, return false
    }

    // bcoz we need to basically change the implementation of this paint which is already written inside the base class
    // we're overriding it we're changing few implementations according to our use
    @Override 
    // pre-defined() to paint which takes the graphics g as object, with the help of this 'g' object we'll only be able to
    // perform some operation related to the graphics, wheter it is designing, boundaries, colors.
    public void paint(Graphics g) { 
        g.translate(80, 80); // Space from x-y axis

        // nested loops to traverse 2D[][]
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                Color color; // i want to set the colors with the color object
                switch(maze[i][j]) {
                    case 1: color = Color.BLACK; break;
                    case 9: color = Color.GREEN; break;
                    default: color = Color.WHITE; break;
                }
                g.setColor(color); // set color for that particular grid
                g.fillRect(30*j, 30*i, 30, 30); // we fill rectangle with that color which is g.setColor
                g.setColor(Color.GRAY);
                g.drawRect(30*j, 30*i, 30, 30);
            }
        }
        // creating path from start to dest
        // why i+=2, bcoz we have added both the coordinates(x, y) in our path if incr by 2, i'll get 2x coord, this is how we'll getch x coord
        for(int i = 0; i < path.size(); i += 2) { // the DFS shortest path, stored in the path<>, we iterate tru again on JFrame
            int pathx = path.get(i);
            int pathy = path.get(i + 1);
            System.out.println("X Coordinates " + pathx);
            System.out.println("Y Coordinates " + pathy);

            g.setColor(Color. RED);
            g.fillRect(30*pathx, 30*pathy, 30, 30); // the path from start to end, which is in green color
        }
    }

    public static void main(String[] args) {
        mazeSolverProject view = new mazeSolverProject();
        view.setVisible(true);
    }
}