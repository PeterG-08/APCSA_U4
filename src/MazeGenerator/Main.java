package MazeGenerator;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(
            71, 
            31
        );

        maze.regenerate();

        maze.display();
    }
}
