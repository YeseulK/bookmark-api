package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.FolderDto
import com.yeseul.bookmark.domain.Bookmark
import com.yeseul.bookmark.domain.Folder
import com.yeseul.bookmark.response.ApiPageMeta
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.FolderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/folders")
class FolderController(
    private val folderService: FolderService
) {

    @GetMapping
    fun getFolders(): ResponseEntity<ApiResponse<List<Folder>>> {
        val result = folderService.findFolders()
        val response = ApiResponse(result, ApiPageMeta(total = result.count()))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getFolder(@PathVariable id: Long):  ResponseEntity<ApiResponse<Folder>> {
        return ResponseEntity.ok(ApiResponse(folderService.findFolder(id)))
    }

    @PostMapping
    fun postFolder(@RequestBody body: FolderDto) {
        folderService.createFolder(body)
    }

    @DeleteMapping("/{id}")
    fun deleteFolder(@PathVariable id: Long) {
        folderService.deleteFolder(id)
    }
}