import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalMarks = 0;
        int individualMarks = 0;

        // Input: Take marks obtained in each subject
        System.out.println("Enter marks obtained in each subject (out of 100):");
        int totalSubjects = 0;



        while(true)
        {
            System.out.print("Enter marks for subject " + (totalSubjects + 1)+ ": ");
            individualMarks = scanner.nextInt();
            totalMarks += individualMarks;
            totalSubjects++;
            System.out.println("Continue to enter the next subject mark ? \n Enter if (Yes - y No - n)");
            String toContinue = scanner.next().toLowerCase();

             if (!toContinue.equals("y")) {
                break;
            }
        } 


        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / totalSubjects;

        // Grade Calculation
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display Results
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks + " out of "+ ( totalSubjects * 100 ));
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
