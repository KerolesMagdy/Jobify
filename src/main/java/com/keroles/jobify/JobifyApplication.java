package com.keroles.jobify;

import com.keroles.jobify.Model.Entity.Address;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource(value = "classpath:application.properties"),
		@PropertySource(value = "classpath:/properties/validation.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "classpath:/properties/mail.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "classpath:/properties/resources.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "classpath:/properties/database.properties", ignoreResourceNotFound = true)
})
public class JobifyApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(JobifyApplication.class, args);
	}

}
