package com.tesco.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.tesco.automation.exception.ServiceException;

@Component
public class CustomResourceLoader implements ResourceLoaderAware {
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomResourceLoader.class);
	

	
	// @Bean(name = "fileUploader")
	public List<String> uploadFileOfMockService(String fileName) throws IOException {
		// This line will be changed for all versions of other examples
		StringBuilder fileData = new StringBuilder();

		List<String> mockData = null;
		BufferedReader bufferReader = null;
		try {
	
			ClassPathResource cpr = new ClassPathResource(fileName);
			InputStreamReader ipsr = new InputStreamReader(cpr.getInputStream());
			bufferReader = new BufferedReader(ipsr);
			String line;
			while ((line = bufferReader.readLine()) != null) {
				fileData.append(line);
			}			
			mockData = parseData(fileData.toString());
		} catch (Exception exception) {
			throw new ServiceException(ErrorCode.ERROR_UPLOAD_FAIL,exception.getMessage());
		} finally {
			if(bufferReader != null){
				bufferReader.close();	
			}
			
		}
		
		return mockData;
	}

	private List<String> parseData(String fileData) {
		String[] dataArray = fileData.split("AND");	
		List<String> mockData = Arrays.asList(dataArray);
		return mockData;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		
	}
}