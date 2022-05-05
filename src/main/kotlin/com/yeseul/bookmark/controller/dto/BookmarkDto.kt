package com.yeseul.bookmark.controller.dto

data class BookmarkDto(
    val url: String,
    val title: String,
    val folderId: Long
    ) {
}