package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateFolderDto
import com.yeseul.bookmark.controller.dto.response.FolderAndBookmarksDto
import com.yeseul.bookmark.controller.dto.response.FolderDto
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.FolderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/v1/folders")
class FolderController(
    private val folderService: FolderService
) {

    @GetMapping
    fun getFolders(
        request: HttpServletRequest
    ): ResponseEntity<ApiResponse<List<FolderDto>>> {
        val username = request.getAttribute("username") as String // TODO: 바꾸기
        val result = folderService.findFolders(username)
        val response = ApiResponse(result)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getFolder(@PathVariable id: Long):  ResponseEntity<ApiResponse<FolderAndBookmarksDto>> {
        return ResponseEntity.ok(ApiResponse(folderService.findFolder(id)))
    }

    @PostMapping
    fun postFolder(
        request: HttpServletRequest,
        @RequestBody body: CreateFolderDto
    ) {
        val username = request.getAttribute("username") as String
        folderService.createFolder(username, body)
    }

    @DeleteMapping("/{id}")
    fun deleteFolder(@PathVariable id: Long) {
        folderService.deleteFolder(id)
    }
}