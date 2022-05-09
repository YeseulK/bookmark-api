package com.yeseul.bookmark.domain

import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String? = null,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    var password: String
)