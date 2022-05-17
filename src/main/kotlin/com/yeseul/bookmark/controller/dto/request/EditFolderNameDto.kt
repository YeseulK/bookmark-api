package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class EditFolderNameDto(
    @field: NotBlank
    @field: Length(max = 100)
    val name: String
)
