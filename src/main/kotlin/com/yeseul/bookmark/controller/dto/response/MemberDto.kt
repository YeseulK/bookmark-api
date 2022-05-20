package com.yeseul.bookmark.controller.dto.response

import java.time.LocalDateTime

data class MemberDto @JvmOverloads constructor(
    val id: Long? = null,
    val username: String? = null,
    val password: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)