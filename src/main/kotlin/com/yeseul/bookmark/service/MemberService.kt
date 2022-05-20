package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.ChangeMemberPasswordDto
import com.yeseul.bookmark.controller.dto.request.CreateMemberDto
import com.yeseul.bookmark.controller.dto.response.MemberDto
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
    fun signup(dto: CreateMemberDto) {
        var member = memberRepository.findByUsername(dto.email)
        if (member == null) {
            dto.password = passwordEncoder.encode(dto.password)
            member = Member(dto.email, dto.password)
            memberRepository.save(member)
        } else {
            throw IllegalArgumentException("이미 존재하는 사용자 입니다.")
        }
    }

    @Transactional(readOnly = true)
    fun login(dto: CreateMemberDto): String {
        try {
            // 인증시도
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(dto.email, dto.password, null)
            )
        } catch (e: BadCredentialsException) {
            throw BadCredentialsException("로그인 실패")
        }
        return jwtUtils.createToken(dto.email)
    }

    fun findMembers(): List<MemberDto> {
        val entities = memberRepository.findAll().toList()
        return entities.map { mapper.map(it, MemberDto::class.java) }
    }

    fun findMember(id: Long): MemberDto {
        val entity = memberRepository.findById(id).orElse(null)
        return mapper.map(entity, MemberDto::class.java)
    }

    fun updateMemberPassword(id: Long, dto: ChangeMemberPasswordDto) {
        val member: Member = memberRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("존재하지 않는 사용자 입니다.")
        dto.password = passwordEncoder.encode(dto.password)
        member.updateMemberPassword(dto.password)
        memberRepository.save(member)
    }

    fun deleteMember(id: Long) {
        memberRepository.deleteById(id)
    }
}