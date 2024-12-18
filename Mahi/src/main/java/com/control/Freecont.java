package com.control;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.serv.Conne;
import com.serv.Freeserv;

@RestController
@RequestMapping("/mlc")
public class Freecont {

	@Autowired
	private Conne DatabaseService;

	public List<String> fetchMasterRefId() {
		return DatabaseService.fetchMasterRefIdList();
	}

	@Autowired
	Freeserv ftlp;

	public String data() {
		List<String> masterRefId = fetchMasterRefId();
		String data = masterRefId.get(0);
		return data;
	}

	@PostMapping(value = "/ipl", consumes = "application/json", produces = "application/json")
	public String generateXml(@RequestBody String jsonContent) {
		try {

			 String ftlContent = new
			 String(Files.readAllBytes(Paths.get("src/templates/cre.ftl")));
			// String ftlContent = new
			// String(Files.readAllBytes(Paths.get("src/main/resources/templates/jsontoxmllist.ftl")));
//			String ftlContent = new String(
//					Files.readAllBytes(Paths.get("src/main/resources/templates/cre.ftl")));
			String xmlOutput = ftlp.processTemplate(jsonContent, ftlContent);
			String mockUrl = "http://bsit-srv04:8003/tiplus2-deploy2/hello";

			XmlMapper xmlMapper = new XmlMapper();

			JsonNode jsonNode = xmlMapper.readTree(xmlOutput);

			ObjectMapper objectMapper = new ObjectMapper();

			String jsonData = objectMapper.writeValueAsString(jsonNode);

			JsonNode rootNode = objectMapper.readTree(jsonData);
			String theirReference = null;

			theirReference = rootNode.path("TFCPCCRT").path("Context").path("TheirReference").asText();

			System.out.println("TheirReference: " + theirReference);

			String data = data();

			 String ftlFilePath = new String(
			 Files.readAllBytes(Paths.get("src/templates/crejs.ftl")));
			// String ftlFilePath = new
			// String(Files.readAllBytes(Paths.get("src/main/resources/templates/xmltojsonlist.ftl")));
//			String ftlFilePath = new String(
//					Files.readAllBytes(Paths.get("crejs.ftl")));

			String result = ftlp.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, data);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error processing template: " + e.getMessage();
		}
	}

}
