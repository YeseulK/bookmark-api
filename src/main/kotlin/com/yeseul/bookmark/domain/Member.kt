package com.yeseul.bookmark.domain

import com.yeseul.bookmark.utils.BaseTime
import javax.persistence.*

@Entity
class Member (
    email: String,
    password: String,
): BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var email: String = email
        protected set

    @Column(nullable = false)
    var password: String = password
        protected set

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    val folders: MutableList<Folder> = arrayListOf()

    fun updateMember(username: String, password: String) {
        this.email = username
        this.password = password
    }
}