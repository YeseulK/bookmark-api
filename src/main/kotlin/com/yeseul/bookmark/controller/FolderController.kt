package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.FolderDto
import com.yeseul.bookmark.domain.Folder
import com.yeseul.bookmark.service.FolderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/folders")
class FolderController(
    private val folderService: FolderService
) {

    @GetMapping
    fun getFolders(): List<Folder> {
        return folderService.findFolders()
    }

    @GetMapping("/{id}")
    fun getFolder(@PathVariable id: Long): Folder {
        return folderService.findFolder(id)
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