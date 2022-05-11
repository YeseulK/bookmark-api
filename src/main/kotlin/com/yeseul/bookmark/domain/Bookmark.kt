package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) // TODO:
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

