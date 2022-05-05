package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import javax.persistence.*
import java.util.TreeSet

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Folder(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var title: String? = null,
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany
    var bookmarks: MutableList<Bookmark> = arrayListOf()
) {
    fun addBookmark(bookmark: Bookmark) {
        bookmarks.add(bookmark)
    }
}

