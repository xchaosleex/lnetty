package nettybase.javaio.contolerio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestControllerIO {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.err.println("An error occurred!");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            System.out.println("You entered: " + line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Hello, " + name + "!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
