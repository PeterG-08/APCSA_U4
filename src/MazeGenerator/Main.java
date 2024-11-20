package MazeGenerator;

public class Main {
    public static void main(String[] args) {
        Maze m = new Maze(10, 10);

        m.reset();

        m.displayGrid();
    }
}
