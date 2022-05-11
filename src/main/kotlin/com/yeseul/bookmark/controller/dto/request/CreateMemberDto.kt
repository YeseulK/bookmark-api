package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class RequestMemberDto(
    @field: NotNull
    @field: Length(max = 100)
    val username: String,

    @field: NotNull
    @field: Length(max = 100)
    var password: String
)
