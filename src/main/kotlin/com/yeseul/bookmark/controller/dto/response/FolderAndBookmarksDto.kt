package com.yeseul.bookmark.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class FolderAndBookmarksDto @JvmOverloads constructor(
    val id: Long? = null,
    val name: String? = null,
    var bookmarks: MutableList<BookmarkDto> = arrayListOf(),

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    val createdAt: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    val updatedAt: LocalDateTime? = null
)
