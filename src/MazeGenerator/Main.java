package MazeGenerator;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(11, 11);

        maze.reset();
        maze.display();
    }
}
