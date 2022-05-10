package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.yeseul.bookmark.BaseTime
import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Folder(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @field:NotBlank
    @field:Length(max = 100)
    var name: String? = null,

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    @JsonManagedReference
    var bookmarks: MutableList<Bookmark> = arrayListOf()

): BaseTime()
