package com.tesco.automation.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tesco.automation.event.ApplicationStartup;
import com.tesco.automation.exception.ServiceException;
import com.tesco.automation.util.ErrorCode;

@RestController
@RequestMapping(value = "/v1/create")
public class MockServiceController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MockServiceController.class);

	
	@Autowired
	private BatchService batchService;

	@Autowired
	private ApplicationStartup applicationService;
	
	@Value("${wiremock.add.new.service.url}")
	private String createUrl;

	static int ofsCount = 0;
	static int product = 0;
	static int location = 0;
	static int identity = 0;

	@RequestMapping(value = "/allMockService", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> uploadData() throws IOException {

		try {
			LOGGER.info("Creating mock service for OFS,LOCATION and PRDUCT !!!");
			List<String> ofsMockData = applicationService.getOFSMockList();
			List<String> productMockData = applicationService.getProductMockList();
			List<String> LocationMockData = applicationService.getProductLocationMockList();
			List<String> identityServiceMockData = applicationService.getIdentityServiceMockList();
			ofsMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				ofsCount++;
			});

			LOGGER.info("Total number of OFS mockrervice resource created : " + ofsCount);
			productMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				product++;
			});

			LOGGER.info("Total number of product service resource created : " + product);
			LocationMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				location++;
			});
			LOGGER.info("Total number of location service resource created : " + location);
			identityServiceMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				identity++;
			});
			
			LOGGER.info("Total number of identity service resource created : " + identity);
			
		} catch (ServiceException serviceException) {
			LOGGER.info("Unable to create mock service on wiremock :" + serviceException.toString());
			return new ResponseEntity<>(serviceException.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception serviceException) {
			return new ResponseEntity<>(
					new ServiceException(ErrorCode.ERROR_UPLOAD_FAIL, serviceException.getMessage()).toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		StringBuilder message = new StringBuilder();
		message.append("Total number of OFS service created =" + ofsCount)
				.append("  Product service created =" + product)
				.append("  Product location service created =" + location)
				.append("  Identity service created =" + identity);
		resetCounter();
		return new ResponseEntity<>(message.toString(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/productMockService", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> createProductService() throws IOException {

		try {

			LOGGER.info("Creating mock service for PRDUCT !!!");
			List<String> productMockData = applicationService.getProductMockList();
			productMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				product++;
			});
			LOGGER.info("Total number of product service resource created : " + product);

		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.info("Unable to create mock service on wiremock :" + exception);
			return new ResponseEntity<>("Unable to create mock service on wiremock :",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		StringBuilder message = new StringBuilder();
		message.append("Total number of product service resource created :"+product);
		resetCounter();
		return new ResponseEntity<>(message.toString(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/locationMockService", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> createProductLocationService() throws IOException {

		try {

			LOGGER.info("Creating mock service for PRDUCT Location!!!");			
			List<String> LocationMockData = applicationService.getProductLocationMockList();			
			LocationMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				location++;
			});
			LOGGER.info("Total number of product location service resource created : " + location);

		} catch (ServiceException exception) {
			exception.printStackTrace();
			System.out.println(exception);
			LOGGER.info("Unable to create mock service on wiremock :" + exception);
			return new ResponseEntity<>("Unable to create mock service on wiremock :",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		StringBuilder message = new StringBuilder();
		message.append("Total number of product location service resource created :"+location);
		resetCounter();
		return new ResponseEntity<>(message.toString(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/ofsService", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> createOFSService() throws IOException {

		try {
			LOGGER.info("Creating mock service for OFS !!!");			
			List<String> ofsMockData = applicationService.getOFSMockList();			
			ofsMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				ofsCount++;
			});
			LOGGER.info("Total number of OFS service resource created : " + ofsCount);

		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.info("Unable to create mock service on wiremock :" + exception);
			return new ResponseEntity<>("Unable to create mock service on wiremock :",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		StringBuilder message = new StringBuilder();
		message.append("Total number of OFS mock service resource created :"+ofsCount);		
		resetCounter();
		return new ResponseEntity<>(message.toString(), HttpStatus.CREATED);
	}
	@RequestMapping(value = "/identityService", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> createIdentityService() throws IOException {

		try {
			LOGGER.info("Creating mock service for OFS !!!");			
			List<String> identityServiceMockData = applicationService.getIdentityServiceMockList();
			identityServiceMockData.stream().forEach((payLoad) -> {
				batchService.createResource(createUrl, payLoad);
				identity++;
			});
			LOGGER.info("Total number of OFS service resource created : " + identity);

		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.info("Unable to create mock service on wiremock :" + exception);
			return new ResponseEntity<>("Unable to create mock service on wiremock :",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		StringBuilder message = new StringBuilder();
		message.append("Total number of Identity mock service resource created :"+identity);		
		resetCounter();
		return new ResponseEntity<>(message.toString(), HttpStatus.CREATED);
	}
	
	private void resetCounter() {
		ofsCount = 0;
		product = 0;
		location = 0;
		identity = 0;
	}

}
