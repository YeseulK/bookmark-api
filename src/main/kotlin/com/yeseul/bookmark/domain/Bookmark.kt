package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bookmark(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var url: String,
    var memo: String,
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "folder_id")
    var folderId: Long
)

