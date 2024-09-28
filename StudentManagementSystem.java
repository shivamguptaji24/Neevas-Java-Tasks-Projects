import java.util.Scanner;
import java.util.HashMap;


// This class contains the methods to add, update, remove, and retrieve students.
public class StudentManagementSystem {
    private HashMap<String, Student> students = new HashMap<>();
    private Scanner scan = new Scanner(System.in);

    // Method to add a student
    public void addStudent() {
        System.out.println("Enter Student ID: ");
        String id = scan.nextLine();

        System.out.println("Enter Studnet Name: ");
        String name = scan.nextLine();

        System.out.println("Enter Student Age: ");
        int age = scan.nextInt();

        System.out.println("Enter Student Course: ");
        String course = scan.nextLine();

        Student student = new Student(id, name, age, course);
        System.out.println("Student added successfully !!!!!");
    }

    // Method to update a student
    public void updateStudent() {
        System.out.println("Enter Student ID to update: ");
        String id = scan.nextLine();

        if (students.containsKey(id)) {
            System.out.println("Enter new Name: ");
            String name = scan.nextLine();

            System.out.println("Enter new Age: ");
            int age = scan.nextInt();
            scan.nextLine();

            System.out.println("Enter new Course: ");
            String course = scan.nextLine();

            Student student = students.get(id);
            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
            System.out.println("Student updated successfully !!!!!");
        } else {
            System.out.println("Student ID not found !!!!!");
        }
    }

    // Method to remove a student
    public void removeStudent() {
        System.out.println("Enter Student ID to remove: ");
        String id = scan.nextLine();

        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student removed successfully !!!!!");
        } else {
            System.out.println("Student ID not found !!!!!");
        }
    }

    // Method to retrieve a student's details
    public void viewStudent() {
        System.out.println("Enter Student ID to view: ");
        String id = scan.nextLine();

        if (students.containsKey(id)) {
            System.out.println(students.get(id.toString()));
        } else {
            System.out.println("Student ID not found !!!!!");
        }
    }

    // Method to list all students
    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No Students available !!!!!");
        } else {
            for (Student student : students.values()) {
                System.out.println(student.toString());
            }
        }
    }
}


// This class contains the student entity with relevant attributes (ID, name, age, course, etc).
class Student {
    private String studentId;
    private String name;
    private int age;
    private String course;

    public Student(String studentId, String name, int age, String course) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getter and Setter Methods
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}


// Main Class
class Main {
    public static void main(String[] args) {
        StudentManagementSystem management = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n ---------------------------");
            System.out.println("Student Management System");
            System.out.println("\n ---------------------------");

            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Remove Student");
            System.out.println("4. View Student");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Enter you choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    management.addStudent();
                    break;

                case 2:
                    management.updateStudent();
                    break;

                case 3:
                    management.removeStudent();
                    break;

                case 4:
                    management.viewStudent();
                    break;

                case 5:
                    management.listAllStudents();
                    break;

                case 6:
                    exit = true;
                    System.out.println("Exiting Student Management System. Goodbye !!!!!");
                    break;
            
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
        sc.close();
    }
}