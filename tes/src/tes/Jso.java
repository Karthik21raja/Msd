package tes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jso {
    public static void main(String[] args) {

        // Create a HashMap to store student details
        HashMap<String, Object> students = new HashMap<>();

        // Student 1
        HashMap<String, Object> student1 = new HashMap<>();
        student1.put("name", "Kishorekumar");
        student1.put("Gender", "Male");

        // Create a list to hold the semesters for Student1
        List<HashMap<String, Integer>> marks1 = new ArrayList<>();

        // Semester 1 for Student1
        HashMap<String, Integer> semester1Student1 = new HashMap<>();
        semester1Student1.put("mark1", 65);
        semester1Student1.put("mark2", 70);
        semester1Student1.put("mark3", 60);
        semester1Student1.put("mark4", 80);

        // Semester 2 for Student1
        HashMap<String, Integer> semester2Student1 = new HashMap<>();
        semester2Student1.put("mark1", 59);
        semester2Student1.put("mark2", 64);
        semester2Student1.put("mark3", 56);
        semester2Student1.put("mark4", 90);

        // Add the semesters to the marks list for Student1
        marks1.add(semester1Student1);
        marks1.add(semester2Student1);

        // Add the marks list to Student1's details
        student1.put("Mark", marks1);

        // Add Student1 to the students map
        students.put("Student1", student1);

        // Student 2
        HashMap<String, Object> student2 = new HashMap<>();
        student2.put("name", "Gokul");
        student2.put("Gender", "Male");

        // Create a list to hold the semesters for Student2
        List<HashMap<String, Integer>> marks2 = new ArrayList<>();

        // Semester 1 for Student2
        HashMap<String, Integer> semester1Student2 = new HashMap<>();
        semester1Student2.put("mark1", 60);
        semester1Student2.put("mark2", 65);
        semester1Student2.put("mark3", 70);
        semester1Student2.put("mark4", 85);

        // Semester 2 for Student2
        HashMap<String, Integer> semester2Student2 = new HashMap<>();
        semester2Student2.put("mark1", 56);
        semester2Student2.put("mark2", 68);
        semester2Student2.put("mark3", 60);
        semester2Student2.put("mark4", 90);

        // Add the semesters to the marks list for Student2
        marks2.add(semester1Student2);
        marks2.add(semester2Student2);

        // Add the marks list to Student2's details
        student2.put("Mark", marks2);

        // Add Student2 to the students map
        students.put("Student2", student2);

        // Print the students map (this would represent your JSON-like structure)
        System.out.println(students);
    }
}
