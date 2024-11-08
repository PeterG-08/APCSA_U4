package L1;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What number do you want to start at? ");
        int userNumber = Integer.parseInt(scanner.nextLine());

        /* complete me! */
        for (int i = userNumber; i >= 0; i--) {
            System.out.print(i + ", ");
        }

        System.out.print("Time's up! " + userNumber + " seconds have passed!");
    }
}
