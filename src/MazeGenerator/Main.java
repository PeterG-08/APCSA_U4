package MazeGenerator;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(5, 5);

        // maze.display();
        // System.out.println();

        maze.regenerate(0, 0);
        // maze.setNode(0, 0, )

        maze.display();
    }
}
