package com.example.Msd.service;

import java.io.StringWriter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CreServ {

	
	private Conne databaseService = null;
	private static final Logger logger = Logger.getLogger(CreServ.class.getName());

	@Autowired
	public CreServ(Conne databaseService) {
		this.databaseService = databaseService;
	}
	
	public static String generateCorrelationId() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}
	
	 public String fetchTheirReference(JsonNode rootNode) {
	        return rootNode.path("TFCPCCRT").path("Context").path("TheirReference").asText();
	    }
	 
	 public String fetchDataMasterID() {
	        List<String> masterRefId = databaseService.fetchMasterRefIdList();
	        if (masterRefId.isEmpty()) {
	            logger.warning("Master reference ID list is empty.");
	            return null;
	        }
	        return masterRefId.get(0);
	    }
	
	
	 public String generateCorrelationIdAndAddToData(Map<String, Object> dataModel) {
	        String correlationId = generateCorrelationId();
	        dataModel.put("correlationId", correlationId);
	        return correlationId;
	    }

	 public static String transformXmlToJson(String xmlResponse, String ftlFilePath, String theirReference, String dataMasterId) throws IOException, TemplateException {
	        XmlMapper xmlMapper = new XmlMapper();
	        Map<String, Object> data = xmlMapper.readValue(xmlResponse, Map.class);

	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonContent = objectMapper.writeValueAsString(data);

	        StringWriter out = new StringWriter();
	        Configuration config = new Configuration(Configuration.VERSION_2_3_32);
	        Template template = new Template("template", new StringReader(ftlFilePath), config);

	        
	        Map<String, Object> dataModel = objectMapper.readValue(jsonContent, Map.class);
	        dataModel.put("theirReference", theirReference);
	        dataModel.put("masterId", dataMasterId);

	        template.process(dataModel, out);

	        return out.toString();
	    }

	 public String processTemplate(String jsonContent, String ftlContent) {
	        StringWriter writer = new StringWriter();
	        try {
	            Configuration config = new Configuration(Configuration.VERSION_2_3_31);
	            Template template = new Template("template", new StringReader(ftlContent), config);
	            ObjectMapper objectMapper = new ObjectMapper();
	            Map<String, Object> dataModel = objectMapper.readValue(jsonContent, Map.class);

	            generateCorrelationIdAndAddToData(dataModel);
	            String masterRefId = fetchDataMasterID();
	            dataModel.put("masterReferenceId", masterRefId);

	            template.process(dataModel, writer);
	        } catch (IOException | TemplateException e) {
	            logger.severe("Error processing template: " + e.getMessage());
	        }
	        return writer.toString();
	    }

	 public static String processMockApi(String url, String requestBody, String ftlFilePath, String theirReference, String dataMasterId) throws Resol, TemplateException {
		    try {
		        RestTemplate restTemplate = new RestTemplate();
		        HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Type", "application/xml");
		        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

		        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
		            String xmlResponse = response.getBody();
		            return transformXmlToJson(xmlResponse, ftlFilePath, theirReference, dataMasterId);
		        } else {
		            throw new Resol("Invalid response from API: " + response.getStatusCode());
		        }
		    } catch (RestClientException e) {
		        throw new Resol("Error occurred while calling the API: " + e.getMessage(), e);
		    } catch (IOException e) {
		        throw new Resol("Error reading or transforming data: " + e.getMessage(), e);
		    }
		}

	public void createRestTemplate() {
	
		
	}

}