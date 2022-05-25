package com.yeseul.bookmark.domain

import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
data class Folder(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String? = null,

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    var bookmarks: MutableList<Bookmark> = arrayListOf(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User

): BaseTime()
