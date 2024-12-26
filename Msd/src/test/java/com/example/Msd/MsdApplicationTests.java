package com.example.Msd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.client.RestClientException;

import com.example.Msd.controller.Crecontroller;
import com.example.Msd.service.Conne;
import com.example.Msd.service.CreServ;
import com.example.Msd.service.Resol;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.TemplateException;

@SpringBootTest
class MsdApplicationTests {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private Conne databaseService1;

	@Mock
	private Conne databaseService;

	@InjectMocks
	private CreServ jsonTOJsonService;

	@Mock
	private CreServ jsonTOJsonService1;

	@Mock
	private CreServ templateService;

	@Mock
	private Crecontroller controller1;

	@InjectMocks
	private Crecontroller controller;

	@Test
	void testGenerateCorrelationId() {
		String correlationId = CreServ.generateCorrelationId();
		assertNotNull(correlationId);
		assertEquals(36, correlationId.length());
	}

	@Test
	void testFetchTheirReference() throws IOException {
		String jsonData = "{\"TFCPCCRT\":{\"Context\":{\"TheirReference\":\"TR-12345\"}}}";
		JsonNode rootNode = new ObjectMapper().readTree(jsonData);
		String theirReference = jsonTOJsonService.fetchTheirReference(rootNode);
		assertEquals("TR-12345", theirReference);
	}

	@Test
	void testFetchDataMasterID() {
		when(databaseService.fetchMasterRefIdList()).thenReturn(List.of("0132OCPC11000308"));
		String masterRefId = jsonTOJsonService.fetchDataMasterID();
		assertEquals("0132OCPC11000308", masterRefId);

		when(databaseService.fetchMasterRefIdList()).thenReturn(Collections.emptyList());
		String emptyMasterId = jsonTOJsonService.fetchDataMasterID();
		assertNull(emptyMasterId);
	}

	@Test
	void testGenerateCorrelationIdAndAddToData() {
		Map<String, Object> dataModel = mock(Map.class);
		String correlationId = jsonTOJsonService.generateCorrelationIdAndAddToData(dataModel);
		assertNotNull(correlationId);
		verify(dataModel).put("correlationId", correlationId);
	}

	@Test
	void testDataForMasterID() {
		List<String> masterRefId = List.of("MASTER123");
		when(databaseService.fetchMasterRefIdList()).thenReturn(masterRefId);

		String result = controller.dataForMasterID();

		verify(databaseService, times(1)).fetchMasterRefIdList();
		assertEquals("MASTER123", result);
	}

}