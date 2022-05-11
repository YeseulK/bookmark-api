package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateBookmarkDto(
    @field: URL
    val url: String,

    @field: NotBlank
    @field: Length(max = 200)
    val memo: String,

    @field: NotNull
    val folderId: Long
) {
}