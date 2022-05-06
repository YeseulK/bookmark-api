package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.UserDto
import com.yeseul.bookmark.domain.User
import com.yeseul.bookmark.service.UserService
import org.springframework.web.bind.annotation.*

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

    // TODO: jwt token
    @GetMapping("/login") // ?email=jess@gmail.com&password=1234
    fun getUserByEmailAndPassword(
        @RequestParam email: String,
        @RequestParam password: String
    ): User? {
        return userService.findUserByEmailAndPassword(email, password)
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