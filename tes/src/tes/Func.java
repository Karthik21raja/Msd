package tes;

import java.util.ArrayList;
import java.util.HashMap;

public class Func {
    public static void main(String[] args) {
        
        ArrayList<HashMap<String, String>> listOfMaps = new ArrayList<>();

        
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("name", "Alice");
        map1.put("city", "New York");
        listOfMaps.add(map1);

       
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("name", "Bob");
        map2.put("city", "Los Angeles");
        listOfMaps.add(map2);

       
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("name", "Charlie");
        map3.put("city", "Chicago");
        listOfMaps.add(map3);

        System.out.println(listOfMaps.get(0).get("name")+" "+listOfMaps.get(1).get("name"));
        
    }
}
