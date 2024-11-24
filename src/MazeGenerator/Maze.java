package MazeGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
    private final int width;
    private final int height;

    private final int startX;
    private final int startY;

    // not final as these might to be regenerated
    private int endX;
    private int endY;

    private final Node[][] nodeGrid;
    private final String[][] displayGrid;

    /**
     * Represents a single node in the grid.
     */
    private enum Node {
        CELL("⬜", true),
        START("🟩", true), // same function as a cell
        END("🟥", false), // same function as a wall
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
     * Creates a new Maze object with given parameters.
     *
     * @param width The width of the Maze.
     * @param height The height of the Maze.
     */
    public Maze(
        int width, 
        int height
    ) {
        this.width = width;
        this.height = height;

        startX = (int) (Math.random() * width);
        startY = (int) (Math.random() * height);

        generateEndCoords();
        
        displayGrid = new String[height][width];
        nodeGrid = new Node[height][width];

        reset();
    }

    private void generateEndCoords() {
        endX = (int) (Math.random() * width);
        endY = (int) (Math.random() * height);

        if (endX == startX && endY == startY) {
            generateEndCoords();
        }

        return;
    }


    private Node getNode(int x, int y) {
        return nodeGrid[y][x];
    }

    public void setNode(int x, int y, Node node) {
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
        while (possibleDirections.size() > 0) {
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
                setNode(adjX, adjY, Node.CELL);
                
                regenerate(newX, newY);
            }
        }

        // if none of the directions worked, then the maze has been generated!
        return;
    }

    /**
     * Displays the grid.
     */
    public void display() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                System.out.print(displayGrid[i][j]);
            }

            System.out.println();
        }
    }

    /**
     * Regenerates the maze.
     */
    public void regenerate() {
        setNode(startX, startY, Node.START);
        setNode(endX, endY, Node.END);

        regenerate(startX, startY);

        // necessary re-coloring
        setNode(startX, startY, Node.START);
        setNode(endX, endY, Node.END);

        resetDisplayGrid();
    }

    /**
     * Clears and resets the grid to make a grid of walls and cells.
     */
    public void reset() {
        resetNodeGrid();
        resetDisplayGrid();
    }
}
