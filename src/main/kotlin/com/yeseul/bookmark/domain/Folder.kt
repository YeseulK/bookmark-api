package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Folder(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String? = null,

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    var bookmarks: MutableList<Bookmark> = arrayListOf()

): BaseTime()
