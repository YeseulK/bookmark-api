package com.yeseul.bookmark.domain

import com.yeseul.bookmark.BaseTime
import com.yeseul.bookmark.controller.dto.MemberDto
import javax.persistence.*

@Entity
class Member (
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

//    @OneToMany(mappedBy = "member")
//    val folders: MutableList<Folder> = ArrayList()

    fun updateMember(memberDto: MemberDto) {
        this.username = memberDto.username
        this.password = memberDto.password
    }

    fun updateUsername(username: String) {
        this.username = username
    }

    fun updatePassword(password: String) {
        this.password = password
    }
}