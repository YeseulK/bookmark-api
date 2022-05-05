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
    var title: String,
    var order: Int? = null,
    var createdAt: LocalDateTime? = null
)