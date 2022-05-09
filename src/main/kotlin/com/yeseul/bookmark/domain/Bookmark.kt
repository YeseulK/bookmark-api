package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonBackReference
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

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonBackReference
    var folder: Folder
)


