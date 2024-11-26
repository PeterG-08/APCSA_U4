package MazeGenerator;

import static MazeGenerator.Maze.clear;

import java.util.Scanner;

import MazeGenerator.Maze.GenerationControl;

public class Main {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);

        final Maze maze = new Maze(
            10,
            10,
            GenerationControl.STEP // control how the maze is generated
        );

        int attempt = 1;

        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.println("Wow! your name has " + name.length() + " letters!");

        maze.regenerate();
        System.out.println(maze);

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
            System.out.println(maze);

            if (maze.won()) break;

            attempt ++;
        }

        System.out.println("YOU WON!!!");

        input.close();
    }
}
