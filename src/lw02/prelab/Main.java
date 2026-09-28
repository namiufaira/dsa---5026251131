package lw02.prelab; 

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

       Scanner scanner = new Scanner(
    new File("DSA/dsa - 5026251131/src/lw02/prelab/transactions.txt")
);

       while (scanner.hasNextLine()) {
         String line = scanner.nextLine().trim();
         if (line.isEmpty()) {
        continue;
           }
    
        String[] data = line.split(" ");
        transactions.add(data);

         String name = data[0];
         boolean found = false;

        for (String[] customer : customers) {
            if (customer[0].equals(name)) {
                 found = true;
                break;
                }
            }

            if (!found) {
                customers.add(new String[]{name, "0"});
            }
        }

        scanner.close();
        
        Queue<String[]> queue = new LinkedList<>();
        while (!transactions.isEmpty()) {
            queue.add(transactions.remove());
        }

        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] transaction = queue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);
                    } else {
                        if (amount > balance) {
                            failed.push(transaction);
                        } else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }

        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] transaction = failed.pop();
            System.out.println(
                transaction[0] + " "
                + transaction[1] + " "
                + transaction[2]
            );
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }
        
    }
}