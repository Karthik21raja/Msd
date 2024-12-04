package fea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Quv {

    public static void main(String[] args) {
        // Gson instance for JSON formatting
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Student 1 details
        LinkedHashMap<String, Object> student1 = new LinkedHashMap<>();
        student1.put("Name", "Karthik Raja"); // Ensure "Name" appears first
        student1.put("Gender", "Male");

        // Create HashMap for Semester 1 marks for Student 1 (using subject names)
        HashMap<String, Integer> semester1MarksStudent1 = new HashMap<>();
        semester1MarksStudent1.put("Math", 50);
        semester1MarksStudent1.put("Science", 60);
        semester1MarksStudent1.put("English", 70);
        semester1MarksStudent1.put("History", 80);
        semester1MarksStudent1.put("Geography", 90);

        // Create HashMap for Semester 2 marks for Student 1 (using subject names)
        HashMap<String, Integer> semester2MarksStudent1 = new HashMap<>();
        semester2MarksStudent1.put("Math", 67);
        semester2MarksStudent1.put("Science", 78);
        semester2MarksStudent1.put("English", 89);
        semester2MarksStudent1.put("History", 90);
        semester2MarksStudent1.put("Geography", 92);

        // Add the marks HashMaps to the student1 LinkedHashMap
        student1.put("Semester1 Marks", semester1MarksStudent1);
        student1.put("Semester2 Marks", semester2MarksStudent1);

        // Student 2 details
        LinkedHashMap<String, Object> student2 = new LinkedHashMap<>();
        student2.put("Name", "Kumar"); // Ensure "Name" appears first
        student2.put("Gender", "Male");

        // Create HashMap for Semester 1 marks for Student 2 (using subject names)
        HashMap<String, Integer> semester1MarksStudent2 = new HashMap<>();
        semester1MarksStudent2.put("Math", 55);
        semester1MarksStudent2.put("Science", 65);
        semester1MarksStudent2.put("English", 75);
        semester1MarksStudent2.put("History", 85);
        semester1MarksStudent2.put("Geography", 95);

        // Create HashMap for Semester 2 marks for Student 2 (using subject names)
        HashMap<String, Integer> semester2MarksStudent2 = new HashMap<>();
        semester2MarksStudent2.put("Math", 70);
        semester2MarksStudent2.put("Science", 75);
        semester2MarksStudent2.put("English", 80);
        semester2MarksStudent2.put("History", 85);
        semester2MarksStudent2.put("Geography", 75);

        // Add the marks HashMaps to the student2 LinkedHashMap
        student2.put("Semester1 Marks", semester1MarksStudent2);
        student2.put("Semester2 Marks", semester2MarksStudent2);

        // Print students in JSON format
        System.out.println("Student 1 JSON Output:");
        System.out.println(gson.toJson(student1)); // JSON for Student 1
        System.out.println("Student 2 JSON Output:");
        System.out.println(gson.toJson(student2)); // JSON for Student 2
    }
}
