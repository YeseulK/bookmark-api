package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class EditMemoDto(
    @field: NotBlank
    @field: Length(max = 200)
    val text: String
)
