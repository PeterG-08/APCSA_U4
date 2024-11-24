package MazeGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Maze {
    private final static int CELL = 1;
    private final static int VISITED = 2;
    private final static int WALL = 3;

    private final static Map<Integer, String> EMOJIS = new HashMap<Integer, String>() {{
        put(CELL, "⬜");
        put(VISITED, "⬜");
        put(WALL, "⬛");
    }};

    private final int width;
    private final int height;

    private final int[][] nodeGrid;
    private final String[][] displayGrid;

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
    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        displayGrid = new String[height][width];
        nodeGrid = new int[height][width];

        reset();
    }


    private int getNode(int x, int y) {
        return nodeGrid[y][x];
    }

    public void setNode(int x, int y, int node) {
        nodeGrid[y][x] = node;
    }

    private boolean inBounds(int x, int y) {
        return 0 <= x && x <= width - 1 && 0 <= y && y <= height - 1;
    }

    // reset the node grid to a grid of cells and walls
    private void resetNodeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // if (j % 2 == 1 && i % 2 == 1) {
                //     setNode(j, i, CELL);
                // } else {
                //     setNode(j, i, WALL);
                // }
                setNode(j, i, WALL);
            }
        }
    }

    // reset the display grid so it matches the node grid
    private void resetDisplayGrid() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                displayGrid[i][j] = EMOJIS.get(nodeGrid[i][j]);
            }
        }
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
    public void regenerate(int x, int y) {
        // this node is now a visited cell
        setNode(x, y, VISITED);

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
            if (!(getNode(newX, newY) == VISITED)) {
                setNode(adjX, adjY, VISITED);
                
                regenerate(newX, newY);
            }
        }

        // if none of the directions worked, then the maze has been generated!
        resetDisplayGrid();
        return;
    }

    /**
     * Clears and resets the grid to make a grid of walls and cells.
     */
    public void reset() {
        resetNodeGrid();
        resetDisplayGrid();
    }
}
