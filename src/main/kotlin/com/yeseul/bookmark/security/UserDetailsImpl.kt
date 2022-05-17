package com.yeseul.bookmark.security

import com.yeseul.bookmark.domain.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val member: Member): UserDetails {

    var enabled: Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.createAuthorityList()

    override fun getPassword(): String = member.password

    override fun getUsername(): String = member.username

    override fun isAccountNonExpired(): Boolean = enabled

    override fun isAccountNonLocked(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean = enabled

    override fun isEnabled(): Boolean = enabled

    fun getMemberId(): Long = member.id!!
}