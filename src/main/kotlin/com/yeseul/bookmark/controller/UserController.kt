package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.ChangeUserPasswordDto
import com.yeseul.bookmark.controller.dto.request.CreateUserDto
import com.yeseul.bookmark.controller.dto.response.UserDto
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService
) {

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    fun signup(@RequestBody body: CreateUserDto): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(body))
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody body: CreateUserDto): ResponseEntity<String> {
        return ResponseEntity.ok().body(userService.login(body))
    }

    @ApiOperation(value = "사용자 리스트 조회")
    @GetMapping
    fun getUsers(): ResponseEntity<ApiResponse<List<UserDto>>> {
        return ResponseEntity.ok(ApiResponse(userService.findUsers()))
    }

    @ApiOperation(value = "사용자 조회")
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<ApiResponse<UserDto>> {
        return ResponseEntity.ok(ApiResponse(userService.findUser(id)))
    }

    @ApiOperation(value = "사용자 비밀번호 변경")
    @PatchMapping("/{id}")
    fun patchUser(
        @PathVariable id: Long,
        @RequestBody body: ChangeUserPasswordDto
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok().body(userService.updateUserPassword(id, body))
    }

    @ApiOperation(value = "사용자 삭제")
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}