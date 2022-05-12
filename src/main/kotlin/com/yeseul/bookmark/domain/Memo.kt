package com.yeseul.bookmark.domain

import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
data class Memo(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var text: String? = null,

    @OneToOne
    @JoinColumn(name = "bookmark_id")
    var bookmark: Bookmark

): BaseTime() {
}
