package com.yeseul.bookmark.controller.dto.response

import java.time.LocalDateTime

data class BookmarkDto @JvmOverloads constructor(
    val id: Long? = null,
    val url: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val memo: MemoDto? = null
)