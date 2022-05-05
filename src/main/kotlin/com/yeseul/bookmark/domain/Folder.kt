package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Folder(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var title: String? = null,
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany
    var bookmark: List<Bookmark>

)