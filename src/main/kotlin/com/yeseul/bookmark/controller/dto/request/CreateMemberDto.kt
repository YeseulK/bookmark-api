package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateMemberDto(
    @field: NotBlank
    @field: Email
    val email: String,

    @field: NotBlank
    @field: Length(max = 100)
    var password: String
)
