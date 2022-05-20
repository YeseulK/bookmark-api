package com.yeseul.bookmark.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class BookmarkDto @JvmOverloads constructor(
    val id: Long? = null,
    val url: String? = null,
    val memo: MemoDto? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    val createdAt: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    val updatedAt: LocalDateTime? = null
)