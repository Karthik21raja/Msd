package com.example.Msd.controller;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Msd.service.Conne;
import com.example.Msd.service.CreServ;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.logging.Logger;

@RestController
@RequestMapping("/ftl")
@Component
public class Ftlne {

	private final CreServ jjService;

	private final Conne databaseService;
	
	 public Ftlne( CreServ jjService, Conne databaseService) {
		super();
		this.jjService = jjService;
		this.databaseService = databaseService;
	}


	Logger logger = Logger.getLogger(getClass().getName());

	public List<String> fetchMasterRefId() {
		return databaseService.fetchMasterRefIdList();
	}

	public String dataForMasterID() {
		List<String> masterRefId = fetchMasterRefId();
		String data = masterRefId.get(0);
		logger.info(data);
		return data;
	}
	

	@PostMapping(value = "/xml", consumes = "application/json", produces = "application/json")
	public String generateXml(@RequestBody String jsonContent) {
		try {

			 String ftlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/cre.ftl")));

			String mockUrl = "http://bsit-srv04:8003/tiplus2-deploy2/hello";
			String xmlOutput = jjService.processTemplate(jsonContent, ftlContent);
			logger.info(xmlOutput);

			XmlMapper xmlMapper = new XmlMapper();

			JsonNode jsonNode = xmlMapper.readTree(xmlOutput);

			ObjectMapper objectMapper = new ObjectMapper();

			String jsonData = objectMapper.writeValueAsString(jsonNode);

			JsonNode rootNode = objectMapper.readTree(jsonData);

			String theirReference = rootNode.path("TFCPCCRT").path("Context").path("TheirReference").asText();

			String dataMasterID = dataForMasterID();
			logger.info(theirReference);
			
			  String ftlFilePath = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/crejs.ftl")));
			//return CreServ.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, dataMasterID);
			  return xmlOutput;
			
		} catch (Exception e) {
			logger.severe("Error processing template: " + e.getMessage());
			return "Error processing template: " + e.getMessage();
		}
	}

}