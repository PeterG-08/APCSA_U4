package MazeGenerator;

import java.util.Arrays;

public class Maze {
    private final int width;
    private final int height;

    private final Node[][] nodeGrid;
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
     * A node in the grid.
     */
    private enum Node {
        Cell("⬜"),
        Wall("⬛");

        public boolean visited = false;

        public final String displayEmoji;

        private Node(String display) {
            this.displayEmoji = display;
        }
    }


    /**
     * Creates a new Maze object with given parameters.
     *
     * @param width The width of the Maze.
     * @param height The height of the Maze.
     */
    public Maze(int width, int height) {
        // ensure that width and height are odd
        this.width = width % 2 == 1 ? width : width + 1;
        this.height = height % 2 == 1 ? height : height + 1;

        displayGrid = new String[height][width];
        nodeGrid = new Node[height][width];
    }

    // get the node at the given coordinates
    private Node getNode(int x, int y) {
        return nodeGrid[y][x];
    }


    // reset the node grid to a grid of cells and walls
    private void resetNodeGrid() {
        Node[] wallRow = new Node[width];
        Arrays.fill(wallRow, Node.Wall);

        Node[] cellRow = new Node[width];

        // fill in cell row
        for (int j = 0; j < width; j++) {
            if (j % 2 == 1) {
                cellRow[j] = Node.Cell;
            } else {
                cellRow[j] = Node.Wall;
            }
        }

        // fill everything with walls initially
        for (int i = 0; i < height; i++) {
            nodeGrid[i] = wallRow;
        }

        // then alternate with cell row
        for (int i = 1; i < height - 1; i+=2) {
            nodeGrid[i] = cellRow;
        }
    }

    // reset the display grid so it matches the node grid
    private void resetDisplayGrid() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                displayGrid[i][j] = nodeGrid[i][j].displayEmoji;
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
    public void regenerate(int startX, int startY) {}

    /**
     * Clears and resets the grid to make a grid of walls and cells.
     */
    public void reset() {
        resetNodeGrid();
        resetDisplayGrid();
    }
}
