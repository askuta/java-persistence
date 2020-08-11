package com.epam.quiz.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    val contact: Contact = Contact("My contact name", "http://my.contact.url.com", "my.email@address.com")

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .apiInfo(buildApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.epam.quiz.api"))
            .paths(PathSelectors.any())
            .build()

    private fun buildApiInfo(): ApiInfo = ApiInfoBuilder()
            .title("Spring Quiz App title")
            .description("Some very important description")
            .version("1.0")
            .termsOfServiceUrl("http://my.terms.of.service.url.com")
            .contact(contact)
            .license("My license")
            .licenseUrl("http://my.license.url.com")
            .build()
}
