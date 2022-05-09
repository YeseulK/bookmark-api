package com.yeseul.bookmark.controller.dto

data class BookmarkDto(
    val url: String,
    val memo: String,
    val folderId: Long
    ) {
}