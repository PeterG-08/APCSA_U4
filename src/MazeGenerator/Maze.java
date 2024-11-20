package MazeGenerator;

import java.util.Arrays;

public class Maze {
    private final int width;
    private final int height;

    private Node[][] nodeGrid;
    private String[][] displayGrid;

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
        public final Coordinates coords;

        public boolean visited = false;

        public Node(NodeType type, Coordinates coords) {
            this.type = type;
            this.coords = coords;
        }

        /**
         * Returns the coordinates for the cell adjacent to this node. (ensures to jump over walls)
         */
        public Coordinates adjacent(Direction direction) {
            return switch (direction) {
                case UP -> new Coordinates(coords.x, coords.y - 2);

                case DOWN -> new Coordinates(coords.x, coords.y + 2);

                case LEFT -> new Coordinates(coords.x - 2, coords.y);

                case RIGHT -> new Coordinates(coords.x + 2, coords.y);

                default -> null;
            };
        }

        public String display() {
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

    private void initDisplayGrid() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                displayGrid[i][j] = nodeGrid[i][j].display();
            }
        }
    }

    public void displayGrid() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                System.out.print(displayGrid[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Clears the grid to make a grid of walls and cells.
     */
    public void reset() {
//        displayGrid =
        Node[] row = new Node[width];
        Arrays.fill(row, new Node(NodeType.Cell, new Coordinates(1, 2)));
        Arrays.fill(nodeGrid, row);

        initDisplayGrid();
    }
}
