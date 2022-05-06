package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.UserDto
import com.yeseul.bookmark.domain.User
import com.yeseul.bookmark.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getUsers(): List<User> {
        return userService.findUsers()
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User {
        return userService.findUserById(id)
    }

    @PostMapping
    fun postUser(@RequestBody body: UserDto) {
        userService.joinUser(body)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }
}