package com.yeseul.bookmark.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class User(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String,

    @OneToMany
    var folder: List<Folder>
)