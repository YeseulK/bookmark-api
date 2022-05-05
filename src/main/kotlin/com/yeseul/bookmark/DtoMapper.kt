package com.yeseul.bookmark

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies

class DtoMapper: ModelMapper() {
    init {
        configuration.isSkipNullEnabled = true
        configuration.isFieldMatchingEnabled = true
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.matchingStrategy = MatchingStrategies.LOOSE
    }
}