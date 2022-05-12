package com.yeseul.bookmark.domain

import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
data class Bookmark(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var url: String? = null,

    @ManyToOne
    @JoinColumn(name = "folder_id")
    var folder: Folder,

    @OneToOne(mappedBy = "bookmark", fetch = FetchType.LAZY)
    var memo: Memo
): BaseTime() {

}

