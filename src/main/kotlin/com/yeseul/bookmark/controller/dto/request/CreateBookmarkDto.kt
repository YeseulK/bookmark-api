package com.yeseul.bookmark.controller.dto.request

import org.hibernate.validator.constraints.URL

data class CreateBookmarkDto(
    @field: URL
    val url: String
) {
}