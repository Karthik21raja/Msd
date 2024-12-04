package msd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Dhoni {
public void jsonTOxml() throws StreamReadException, DatabindException, IOException {
		
		try {
		//Loading json file to map
		ObjectMapper ObjMap=new ObjectMapper();
	//	File jsonFile=new File("D:\\Bluescope\\blue_project\\sample_1.json");
		Map<String,Object> data4=ObjMap.readValue(new File("D:/pro/tryjson.json"),Map.class);
		
		//Configuring FreeMarker
		Configuration cfg=new Configuration(Configuration.VERSION_2_3_31);
		cfg.setDirectoryForTemplateLoading(new File("D:\\pro"));
		Template template123 =cfg.getTemplate("fre.ftl");
		
		//Generating xml output
		FileWriter writer=new FileWriter("D:\\pro\\rose.xml");
		template123.process(Map.of("da",data4),writer);
		writer.close();
		
		System.out.println("XML file generated successfully!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		Dhoni method=new Dhoni();
		method.jsonTOxml();
		
	}

}


