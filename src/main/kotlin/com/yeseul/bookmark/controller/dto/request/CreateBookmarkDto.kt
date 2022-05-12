package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotNull

data class CreateBookmarkDto(
    @field: URL
    val url: String,

    @field: NotNull
    val folderId: Long
) {
}