package com.its;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class ItsUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItsUserManagementApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	
	@Bean
	public Docket getCustomizedDocket() {
	return new Docket(DocumentationType.SWAGGER_2)
	.select()
	.apis(RequestHandlerSelectors.basePackage("com.zensar"))
	.paths(PathSelectors.any())
	.build()
	.apiInfo(getApiInfo());

	}
	private ApiInfo getApiInfo() {
	ApiInfo apiInfo=new ApiInfo("Stock Rest Api Documentation",
	"This page givesRest API Doucmentation ", "2.5",
	"My Terms of services",
	new Contact("Interview Tracking System","http://localhot:8003","its-team-2@zensar.com"),
	"GPL", "http://gpl.org",
	new ArrayList<VendorExtension>());
	return apiInfo;
	}
}
