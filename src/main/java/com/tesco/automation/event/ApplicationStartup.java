package com.tesco.automation.event;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tesco.automation.exception.ServiceException;
import com.tesco.automation.util.CustomResourceLoader;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

	private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class);

	@Autowired
	private CustomResourceLoader customResourceLoader;

	@Value("${mock.identity.service.filename}")
	private String identityServiceFileName;

	@Value("${mock.ofs.service.filename}")
	private String ofsFileName;

	@Value("${mock.product.service.filename}")
	private String productFileName;

	@Value("${mock.product.location.service.filename}")
	private String locationFileName;

	List<String> identiServiceMockData;
	List<String> ofsMockData;
	List<String> productMockData;
	List<String> LocationMockData;	

	/**
	 * This method is called during Spring's startup.
	 * 
	 * @param event
	 *            Event raised when an ApplicationContext gets initialized or
	 *            refreshed.
	 */

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		LOGGER.info("Batch Service Started****************************");
		try {
			ofsMockData = customResourceLoader.uploadFileOfMockService(ofsFileName);
			LOGGER.info("OFS MockData uploaded from file");
			productMockData = customResourceLoader.uploadFileOfMockService(productFileName);
			LOGGER.info("Product Service mock data uploaded from file ");
			LocationMockData = customResourceLoader.uploadFileOfMockService(locationFileName);
			LOGGER.info("Location MockData uploaded from file");
			identiServiceMockData = customResourceLoader.uploadFileOfMockService(identityServiceFileName);
			LOGGER.info("identiServiceMockData MockData uploaded from file");

		}catch(ServiceException serviceException){
			LOGGER.info("File Upload failed ",serviceException);
			return;
		} catch (Exception uploadException) {
			LOGGER.info("File upload failed !!",uploadException);
			return;
		}
		LOGGER.info("All mock service data uploaded from the file successfully");
		return;
	}

	public List<String> getOFSMockList() {
		return ofsMockData;
	}

	public List<String> getProductMockList() {
		return productMockData;
	}

	public List<String> getProductLocationMockList() {
		return LocationMockData;
	}

	public List<String> getIdentityServiceMockList() {
		return identiServiceMockData;
	}

}