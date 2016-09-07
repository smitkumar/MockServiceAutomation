package com.tesco.automation.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tesco.automation.exception.ServiceException;
import com.tesco.automation.util.ErrorCode;

@Component
public class BatchService {
	private final static Logger LOGGER = LoggerFactory.getLogger(BatchService.class);
    
	@Autowired
	private RestTemplate restTemplate;
	
    public void createResource(String url,String payLoad)
	{	
   	try{	
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));		
		HttpEntity<String> mockServiceHeaderEntity = new HttpEntity<String>(payLoad, headers);	
		ResponseEntity<String> response = restTemplate.postForEntity(url, mockServiceHeaderEntity, String.class);
		if(HttpStatus.CREATED != response.getStatusCode()){			
			throw new ServiceException(ErrorCode.ERROR_MOCKSERVICE_CREATION_FAILED, "try again");			
		}
   	}catch(Exception e){   		//
		throw new ServiceException(ErrorCode.ERROR_MOCKSERVICE_CREATION_FAILED, " failed");
		
   	}
		return ;		
	}
    
    public ResponseEntity<String> uploadProductService(String url,String payLoad)
	{					
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));		
		HttpEntity<String> mockServiceHeaderEntity = new HttpEntity<String>(payLoad, headers);	
		ResponseEntity<String> response = restTemplate.postForEntity(url, mockServiceHeaderEntity, String.class);
		//System.out.println(response);				
		return response;		
	}
    
    public ResponseEntity<String> uploadOFSService(String url,String payLoad)
	{					
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));		
		HttpEntity<String> mockServiceHeaderEntity = new HttpEntity<String>(payLoad, headers);	
		ResponseEntity<String> response = restTemplate.postForEntity(url, mockServiceHeaderEntity, String.class);
		System.out.println(response);				
		return response;		
	}
    public ResponseEntity<String> uploadProductServiceLocation(String url,String payLoad)
	{					
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));		
		HttpEntity<String> mockServiceHeaderEntity = new HttpEntity<String>(payLoad, headers);	
		ResponseEntity<String> response = restTemplate.postForEntity(url, mockServiceHeaderEntity, String.class);
		System.out.println(response);				
		return response;		
	}
    
    
}
