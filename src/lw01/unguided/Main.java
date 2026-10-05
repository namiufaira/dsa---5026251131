package lw01.unguided;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main.class.getResourceAsStream("rentals.txt");

        List<Rental> rentals = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("src/lw01/unguided/rentals.txt"));
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();

                if (type.equalsIgnoreCase("PROJECTOR")) {
                    rentals.add(new ProjectorRental(id, days));
                } else if (type.equalsIgnoreCase("LAPTOP")) {
                    rentals.add(new LaptopRental(id, days));
                }
            } 
            scanner.close();

            for (Rental rental : rentals) {
                System.out.println(rental.summary());
            }
        } catch (Exception e) {
            System.out.println("Error reading rentals.txt: " + e.getMessage());
        }
    } 
}
 
