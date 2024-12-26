package com.example.Msd.controller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Msd.entity.Dataforjson;
import com.example.Msd.entity.Detailform;
import com.example.Msd.entity.Listform;
import com.example.Msd.service.Conne;
import com.example.Msd.service.CreServ;
import com.example.Msd.service.Resol;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/ftlCheck")
@Component
public class Crecontroller {

	private CreServ jjService = null;

	private Conne databaseService = null;

	public Crecontroller( CreServ jjService, Conne databaseService) {
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

	@PostMapping(value = "/JCtoJC", consumes = "application/json", produces = "application/json")
	public String generateJsontoJsonCre(@RequestBody String jsonContent) throws IOException, TemplateException, Resol  {

		String ftlContent = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/cre.ftl")));

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

		String ftlFilePath = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/crejs.ftl")));
		

	return CreServ.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, dataMasterID);

	}
	
	
	
	@PostMapping(value = "/JLtoJL", consumes = "application/json", produces = "application/json")
	public String generateJsontoJsonLis(@RequestBody String jsonContent) throws IOException, TemplateException, Resol  {

		String ftlContent = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/lis.ftl")));

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

		String ftlFilePath = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/lisjs.ftl")));
		

	return CreServ.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, dataMasterID);

	}
	
	
	@PostMapping(value = "/JDtoJD", consumes = "application/json", produces = "application/json")
	public String generateJsontoJsonDet(@RequestBody String jsonContent) throws IOException, TemplateException, Resol  {

		String ftlContent = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/det.ftl")));

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

		String ftlFilePath = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/detjs.ftl")));
		

	return CreServ.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, dataMasterID);

	}
	
	public String generateJson(String jsonContent) throws IOException, TemplateException  {

		String ftlContent = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/datajson.ftl")));
		StringWriter writer = new StringWriter();
	       
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        Template template = new Template("template", new StringReader(ftlContent), config);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataModel = objectMapper.readValue(jsonContent, Map.class);


        template.process(dataModel, writer);
   
    return writer.toString();
}
	
	public String generateJsontoJsonCreatebydata(String jsonContent) throws IOException, TemplateException, Resol  {

		String ftlContent = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/cre.ftl")));

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

		String ftlFilePath = new String(
				Files.readAllBytes(Paths.get("src/main/resources/templates/crejs.ftl")));
		

	return CreServ.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, dataMasterID);

	}
	
	 @PostMapping("/dataforjson")
	    public String createCustomer(@RequestBody Dataforjson customerRequest) throws IOException, TemplateException, Resol {
	      
	        System.out.println("Received data: " + customerRequest);
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(customerRequest);
	        System.out.println(jsonString);
	        String jsondata =generateJson(jsonString);

	        
	        return generateJsontoJsonCreatebydata(jsondata);
	    }

	 public String generateJsonlist(String jsonContent) throws IOException, TemplateException {

		  String ftlContent = new String(
		    Files.readAllBytes(Paths.get("src/main/resources/templates/listform.ftl")));
		  StringWriter writer = new StringWriter();

		  Configuration config = new Configuration(Configuration.VERSION_2_3_31);
		  Template template = new Template("template", new StringReader(ftlContent), config);
		  ObjectMapper objectMapper = new ObjectMapper();
		  Map<String, Object> dataModel = objectMapper.readValue(jsonContent, Map.class);

		  template.process(dataModel, writer);
		  logger.info(writer.toString());

		  return writer.toString();
		 }

		 public String generateJsontoJsonListbydata(String jsonContent) throws IOException, TemplateException, Resol {

		  String ftlContent = new String(
		    Files.readAllBytes(Paths.get("src/main/resources/templates/lis.ftl")));

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

		  String ftlFilePath = new String(
		    Files.readAllBytes(Paths.get("src/main/resources/templates/lisjs.ftl")));

		  return CreServ.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, dataMasterID);

		 }

		 @PostMapping("/listform")
		 public String listform(@RequestBody Listform json) throws IOException, TemplateException, Resol {

		  System.out.println("Received data: " + json);
		  ObjectMapper objectMapper = new ObjectMapper();
		  String jsonString = objectMapper.writeValueAsString(json);
		  System.out.println(jsonString);
		  String jsondata = generateJsonlist(jsonString);

		  return generateJsontoJsonListbydata(jsondata);
		 }

		 public String generateJsondetails(String jsonContent) throws IOException, TemplateException {

		  String ftlContent = new String(
		    Files.readAllBytes(Paths.get("src/main/resources/templates/detailform.ftl")));
		  StringWriter writer = new StringWriter();

		  Configuration config = new Configuration(Configuration.VERSION_2_3_31);
		  Template template = new Template("template", new StringReader(ftlContent), config);
		  ObjectMapper objectMapper = new ObjectMapper();
		  Map<String, Object> dataModel = objectMapper.readValue(jsonContent, Map.class);

		  template.process(dataModel, writer);
		  logger.info(writer.toString());

		  return writer.toString();
		 }

		 public String generateJsontoJsonDetailsbydata(String jsonContent) throws IOException, TemplateException, Resol {

		  String ftlContent = new String(
		    Files.readAllBytes(Paths.get("src/main/resources/templates/det.ftl")));

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

		  String ftlFilePath = new String(
		    Files.readAllBytes(Paths.get("src/main/resources/templates/detjs.ftl")));

		  return CreServ.processMockApi(mockUrl, xmlOutput, ftlFilePath, theirReference, dataMasterID);

		 }

		 @PostMapping("/detailsform")
		 public String detailsform(@RequestBody Detailform json) throws IOException, TemplateException, Resol {

		  System.out.println("Received data: " + json);
		  ObjectMapper objectMapper = new ObjectMapper();
		  String jsonString = objectMapper.writeValueAsString(json);
		  System.out.println(jsonString);
		  String jsondata = generateJsondetails(jsonString);

		  return generateJsontoJsonDetailsbydata(jsondata);
		 }


}