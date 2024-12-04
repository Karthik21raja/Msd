package bis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Dis {

    public static void main(String[] args) {
        // Step 1: Create HashMaps for name and gender
        HashMap<String, Object> namegen = new LinkedHashMap<>();
        namegen.put("name", "Karthik");
        namegen.put("gender", "Male");

       
        //Creating Data for Semester 1
       
        ArrayList<HashMap<String,Object>> semester_1=new ArrayList<>();
        
        HashMap<String,Object> semester1=new HashMap<>();
        ArrayList<HashMap<String,Integer>> semester1Subs = new ArrayList<>();
        HashMap<String,Integer> semester1Sub1=new HashMap<>();
        semester1Sub1.put("Tamil", 89);
        semester1Sub1.put("English", 88);
        semester1Sub1.put("Maths", 89);
        semester1Subs.add(semester1Sub1);
        semester1.put("Semester 1", semester1Subs);
       
        semester_1.add(semester1);

        //Creating Data for Semester 2
        HashMap<String,Object> semester2=new HashMap<>();
        ArrayList<HashMap<String,Integer>> semester2Subs = new ArrayList<>();
        HashMap<String,Integer> semesterSub2=new HashMap<>();
        semesterSub2.put("Tamil", 60);
        semesterSub2.put("English", 68);
        semesterSub2.put("Maths", 60);
        semester2Subs.add(semesterSub2);
        semester2.put("Semester 2",  semester2Subs);
      
        semester_1.add(semester2);
        
        //Associating Marks Data with Student
        namegen.put("marks", semester_1);
        
        //Storing Students Information
        ArrayList<HashMap<String,Object>> students = new ArrayList<>();
        students.add(namegen);
      //Storing All Students Data in One Map
        HashMap<String, Object>Students = new HashMap<>();
		Students.put("Students",students);
		
		//System.out.println(b.get(0).get("Semester_1"));
       // System.out.println(Students);
		
		HashMap<String,Object> a=students.get(0);
		List<HashMap<String,Object>> b=(List<HashMap<String,Object>>) a.get("marks");
		HashMap<String,Object> b1= b.get(0);
		List<HashMap<String,Object>> c=(List<HashMap<String,Object>>) b1.get("Semester 1");
		HashMap<String,Object> c1= c.get(0);
		System.out.println(c1.get("Maths"));
         


    }
}