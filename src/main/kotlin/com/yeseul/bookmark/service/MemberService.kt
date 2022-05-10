package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.MemberDto
import com.yeseul.bookmark.domain.Member
import com.yeseul.bookmark.security.JwtUtils
import com.yeseul.bookmark.repository.MemberRepository
import org.modelmapper.ModelMapper
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
        var memberDto = dto
        memberDto.password = passwordEncoder.encode(dto.password)
        val entity = mapper.map(memberDto, Member::class.java)
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
        // 예외가 발생하지 않았다면 인증에 성공한 것.
        // 토큰 생성
        val token = jwtUtils.createToken(dto.username)

        return token
    }
}