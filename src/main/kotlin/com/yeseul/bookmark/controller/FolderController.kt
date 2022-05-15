package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateFolderDto
import com.yeseul.bookmark.controller.dto.response.FolderDto
import com.yeseul.bookmark.response.ApiPageMeta
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
        request: HttpServletRequest,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") limit: Int,
    ): ResponseEntity<ApiResponse<List<FolderDto>>> {
        val username = request.getAttribute("username") as String
        val result = folderService.findFolders(username, page, limit)
//        val response = ApiResponse(result.data, ApiPageMeta(page, limit, result.total)) // TODO:
        val response = ApiResponse(result)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getFolder(@PathVariable id: Long):  ResponseEntity<ApiResponse<FolderDto>> {
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