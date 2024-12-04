package tes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Tar{
	
	public void first() {
		
		System.out.println("Initial");
		
		HashMap<String, Object> sub1=new HashMap<>();
		sub1.put("Tamil", 78);
		
		HashMap<String, Object> sub2=new HashMap<>();
		sub2.put("English",99);
		
		HashMap<String, Object> sub3=new HashMap<>();
		sub3.put("Maths", 81);

		
		ArrayList<HashMap<String,Object>> mark=new ArrayList<>();
		mark.add(sub1);
		mark.add(sub2);
		mark.add(sub3);
		
		LinkedHashMap<String, Object> stu=new LinkedHashMap<>();
		stu.put("Name", "Raja");
		stu.put("Gender", "Male");
		stu.put("marks", mark);
		
	//	System.out.println(stu);
		
	//	System.out.println(stu.get("marks"));
		List<HashMap<String,Object>> stud=(List<HashMap<String, Object>>) stu.get("marks");
		System.out.println(stud);
		System.out.println("Tamil: "+stud.get(0));

		System.out.println("Tamil: "+stud.get(1).get("English"));
		
	
		
	}
	
	public void second() {
		
		System.out.println("Next");
		
		HashMap<String,Object> sem1=new HashMap<>();
		sem1.put("Physics","77");
		sem1.put("Biology", "77");
		sem1.put("Chemistry", "77");
		HashMap<String,Object> sem2=new HashMap<>();
		sem2.put("Physics", "88");
		sem2.put("Biology", "88");
		sem2.put("Chemistry", "88");
		HashMap<String,Object> sem3=new HashMap<>();
		sem3.put("Physics", "99");
		sem3.put("Biology", "99");
		sem3.put("Chemistry", "99");
		
		HashMap<String,Object> semA=new HashMap<>();
		semA.put("sem1", sem1);
		HashMap<String,Object> semB=new HashMap<>();
		semB.put("sem2", sem2);
		HashMap<String,Object> semC=new HashMap<>();
		semC.put("sem3", sem3);

		ArrayList<HashMap<String,Object>> mark= new ArrayList<>();
		mark.add(semA);
		mark.add(semB);
		mark.add(semC);
		
		HashMap<String,Object> stu=new HashMap<>();
		stu.put("name", "Ranav");
		stu.put("Gender", "Male");
		stu.put("marks", mark);
		
		List<HashMap<String,Object>> stud=(List<HashMap<String, Object>>) stu.get("marks");
		HashMap<String,Object> markOfStu=(HashMap<String, Object>) stud.get(1).get("sem2"); 
		System.out.println("Physics: "+markOfStu.get("Physics")); //7
		
	}
	
	public void third() {
		
		System.out.println("Nexttt");
		
		HashMap<String,Object> sem1=new HashMap<>();
		sem1.put("Physics","11");
		sem1.put("Biology", "11");
		sem1.put("Chemistry", "11");
		HashMap<String,Object> sem2=new HashMap<>();
		sem2.put("Physics", "22");
		sem2.put("Biology", "22");
		sem2.put("Chemistry", "22");
		HashMap<String,Object> sem3=new HashMap<>();
		sem3.put("Physics", "33");
		sem3.put("Biology", "33");
		sem3.put("Chemistry", "33");
		
		HashMap<String,Object> mark=new HashMap<>();
		mark.put("sem1", sem1);
		HashMap<String,Object> semB=new HashMap<>();
		mark.put("sem2", sem2);
		HashMap<String,Object> semC=new HashMap<>();
		mark.put("sem3", sem3);

	
		
		HashMap<String,Object> stu=new HashMap<>();
		stu.put("name", "Rayaan");
		stu.put("Gender", "Male");
		stu.put("marks", mark);
		
		HashMap<String,Object> stud=(HashMap<String, Object>) stu.get("marks");
		HashMap<String,Object> markOfStu=(HashMap<String, Object>) stud.get("sem3"); 
		System.out.println("Physics: "+markOfStu.get("Physics")); //33
	}

	public void fourth() {

		
		System.out.println("Final");
		
		HashMap<String,Object> sem1=new HashMap<>();
		sem1.put("Physics","11");
		sem1.put("Biology", "11");
		sem1.put("Chemistry", "11");
		HashMap<String,Object> sem2=new HashMap<>();
		sem2.put("Physics", "22");
		sem2.put("Biology", "22");
		sem2.put("Chemistry", "22");
		HashMap<String,Object> sem3=new HashMap<>();
		sem3.put("Physics", "33");
		sem3.put("Biology", "33");
		sem3.put("Chemistry", "33");
		
		HashMap<String,Object> mark=new HashMap<>();
		mark.put("sem1", sem1);
		HashMap<String,Object> semB=new HashMap<>();
		mark.put("sem2", sem2);
		HashMap<String,Object> semC=new HashMap<>();
		mark.put("sem3", sem3);

	
		
		HashMap<String,Object> stu=new HashMap<>();
		stu.put("name", "Rayaan");
		stu.put("Gender", "Male");
		stu.put("marks", mark);
		
		HashMap<String,Object> stud=(HashMap<String, Object>) stu.get("marks");
		HashMap<String,Object> markOfStu=(HashMap<String, Object>) stud.get("sem3"); 
		System.out.println("Physics: "+markOfStu.get("Physics")); //33
	}
	
	public static void main(String[] args) {

		Tar solve=new Tar();
		solve.first();
		solve.second();
		solve.third();
		solve.fourth();
	
	}

}