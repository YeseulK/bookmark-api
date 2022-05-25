package com.yeseul.bookmark.domain

import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
class User (
    username: String,
    password: String,
): BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var username: String = username
        protected set

    @Column(nullable = false)
    var password: String = password
        protected set

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val folders: MutableList<Folder> = arrayListOf()

    fun updateUserPassword(password: String) {
        this.password = password
    }
}