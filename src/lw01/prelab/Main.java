package lw01.prelab;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobList = new ArrayList<>();    

        try {
            Scanner scanner = new Scanner(new File("src/lw01/prelab/jobs.txt"));
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    jobList.add(new MonoPrint(id, pages));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    jobList.add(new ColourPrint(id, pages));
                } 
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File jobs.txt not found: " + e.getMessage());
        }

        for (PrintJob job : jobList) {
            System.out.println(job.summary());
        }
    }
}