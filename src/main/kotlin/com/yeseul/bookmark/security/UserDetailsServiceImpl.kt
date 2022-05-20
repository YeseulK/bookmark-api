package com.yeseul.bookmark.security

import com.yeseul.bookmark.domain.Member
import com.yeseul.bookmark.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val memberRepository: MemberRepository
): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val member: Member = memberRepository.findByEmail(email)

        return UserDetailsImpl(member)
    }
}