package MazeGenerator;

import static MazeGenerator.Maze.clear;
import static MazeGenerator.Maze.sleep;

import java.util.Scanner;

import MazeGenerator.Maze.GenerationControl;

public class Main {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);

        final Maze maze = new Maze(
            70, 
            30,
            GenerationControl.NONE
        );

        maze.regenerate();

        maze.display();

        while (!maze.won()) {            
            String move = input.nextLine();

            switch (move) {
                case "w":
                    maze.movePlayer(0, -1);
                    break;
            
                case "a":
                    maze.movePlayer(-1, 0);
                    break;
                
                case "s":
                    maze.movePlayer(0, 1);
                    break;

                case "d":
                    maze.movePlayer(1, 0);
                    break;

                default:
                    break;
            }

            clear();
            maze.display();

            sleep(1000);
        }

        input.close();
    }
}
