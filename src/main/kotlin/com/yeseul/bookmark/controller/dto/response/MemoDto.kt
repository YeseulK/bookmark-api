package com.yeseul.bookmark.controller.dto.response

import java.time.LocalDateTime

data class MemoDto @JvmOverloads constructor(
    val id: Long? = null,
    val text: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)