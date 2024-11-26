package MazeGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {
    private final static String START = "🟩";
    private final static String END = "🟥";

    private final static String PLAYER = "😲";

    private final int width;
    private final int height;

    private int playerX = 0;
    private int playerY = 0;

    private int startX = 0;
    private int startY = 0;

    private int endX = 0;
    private int endY = 0;

    private final Node[][] nodeGrid;
    private final String[][] displayGrid;

    private final GenerationControl control;

    private final Scanner controlScanner = new Scanner(System.in);

    /**
     * Represents a single node in the grid.
     */
    private enum Node {
        CELL("⬜", true),
        WALL("⬛", false);

        /** Whether this node has been visited yet or not by the algorithm. */
        public final boolean visited;

        public final String emoji;

        private Node(String emoji, boolean visited) {
            this.emoji = emoji;
            this.visited = visited;
        }
    }

    /**
     * Directions related to a single Node.
     */
    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /**
     * Lets the user control maze generation.
     */
    public enum GenerationControl {
        /** User doesn't do anything, maze is just generated and displayed. */
        NONE,

        /** Prints each step with a time sleep. */
        PRINT,

        /** Lets the user "step" each generation iteration. Hitting "enter" will step. */
        STEP
    }

    /** Sleep for some time. */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {}
    }

    /** Clears the console. */
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Creates a new Maze object with given parameters.
     *
     * @param width The width of the Maze.
     * @param height The height of the Maze.
     * @param control The user's control over the maze generation.
     */
    public Maze(
        int width, 
        int height,
        GenerationControl control
    ) {
        this.width = width;
        this.height = height;

        this.control = control;

        displayGrid = new String[height][width];
        nodeGrid = new Node[height][width];

        resetNodeGrid();
        resetDisplayGrid();
    }

    private void generateEnd() {
        endX = (int) (Math.random() * width);
        endY = (int) (Math.random() * height);

        Node node = getNode(endX, endY);

        if (endX == startX && endY == startY) {
            generateEnd();
        }

        if (node == Node.WALL) {
            generateEnd();
        }
    }

    private Node getNode(int x, int y) {
        return nodeGrid[y][x];
    }

    private void setNode(int x, int y, Node node) {
        nodeGrid[y][x] = node;
    }

    private boolean inBounds(int x, int y) {
        return 0 <= x && x <= width - 1 && 0 <= y && y <= height - 1;
    }

    // reset the node grid to a grid of cells and walls
    private void resetNodeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                setNode(j, i, Node.WALL);
            }
        }
    }

    // reset the display grid so it matches the node grid
    private void resetDisplayGrid() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                displayGrid[i][j] = getNode(j, i).emoji;
            }
        }
    }

    private void regenerate(int x, int y) {
        // this node is now a visited cell
        setNode(x, y, Node.CELL);

        List<Direction> possibleDirections = new ArrayList<Direction>(Arrays.asList(Direction.values()));

        // check all directions
        while (!possibleDirections.isEmpty()) {
            // choose random direction
            int index = (int) (Math.random() * possibleDirections.size());
            Direction direction = possibleDirections.get(index);

            // remove the direction as a possible choice
            possibleDirections.remove(index);

            // new coordinates
            int adjX = 0;
            int adjY = 0;
    
            int newX = 0;
            int newY = 0;

            switch (direction) {
                case UP:
                    adjY = y - 1;
                    adjX = x;

                    newY = y - 2;
                    newX = x;
                    break;
            
                case DOWN:
                    adjY = y + 1;
                    adjX = x;

                    newY = y + 2;
                    newX = x;
                    break;
                
                case LEFT:
                    adjY = y;
                    adjX = x - 1;

                    newY = y;
                    newX = x - 2;
                    break;

                case RIGHT:
                    adjY = y;
                    adjX = x + 1;

                    newY = y;
                    newX = x + 2;
                    break;

                default:
                    break;
            }

            // if out of bounds, check another direction
            if (!inBounds(newX, newY)) {
                continue;
            }

            // if possible to carve in this direction, then do it
            if (!(getNode(newX, newY).visited)) {
                System.out.println(direction);
                System.out.println(newX + " " + newY);
                System.out.println(getNode(newX, newY));

                setNode(adjX, adjY, Node.CELL);
                
                switch (control) {
                    case NONE:
                        break;
                
                    case PRINT:
                        System.out.println(this);
                        sleep(100);
                        clear();

                        break;

                    case STEP:
                        System.out.println(this);
                        controlScanner.nextLine();
                        clear();

                        break;

                    default:
                        break;
                }

                regenerate(newX, newY);
            }
        }

        // if all the directions have been visited, then the maze has been generated!
        return;
    }

    /**
     * Whether the player won, meaning that the player is at the end.
     */
    public boolean won() {
       return playerX == endX && playerY == endY;
    }

    /**
     * Moves the player's coordinates only if they are in bounds / only if the player can move there (no wall).
     */
    public void movePlayer(int x, int y) {
        int movedX = playerX + x;
        int movedY = playerY + y;

        if (!inBounds(movedX, movedY)) return;
        if (getNode(movedX, movedY) == Node.WALL) return;
        
        playerX = movedX;
        playerY = movedY;
    }

    @Override
    public String toString() {
        String disp = "";

        resetDisplayGrid();

        displayGrid[startY][startX] = START;
        displayGrid[endY][endX] = END;

        displayGrid[playerY][playerX] = PLAYER;

        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                disp += displayGrid[i][j];
            }

            disp += "\n";
        }

        return disp;
    }

    /**
     * Regenerates the maze.
     */
    public void regenerate() {
        startX = (int) (Math.random() * width);
        startY = (int) (Math.random() * height);

        regenerate(startX, startY);

        // reset other emoji locations
        generateEnd();

        playerX = startX;
        playerY = startY;

        resetDisplayGrid();
    }
}
