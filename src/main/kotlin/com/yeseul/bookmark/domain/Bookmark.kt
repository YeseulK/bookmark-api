package com.yeseul.bookmark.domain

import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
data class Bookmark(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var url: String? = null,
//    var memo: String, // TODO: memo entity 만들기 @OneToOne

    @ManyToOne
    @JoinColumn(name = "folder_id")
    var folder: Folder

): BaseTime() {

}

