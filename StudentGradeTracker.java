import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        
        // Input student data
        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            String name = sc.next();
            System.out.print("Enter grade (0-100): ");
            int grade = sc.nextInt();
            students.add(new Student(name, grade));
        }
        
        // Calculate stats
        int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (Student s : students) {
            sum += s.grade;
            if (s.grade > max) max = s.grade;
            if (s.grade < min) min = s.grade;
        }
        double avg = sum / (double) n;
        
        // Summary report
        System.out.println("\n--- Student Grade Summary ---");
        for (Student s : students) {
            System.out.println(s.name + ": " + s.grade);
        }
        System.out.println("Average Score: " + avg);
        System.out.println("Highest Score: " + max);
        System.out.println("Lowest Score: " + min);
        
        sc.close();
    }
}