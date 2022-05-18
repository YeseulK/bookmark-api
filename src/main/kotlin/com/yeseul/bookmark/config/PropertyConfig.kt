package com.yeseul.bookmark.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources

@Configuration
@PropertySources(PropertySource(value = ["classpath:/env.properties"]))
class PropertyConfig {

}