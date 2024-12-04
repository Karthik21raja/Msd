package com.example.Msd.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MarkeService {

StringWriter writer = new StringWriter();
	
	
	public String processTemplate(String jsonContent, String ftlContent) {
		try {
			Configuration confi = new Configuration(Configuration.VERSION_2_3_31);
			Template templ = new Template("template", new StringReader(ftlContent), confi);
			
			ObjectMapper objMapper = new ObjectMapper();
			Map<String, Object> dataModel = objMapper.readValue(jsonContent, Map.class);
			
			templ.process(dataModel, writer);//xml now in the writer
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	public static String TemplateService(String url, String requestBody, String ftlFilePath) throws Exception {
		RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/xml");
    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
        String xmlResponse = response.getBody();
        System.out.println(xmlResponse);

        if (xmlResponse.contains("<success>true</success>") && xmlResponse.contains("<name>")) {
            System.out.println("Valid response received, processing XML to JSON...");

//            JSONObject xmlToJson = new JSONObject();
//            xmlToJson.put("mockResponse", xmlResponse);

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
            
            Template template = new Template("template", new StringReader(ftlFilePath), cfg);
            
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("json", xmlResponse.toString());
            System.out.println("---");


            StringWriter out = new StringWriter();
            template.process(dataModel, out);

            return out.toString();
        } else {
            throw new Exception("Response validation failed. Missing success or name field.");
        }
    } else {
        throw new Exception("Failed to fetch the mock URL response.");
    }
    }

}
