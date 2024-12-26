package com.example.sch;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.SpecVersion;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class JsonValidator {

    public static void main(String[] args) {
        String schemaPath = "C:\\Users\\karthik\\eclipse-workspace\\Msd\\src\\main\\resources\\templates\\jsonsch.json";
        String jsonPath = "C:\\Users\\karthik\\eclipse-workspace\\Msd\\src\\main\\resources\\templates\\inpu.json";

        try {
            boolean isValid = validateJsonAgainstSchema(jsonPath, schemaPath);
            if (isValid) {
                System.out.println("Valid JSON!");
            } else {
                System.out.println("Invalid JSON!");
            }
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }

    private static boolean validateJsonAgainstSchema(String jsonFilePath, String schemaFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(new File(jsonFilePath));
        JsonNode schemaNode = mapper.readTree(new File(schemaFilePath));

        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = factory.getSchema(schemaNode);

       
        Set<ValidationMessage> errors = schema.validate(jsonNode);

        
        if (errors.isEmpty()) {
            System.out.println("JSON is valid against the schema!");
        } else {
            System.out.println("Validation Errors:");
            errors.forEach(error -> System.out.println(error.getMessage()));
        }

      
        printUnnecessaryFields(jsonNode, schemaNode);

        return errors.isEmpty();
    }

    private static void printUnnecessaryFields(JsonNode jsonNode, JsonNode schemaNode) {
        
        JsonNode propertiesNode = schemaNode.path("properties");

        
        Set<String> requiredFields = new HashSet<>();
        if (schemaNode.has("required")) {
            JsonNode requiredNode = schemaNode.path("required");
            for (JsonNode requiredField : requiredNode) {
                requiredFields.add(requiredField.asText());
            }
        }

       
        List<String> unnecessaryFields = new ArrayList<>();

        
        Iterator<String> fieldNames = jsonNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();

            
            if (!requiredFields.contains(fieldName) && propertiesNode.path(fieldName).isMissingNode()) {
                unnecessaryFields.add(fieldName);
            }
        }

        
        if (!unnecessaryFields.isEmpty()) {
            System.out.println("Unnecessary fields: " + unnecessaryFields);
        }
    }
}