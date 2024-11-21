package MazeGenerator;

import java.util.Arrays;

public class Maze {
    private final int width;
    private final int height;

    private final Node[][] nodeGrid;
    private final String[][] displayGrid;

    private Coordinates currentCoords;

    /**
     * The coordinates of a single Node in the grid.
     *
     * @param x The x coordinate from the upper left corner (starts at 0).
     * @param y The y coordinate from the upper left corner (starts at 0).
     */
    private record Coordinates(int x, int y) {}

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
     * The node type.
     */
    private enum NodeType {
        Cell("⬜"),
        Wall("⬛");

        public final String display;

        private NodeType(String display) {
            this.display = display;
        }
    }

    /**
     * Represents a single node, can be a wall or a visited/unvisited cell.
     */
    private static class Node {
        public final NodeType type;

        public boolean visited = false;

        public Node(NodeType type) {
            this.type = type;
        }

        public String displayEmoji() {
            return type.display;
        }
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
        nodeGrid = new Node[height][width];
    }

    // get the node at the given coordinates
    private Node getNode(Coordinates coords) {
        return nodeGrid[coords.y][coords.x];
    }

    // Returns the coordinates for the cell adjacent to a node. (ensures to jump over walls)
    private Coordinates adjacentNode(Coordinates coords, Direction direction) {
        return switch (direction) {
            case UP -> new Coordinates(coords.x, coords.y - 2);

            case DOWN -> new Coordinates(coords.x, coords.y + 2);

            case LEFT -> new Coordinates(coords.x - 2, coords.y);

            case RIGHT -> new Coordinates(coords.x + 2, coords.y);

            default -> new Coordinates(0, 0);
        };
    }

    // reset the node grid to a grid of cells and walls
    private void resetNodeGrid() {
        // upper and lower rows of just walls
        Node[] closingRow = new Node[width];
        Arrays.fill(closingRow, new Node(NodeType.Wall));

        Node[] regularRow = new Node[width];

        // create a regular row of alternating wall and cell nodes
        for (int i=0; i < width; i++) {
            if (i % 2 == 0) {
                regularRow[i] = new Node(NodeType.Wall);
            } else {
                regularRow[i] = new Node(NodeType.Cell);
            }
        }

        // set upper and lower rows
        nodeGrid[0] = closingRow;
        nodeGrid[height - 1] = closingRow;

        // set every other row
        for (int i=1; i < height-1; i++) {
            nodeGrid[i] = regularRow;
        }
    }

    // reset the display grid so it matches the node grid
    private void resetDisplayGrid() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                displayGrid[i][j] = nodeGrid[i][j].displayEmoji();
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
    public void regenerate() {
        for (Direction direction : Direction.values()) {
            Coordinates adjacentCoords = adjacentNode(currentCoords, direction);
            Node adjacent = getNode(adjacentCoords); // TODO null warning thing

            if (!adjacent.visited) {
                currentCoords = adjacentCoords;
                regenerate();
            }
        }
    }

    /**
     * Clears and resets the grid to make a grid of walls and cells.
     */
    public void reset() {
        resetNodeGrid();
        resetDisplayGrid();
    }
}
