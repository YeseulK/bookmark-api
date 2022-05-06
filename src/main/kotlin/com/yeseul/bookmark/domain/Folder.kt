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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    var bookmarks: MutableList<Bookmark> = arrayListOf(),

    @Column(name = "user_id")
    var userId: Long? = null
)
