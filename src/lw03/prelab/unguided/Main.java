package lw03.prelab.unguided;
import java.util.*;

public class Main {
    private static final Map<String, Integer> enrollment = new HashMap<>();
    public static void main(String[] args){
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        List<String> checkResults = new ArrayList<>();
        int rejectedOperations = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ");
            String operation = parts[0];
            String course = parts[1];

            if (operation.equals("REGISTER")){
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    if (enrollment.containsKey(course)) {
                      int currentStock = enrollment.get(course);
                      enrollment.put(course, currentStock + count);
                    } else {
                        enrollment.put(course, count);
                    }
                 }
            } else if (operation.equals("WITHDRAW")){
                int count = Integer.parseInt(parts[2]);
                if (count <= 0){
                    rejectedOperations++;
                    } else if (enrollment.containsKey(course) && enrollment.get(course) >= count) {
                        int currentStock = enrollment.get(course);
                        enrollment.put(course, currentStock - count);
                    } else {
                        rejectedOperations++;
                    }
            } else if (operation.equals("CHECK")){
                if (enrollment.containsKey(course)) {
                    checkResults.add(course + ": " + enrollment.get(course) + " Students");
                } else {
                    checkResults.add(course + ": Not Found");
                }
            }
        }
        scanner.close();

        System.out.println("===== Enrollment Check =====");
        for (int i = 0; i < checkResults.size(); i++) {
            System.out.println(checkResults.get(i));
        }

        System.out.println("===== Final Enrollment =====");
        for (String courseKey : enrollment.keySet()) {
            System.out.println(courseKey + ": " + enrollment.get(courseKey) + " Students");
        }

        System.out.println("Rejected Operations: " + rejectedOperations);
    }
}