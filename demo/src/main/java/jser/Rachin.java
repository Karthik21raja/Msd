package jser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

class Mark {
    private List<Integer> marks;

    public Mark(List<Integer> marks) {
        this.marks = marks;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return marks.toString();
    }
}

class Semester {
    private Map<String, Mark> semesterMarks;

    public Semester() {
        this.semesterMarks = new LinkedHashMap<>();
    }

    public void addSemester(String semesterName, Mark marks) {
        semesterMarks.put(semesterName, marks);
    }

    public Map<String, Mark> getSemesterMarks() {
        return semesterMarks;
    }

    @Override
    public String toString() {
        return semesterMarks.toString();
    }

    public double calculateAverageMarks() {
        int totalMarks = 0;
        int totalSubjects = 0;
        for (Mark mark : semesterMarks.values()) {
            for (int score : mark.getMarks()) {
                totalMarks += score;
                totalSubjects++;
            }
        }
        return totalSubjects > 0 ? (double) totalMarks / totalSubjects : 0;
    }
}

class Student {
    private String name;
    private String gender;
    private Semester semesterDetails;

    public Student(String name, String gender) {
        this.name = name;
        this.gender = gender;
        this.semesterDetails = new Semester();
    }

    public void addSemesterMarks(String semesterName, Mark marks) {
        this.semesterDetails.addSemester(semesterName, marks);
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Semester getSemesterDetails() {
        return semesterDetails;
    }

    @Override
    public String toString() {
        return "{ Name: " + name + ", Gender: " + gender + ", Semesters: " + semesterDetails + " }";
    }

    public double getAverageMarks() {
        return semesterDetails.calculateAverageMarks();
    }
}

public class Rachin {
    private static final String JSON_FILE = "students.json";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Student> students = loadDataFromJsonFile();

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a new student");
            System.out.println("2. Display all student details");
            System.out.println("3. View marks semester-wise for a student");
            System.out.println("4. View average marks for each student");
            System.out.println("5. View students with below passing marks");
            System.out.println("6. Delete a student");
            System.out.println("7. Find students with highest marks in a subject");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(s.nextLine());

            switch (choice) {
                case 1:
                    addStudent(s, students);
                    writeDataToJsonFile(students);
                    break;
                case 2:
                    System.out.println("\nAll Student Details:");
                    students.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("\nEnter the student name to view marks semester-wise: ");
                    String marksStudentName = s.nextLine();
                    students.stream()
                            .filter(student -> student.getName().equalsIgnoreCase(marksStudentName))
                            .forEach(student -> {
                                System.out.println("Marks for " + student.getName() + ":");
                                student.getSemesterDetails().getSemesterMarks()
                                        .forEach((sem, marks) -> System.out.println(sem + ": " + marks));
                            });
                    break;
                case 4:
                    viewAverageMarksForEachStudent(students);
                    break;
                case 5:
                    viewStudentsWithBelowPassingMarks(s, students);
                    break;
                case 6:
                    deleteStudent(s, students);
                    break;
                case 7:
                    findStudentsWithHighestMarksInSubject(s, students);
                    break;
                case 8:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner s, List<Student> students) {
        System.out.println("Enter details for the new student:");
        System.out.print("Name: ");
        String name = s.nextLine();
        System.out.print("Gender: ");
        String gender = s.nextLine();

        Student student = new Student(name, gender);

        System.out.print("Enter number of semesters for " + name + ": ");
        int numSemesters = Integer.parseInt(s.nextLine());

        for (int sem = 1; sem <= numSemesters; sem++) {
            while (true) {
                System.out.print("Enter marks for 5 subjects for Semester " + sem + " (space-separated): ");
                String[] marksInput = s.nextLine().split(" ");
                if (marksInput.length != 5) {
                    System.out.println("Error: You must enter exactly 5 marks.");
                    continue;
                }
                try {
                    List<Integer> marks = new ArrayList<>();
                    for (String mark : marksInput) {
                        marks.add(Integer.parseInt(mark));
                    }
                    student.addSemesterMarks("Semester " + sem, new Mark(marks));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Marks must be numeric. Please try again.");
                }
            }
        }
        students.add(student);
    }

    private static List<Student> loadDataFromJsonFile() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(JSON_FILE)) {
            Type studentListType = new TypeToken<List<Student>>() {}.getType();
            return gson.fromJson(reader, studentListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private static void writeDataToJsonFile(List<Student> students) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            gson.toJson(students, writer);
            System.out.println("\nStudent data has been successfully written to '" + JSON_FILE + "'");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // New Method 1: View average marks for each student
    private static void viewAverageMarksForEachStudent(List<Student> students) {
        students.forEach(student -> {
            System.out.println(student.getName() + " - Average Marks: " + student.getAverageMarks());
        });
    }

    // New Method 2: View students with below passing marks
    private static void viewStudentsWithBelowPassingMarks(Scanner s, List<Student> students) {
        System.out.print("\nEnter passing marks threshold: ");
        int passingMarks = Integer.parseInt(s.nextLine());
        students.stream()
                .filter(student -> student.getSemesterDetails().getSemesterMarks().values().stream()
                        .flatMap(mark -> mark.getMarks().stream())
                        .anyMatch(m -> m < passingMarks))
                .forEach(System.out::println);
    }

    // New Method 3: Delete a student by name
    private static void deleteStudent(Scanner s, List<Student> students) {
        System.out.print("\nEnter the name of the student to delete: ");
        String studentName = s.nextLine();
        students.removeIf(student -> student.getName().equalsIgnoreCase(studentName));
        System.out.println("Student has been deleted.");
    }

    // New Method 4: Find students with highest marks in a subject
    private static void findStudentsWithHighestMarksInSubject(Scanner s, List<Student> students) {
        System.out.print("\nEnter the semester number to check highest marks (e.g., 1 for Semester 1): ");
        int semester = Integer.parseInt(s.nextLine());
        System.out.print("Enter subject index (1 to 5): ");
        int subjectIndex = Integer.parseInt(s.nextLine()) - 1;

        students.stream()
                .filter(student -> student.getSemesterDetails().getSemesterMarks().containsKey("Semester " + semester))
                .map(student -> new AbstractMap.SimpleEntry<>(student, student.getSemesterDetails()
                        .getSemesterMarks().get("Semester " + semester).getMarks().get(subjectIndex)))
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .ifPresent(entry -> System.out.println("Student with highest marks in Subject " + subjectIndex + ": " + entry.getKey()));
    }
}



//arraylist how value add hashmap la how value add and arraylist inside add hashmap 
