import java.util.ArrayList;

class Student {
    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }
}
import java.util.Scanner;
import java.util.ArrayList;

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        
        System.out.println("--- Student Grade Tracker ---");

        while (true) {
            System.out.print("\nEnter student name (or type 'exit' to finish): ");
            String name = scanner.nextLine();
            
            if (name.equalsIgnoreCase("exit")) break;

            Student student = new Student(name);

            while (true) {
                System.out.print("Enter grade for " + name + " (or -1 to stop adding grades): ");
                double grade = scanner.nextDouble();
                
                if (grade == -1) break;
                student.addGrade(grade);
            }
            scanner.nextLine(); // Clear the buffer
            students.add(student);
        }

        // Displaying Results
        System.out.println("\n--- Final Grade Report ---");
        for (Student s : students) {
            System.out.printf("Student: %-10s | Average: %.2f%n", s.getName(), s.calculateAverage());
        }
        
        scanner.close();
        System.out.println("Tracker closed.");
    }
}
