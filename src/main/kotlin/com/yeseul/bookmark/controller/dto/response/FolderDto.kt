package com.yeseul.bookmark.controller.dto.response

import java.time.LocalDateTime

data class FolderDto @JvmOverloads constructor(
    val id: Long? = null,
    val name: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    var bookmarks: MutableList<BookmarkDto> = arrayListOf()
)