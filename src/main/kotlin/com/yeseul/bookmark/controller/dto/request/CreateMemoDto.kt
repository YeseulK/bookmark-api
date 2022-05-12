package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateMemoDto(
    @field: NotBlank
    @field: Length(max = 200)
    val text: String,

    @field: NotNull
    val bookmarkId: Long
)
