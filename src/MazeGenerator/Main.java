package MazeGenerator;

import static MazeGenerator.Maze.clear;

import java.util.Scanner;

import MazeGenerator.Maze.GenerationControl;

public class Main {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);

        final Maze maze = new Maze(
            100,
            40,
            GenerationControl.NONE // control how the maze is generated
        );

        int attempt = 1;

        maze.regenerate();
        maze.display();

        while (true) {
            System.out.print("(ATTEMPT " + attempt + ") " + " Enter wasd: ");
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
                    continue;
            }

            clear();
            maze.display();

            if (maze.won()) break;

            attempt ++;
        }

        System.out.println("YOU WON!!!");

        input.close();
    }
}
