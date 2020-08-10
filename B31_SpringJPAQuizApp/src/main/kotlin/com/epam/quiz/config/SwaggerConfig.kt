package com.epam.quiz.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
    val apiInfo: ApiInfo = ApiInfo("My title", "My description", "1.0", "http://my.terms.of.service.url.com", contact, "My license", "http://my.license.url.com", arrayListOf())

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.epam.quiz.api"))
            .paths(PathSelectors.any())
            .build()
}
