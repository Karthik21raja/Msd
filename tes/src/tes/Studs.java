package tes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Studs {

	public static void main(String[] args) {
		HashMap<String, Object> Details1 = new LinkedHashMap<>();
		Details1.put("name", "Kishore Kumar");
		Details1.put("gender", "Male");
//
		HashMap<String, Object> Details2 = new LinkedHashMap<>();
		Details2.put("name", "Gokul");
		Details2.put("gender", "Male");

		// sem 1
		ArrayList<HashMap<String, Object>> sem_1 = new ArrayList<>();

		HashMap<String, Object> sem1 = new HashMap<>();
		ArrayList<HashMap<String, Integer>> sem1Subject = new ArrayList<>();
		HashMap<String, Integer> sem1Marks = new LinkedHashMap<>();
		sem1Marks.put("Tamil", 83);
		sem1Marks.put("English", 73);
		sem1Marks.put("Maths", 65);
		sem1Marks.put("Science", 89);
		sem1Marks.put("Social Science", 91);
		sem1Subject.add(sem1Marks);
		sem1.put("Semester1", sem1Subject);
		sem_1.add(sem1);
		//System.out.println(sem1Subject.get(0).get("English"));
		// sem 2
		HashMap<String, Object> sem2 = new HashMap<>();
		ArrayList<HashMap<String, Integer>> sem2Subs = new ArrayList<>();
		HashMap<String, Integer> sem2Marks = new LinkedHashMap<>();
		sem2Marks.put("Tamil", 80);
		sem2Marks.put("English", 78);
		sem2Marks.put("Maths", 70);
		sem2Marks.put("Science", 75);
		sem2Marks.put("Social Science", 95);
		sem2Subs.add(0, sem2Marks);
		sem2.put("Semester2", sem2Subs);
		sem_1.add(sem2);

		ArrayList<HashMap<String, Object>> sem_12 = new ArrayList<>();

		HashMap<String, Object> sem12 = new HashMap<>();
		ArrayList<HashMap<String, Integer>> sem1Subject2 = new ArrayList<>();
		HashMap<String, Integer> sem1Marks2 = new LinkedHashMap<>();
		sem1Marks2.put("Tamil", 85);
		sem1Marks2.put("English", 75);
		sem1Marks2.put("Maths", 60);
		sem1Marks2.put("Science", 80);
		sem1Marks2.put("Social Science", 75);
		sem1Subject2.add(sem1Marks2);
		sem12.put("Semester1", sem1Subject2);
		sem_12.add(sem12);

		// sem 2
		HashMap<String, Object> sem22 = new HashMap<>();
		ArrayList<HashMap<String, Integer>> sem2Subs2 = new ArrayList<>();
		HashMap<String, Integer> sem2Marks2 = new LinkedHashMap<>();
		sem2Marks2.put("Tamil", 69);
		sem2Marks2.put("English", 75);
		sem2Marks2.put("Maths", 76);
		sem2Marks2.put("Science", 70);
		sem2Marks2.put("Social Science", 65);
		sem2Subs2.add(0, sem2Marks2);
		sem22.put("Semester2", sem2Subs2);
		sem_12.add(sem22);

		Details1.put("marks", sem_1);
		Details2.put("marks", sem_12);

		ArrayList<HashMap<String, Object>> students = new ArrayList<>();
		students.add(Details1);
		//students.add(Details2);
		HashMap<String, Object> Students = new LinkedHashMap<>();
		Students.put("Students", students);
		// Assuming students is a List<Map<String, Object>> parsed from JSON
		HashMap<String, Object> semester1Marks = ((List<HashMap<String, Object>>) ((List<HashMap<String, Object>>) 
				students.get(0).get("marks")).get(0).get("Semester1")).get(0);
		System.out.println(semester1Marks.get("English")); // Example to fetch Maths marks

//		System.out.println(students.get(0).get("Name"));

		//System.out.println(Students);

	}
}