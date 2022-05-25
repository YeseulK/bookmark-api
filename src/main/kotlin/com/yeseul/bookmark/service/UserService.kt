package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.ChangeUserPasswordDto
import com.yeseul.bookmark.controller.dto.request.CreateUserDto
import com.yeseul.bookmark.controller.dto.response.UserDto
import com.yeseul.bookmark.domain.User
import com.yeseul.bookmark.security.JwtUtils
import com.yeseul.bookmark.repository.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    val mapper: ModelMapper,
) {

    @Transactional
    fun signup(dto: CreateUserDto) {
        var user = userRepository.findByUsername(dto.email)
        if (user == null) {
            dto.password = passwordEncoder.encode(dto.password)
            user = User(dto.email, dto.password)
            userRepository.save(user)
        } else {
            throw IllegalArgumentException("이미 존재하는 사용자 입니다.")
        }
    }

    @Transactional(readOnly = true)
    fun login(dto: CreateUserDto): String {
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

    fun findUsers(): List<UserDto> {
        val entities = userRepository.findAll().toList()
        return entities.map { mapper.map(it, UserDto::class.java) }
    }

    fun findUser(id: Long): UserDto {
        val entity = userRepository.findById(id).orElse(null)
        return mapper.map(entity, UserDto::class.java)
    }

    fun updateUserPassword(id: Long, dto: ChangeUserPasswordDto) {
        val user: User = userRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("존재하지 않는 사용자 입니다.")
        dto.password = passwordEncoder.encode(dto.password)
        user.updateUserPassword(dto.password)
        userRepository.save(user)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}