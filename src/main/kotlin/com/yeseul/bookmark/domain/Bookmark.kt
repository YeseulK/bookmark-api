package com.yeseul.bookmark.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonInclude
import com.yeseul.bookmark.BaseTime
import com.yeseul.bookmark.controller.dto.BookmarkDto
import com.yeseul.bookmark.controller.dto.MemberDto
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bookmark(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @field: URL
    var url: String,

    @field: NotBlank
    @field: Length(max = 200)
    var memo: String,

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonBackReference
    var folder: Folder
): BaseTime() {

}

