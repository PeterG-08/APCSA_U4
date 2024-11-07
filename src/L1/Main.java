package L1;

public class Main {
    public static void main(String[] args) {
        int x = 30;
        int y = 10;
        int z = 5;
        System.out.println("x: " + x + ", y: " + y + ", z: " + z);

        while ((x / y >= 0) && (y != 0))
        {
            x += 2;
            y -= 2;
            if (x % z != 0)
            {
                z--;
            }
            System.out.println("x: " + x + ", y: " + y + ", z: " + z);
        }
    }
}
