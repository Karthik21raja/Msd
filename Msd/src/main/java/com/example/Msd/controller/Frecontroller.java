package com.example.Msd.controller;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Msd.service.MarkeService;

@RestController
@RequestMapping("/ftl")
public class Frecontroller {

	@Autowired 
	MarkeService ftlp;
	
	@PostMapping(value="/Transfer-xml",consumes = "application/json")     
    public String generateXml(@RequestBody String jsonContent) {
        try {
            String ftlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/fre.ftl")));

            
            
            String mockUrl = "https://newstuff.free.beeceptor.com/viewXML"; 
            String xmlOutput = ftlp.processTemplate(jsonContent, ftlContent);

            
             String ftlFilePath = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/newftl.ftl"))); 
             
                 String result = ftlp.TemplateService(mockUrl, xmlOutput, ftlFilePath);
                 System.out.println("Final Output: " + result);
             return result;
             
         } catch (Exception e) {
             e.printStackTrace();
             return "Error processing template: " + e.getMessage();
             
         }
     }
     
}
