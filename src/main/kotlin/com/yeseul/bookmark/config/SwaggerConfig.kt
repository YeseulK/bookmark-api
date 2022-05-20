package com.yeseul.bookmark.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@EnableWebMvc
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.OAS_30)
            .ignoredParameterTypes(AuthenticationPrincipal::class.java)
            .useDefaultResponseMessages(false)
            .securityContexts(securityContext())
            .securitySchemes((apiKey()))
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.yeseul.bookmark"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Bookmark API")
            .description("")
            .version("1.0")
            .contact(
                Contact(
                    "Yeseul Kim",
                    "http://api.jess.kr/",
                    "yeseullk@gmail.com"
                )
            )
            .build()
    }

    private fun securityContext(): List<SecurityContext> {
        return listOf(SecurityContext.builder().securityReferences(defaultAuth()).build())
    }

    private fun defaultAuth(): List<SecurityReference?> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("Authorization", authorizationScopes))
    }

    private fun apiKey(): List<ApiKey> {
        return listOf(ApiKey("Authorization", "Bearer", "header"))
    }
}