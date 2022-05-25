package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateFolderDto
import com.yeseul.bookmark.controller.dto.request.EditFolderNameDto
import com.yeseul.bookmark.controller.dto.response.FolderAndBookmarksDto
import com.yeseul.bookmark.controller.dto.response.FolderDto
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.security.UserDetailsImpl
import com.yeseul.bookmark.service.FolderService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/folders")
class FolderController(
    private val folderService: FolderService
) {

    @ApiOperation(value = "폴더 리스트 조회")
    @GetMapping
    fun getFolders(
        @AuthenticationPrincipal userDetailsImpl: UserDetailsImpl
    ): ResponseEntity<ApiResponse<List<FolderDto>>> {
        val result = folderService.findFolders(userDetailsImpl.getUserId())
        return ResponseEntity.ok(ApiResponse(result))
    }

    @ApiOperation(value = "폴더 조회")
    @GetMapping("/{id}")
    fun getFolder(@PathVariable id: Long): ResponseEntity<ApiResponse<FolderAndBookmarksDto>> {
        return ResponseEntity.ok(ApiResponse(folderService.findFolder(id)))
    }

    @ApiOperation(value = "폴더 생성")
    @PostMapping
    fun postFolder(
        @AuthenticationPrincipal userDetailsImpl: UserDetailsImpl,
        @RequestBody body: CreateFolderDto
    ): ResponseEntity<ApiResponse<FolderDto>> {
        val userId = userDetailsImpl.getUserId()
        val result = folderService.createFolder(userId, body)
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse(result))
    }

    @ApiOperation(value = "폴더 이름 변경")
    @PatchMapping("/{id}")
    fun patchFolder(
        @PathVariable id: Long,
        @RequestBody body: EditFolderNameDto
    ): ResponseEntity<ApiResponse<FolderDto>> {
        val result = folderService.editFolderName(id, body)
        return ResponseEntity.ok(ApiResponse(result))
    }

    @ApiOperation(value = "폴더 삭제")
    @DeleteMapping("/{id}")
    fun deleteFolder(@PathVariable id: Long): ResponseEntity<Any> { // TODO:
        folderService.deleteFolder(id)
        return ResponseEntity.noContent().build()
    }
}