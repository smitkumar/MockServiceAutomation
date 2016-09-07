package com.tesco.automation.util;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientFactory {
	
	@Bean(name="restTemplate")
	public RestTemplate restTemplateWithoutProxy() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();			
	   return new RestTemplate(requestFactory);
	}

}
