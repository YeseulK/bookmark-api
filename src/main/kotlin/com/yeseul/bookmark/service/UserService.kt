package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.UserDto
import com.yeseul.bookmark.domain.User
import com.yeseul.bookmark.repository.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val mapper: ModelMapper
) {

    fun findUsers(): List<User> {
        return userRepository.findAll().toList()
    }

    fun findUserById(id: Long): User {
        return userRepository.findById(id).orElse(null)
    }

    fun findUserByEmailAndPassword(email: String, password: String): User? {
        userRepository.findByEmail(email)?.let {
            if (it.password == password) {
                return it
            }
        }
        return null
    }

    fun joinUser(dto: UserDto): User {
        validateDuplicateUser(dto.email)
        val entity = mapper.map(dto, User::class.java)
        return userRepository.save(entity)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    private fun validateDuplicateUser(email: String) {
        val user = userRepository.findByEmail(email)
        if (user != null) {
            throw Exception()
        }
    }
}