package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonInclude
import com.yeseul.bookmark.BaseTime
import javax.persistence.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bookmark(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var url: String,
    var memo: String,

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonBackReference
    var folder: Folder
): BaseTime()


