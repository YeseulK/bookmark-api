package com.yeseul.bookmark.config

import com.yeseul.bookmark.utils.DtoMapper
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    fun modelMapper(): ModelMapper {
        return DtoMapper()
    }
}