package com.yeseul.bookmark.repository

import com.yeseul.bookmark.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByUsername(username: String): Member?
}