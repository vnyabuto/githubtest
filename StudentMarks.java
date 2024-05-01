import java.util.Scanner;


public class StudentMarks{
    public static void main(String[] args){
        Scanner scanner = new Scanner (System.in);

        //Number of students
        int numStudents = 8;

        //Array to store roll numbers and marks for each student
        int [] rollNumber = new int [numStudents];
        int[][] marks = new int [numStudents][3];


        //input marks for each student
        for (int i = 0; i < numStudents; i++) {
            rollNumber[i] = i + 1;

            System.out.println("enter marks for students" + rollNumber[i] + ":");
            for (int j = 0; j < 3; j++){
                System.out.print("enter marks for subject" + (j + 1) + ":");
                marks[i][j] = scanner.nextInt();
            }
        }


        //Calculate and display average marks for each student
        System.out.println("\n Roll number\tAverage Marks");
        for (int i = 0; i < numStudents; i++){
            int sum = 0;
            for (int j = 0; j < 3; j++){
                sum += marks [i][j];
            }

            double average = sum / 3.0; //Calculate average marks
            System.out.println(rollNumber[i] + "\t\t\t" + average);
        }

        //close the scanner
        scanner.close();
    }
}
