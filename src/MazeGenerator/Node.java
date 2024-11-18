package MazeGenerator;

/**
 * Represents a single node in the grid. This can either be a wall or a cell.
 */
public class Node {
    public Type type;

    public final Coordinates coords;

    public enum Type {
        CELL,
        WALL
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public record Coordinates(int x, int y) {}

    /**
     * Represents a single node in the grid. This can either be a wall or a cell.
     *
     * @param type The node type, this is wall or cell.
     * @param coords The coordinates of the node in the grid.
     */
    public Node(Type type, Coordinates coords) {
        this.type = type;

        this.coords = coords;
    }

//    public Coordinates adjacent(Direction direction) {
//        switch (direction) {
//            case UP: return new Coordinates();
//            case DOWN:
//            case LEFT:
//            case RIGHT:
//        }
//    }
}
