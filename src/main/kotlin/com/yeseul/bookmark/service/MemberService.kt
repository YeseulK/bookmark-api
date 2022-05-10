package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.MemberDto
import com.yeseul.bookmark.domain.Member
import com.yeseul.bookmark.security.JwtUtils
import com.yeseul.bookmark.repository.MemberRepository
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    val mapper: ModelMapper,
) {

    @Transactional
    fun signup(dto: MemberDto) {
        dto.password = passwordEncoder.encode(dto.password)
        val entity = mapper.map(dto, Member::class.java)
        memberRepository.save(entity)
    }

    @Transactional(readOnly = true)
    fun login(dto: MemberDto): String {
        try {
            // 인증시도
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(dto.username, dto.password, null)
            )
        } catch (e: BadCredentialsException) {
            throw BadCredentialsException("로그인 실패")
        }
        return jwtUtils.createToken(dto.username)
    }

    fun findMembers(): List<Member> {
        return memberRepository.findAll().toList()
    }
    fun findMember(id: Long): Member {
        return memberRepository.findById(id).orElse(null)
    }

    fun updateMember(id: Long, body: MemberDto) {
        val findMember: Member = memberRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("존재하지 않는 ID 입니다.")
        findMember.updateMember(body)
    }

    fun deleteMember(id: Long) {
        memberRepository.deleteById(id)
    }
}