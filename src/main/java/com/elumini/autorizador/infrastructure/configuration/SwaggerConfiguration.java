package com.elumini.autorizador.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
@EnableOpenApi
@EnableSwagger2
class SwaggerConfiguration {

	@Bean
	public Docket api(ApiInfo apiInfo) {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(RequestHandlerSelectors.basePackage("com.elumini.autorizador")) //
				.paths(PathSelectors.any()) //
				.build() //
				.apiInfo(apiInfo);
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Mini autorizador API")
				.description("APIs do Mini autorizador")
				.version("1.0.0")
				.termsOfServiceUrl("https://github.com/guilherme-argentino/mini-autorizador")
				.license("Apache 2.0 License")
				.licenseUrl("https://github.com/guilherme-argentino/mini-autorizador/blob/master/LICENSE.md")
				.build();
	}

}