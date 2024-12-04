package com.example.Msd.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Experiment {

    // Method to process Freemarker template  //json to json by using ftl
    public StringWriter ftlMethods(String xmlContent1, String ftlContent) {
        StringWriter writer = new StringWriter();
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            Template template = new Template("template", new StringReader(ftlContent), cfg);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> dataModel = objectMapper.readValue(xmlContent1, Map.class);

            // Process the template with the data model
            template.process(dataModel, writer);
            System.out.println("Generated Output:\n" + writer);

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return writer;
    }
  //xml to json
    public static void main(String[] args) {
        String xmlFilePath = "C:\\Users\\karthik\\eclipse-workspace\\Msd\\src\\main\\resources\\templates\\file.xml"; 
        String ftlFilePath = "C:\\Users\\karthik\\eclipse-workspace\\Msd\\src\\main\\resources\\templates\\Ffile.ftl"; 
        String xmlContent = null;
        String ftlContent = null;

        try {
            xmlContent= new String(Files.readAllBytes(Paths.get(xmlFilePath)));
            System.out.println("XML Content:\n" + xmlContent);

            ftlContent = new String(Files.readAllBytes(Paths.get(ftlFilePath)));
            System.out.println("FTL Content:\n" + ftlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            XmlMapper xmlMapper = new XmlMapper();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = xmlMapper.readValue(xmlContent, Map.class);
            String xmlContent1 = objectMapper.writeValueAsString(data);
            System.out.println("Converted JSON Content:\n" + xmlContent1);

            Experiment obj = new Experiment();
            StringWriter writer = obj.ftlMethods(xmlContent1, ftlContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}