package MazeGenerator;

import MazeGenerator.Maze.GenerationControl;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(
            11, 
            11,
            GenerationControl.STEP
        );

        maze.regenerate();

        maze.display();
    }
}
