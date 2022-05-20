package com.yeseul.bookmark.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class MemoDto @JvmOverloads constructor(
    val id: Long? = null,
    val text: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    val createdAt: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    val updatedAt: LocalDateTime? = null
)