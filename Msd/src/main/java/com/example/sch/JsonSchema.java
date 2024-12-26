package com.example.sch;






import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonSchema {

    public static void main(String[] args) throws IOException {

       
        String schemaFilePath = "C:\\Users\\karthik\\eclipse-workspace\\Msd\\src\\main\\resources\\templates\\jsonsch.json";
        String jsonFilePath = "C:\\Users\\karthik\\eclipse-workspace\\Msd\\src\\main\\resources\\templates\\inpu.json";
        
      
        try (InputStream schemaStream = new FileInputStream(schemaFilePath)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            org.everit.json.schema.Schema schema = SchemaLoader.load(rawSchema);

           
            try (InputStream jsonStream = new FileInputStream(jsonFilePath)) {
                JSONObject jsonData = new JSONObject(new JSONTokener(jsonStream));

               
                try {
                    schema.validate(jsonData);
                    System.out.println("JSON is valid!");
                } catch (Exception e) {
                    System.out.println("Age in years which must be equal to or greater than eighteen in order to vote");
                }

            } catch (FileNotFoundException e) {
                System.out.println("JSON file not found: " + e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Schema file not found: " + e.getMessage());
        }
    }
}

