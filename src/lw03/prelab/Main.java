package lw03.prelab;

import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        //problem 1 playlist
        System.out.println("===== Problem 1 =====");
        Scanner playlistReader = new Scanner(new File("src/lw03/prelab/playlist.txt"));
        managePlaylist(playlistReader);
        playlistReader.close();

        //problem 2 participants
        System.out.println("===== Problem 2 =====");
        Scanner participantsReader = new Scanner(new File("src/lw03/prelab/participants.txt"));
        manageParticipants(participantsReader);
        participantsReader.close();

        //problem 3 inventory
        System.out.println("===== Problem 3 =====");
        Scanner inventoryReader = new Scanner(new File("src/lw03/prelab/inventory.txt"));
        manageInventory(inventoryReader);
        inventoryReader.close();
    }

    //problem 1 playlist
    public static void managePlaylist(Scanner reader) {

        List<String> playlist = new ArrayList<>();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String operation = line.substring(0,line.indexOf(" "));
            String details = line.substring( line.indexOf(" ") + 1 );

            if (operation.equals("ADD")) {
                String song = details;
                playlist.add(song);
            } else if (operation.equals("INSERT")) {
                int index = Integer.parseInt(details.substring(0,details.indexOf(" ")));
                String song = details.substring(details.indexOf(" ") + 1);
                playlist.add(index, song);
            } else if (operation.equals("REMOVE")) {
                String song = details;
                if (playlist.contains(song)) {
                    playlist.remove(song);
                }
            }
        }

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(
                (i + 1) + ": " + playlist.get(i)
            );
        }
    }
  
    //problem 2 participants
    public static void manageParticipants(Scanner reader) {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        while (reader.hasNextLine()) {
            String name = reader.nextLine();
            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String participant : participants) {
            System.out.println( number + ". " + participant);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }

    //problem 3 inventory
    public static void manageInventory(Scanner reader) {
        Map<String, Integer> inventory =
            new LinkedHashMap<>();
        int failedSales = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(" ");
            String operation = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            if (operation.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    inventory.put(product,inventory.get(product) + quantity);
                } else {
                    inventory.put(product,quantity);
                }
            } else if (operation.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity){
                 inventory.put(product,inventory.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }

        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }

        System.out.println("Failed sales: " + failedSales);
    }
}