package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.FolderDto
import com.yeseul.bookmark.domain.Folder
import com.yeseul.bookmark.repository.FolderRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class FolderService(
    val folderRepository: FolderRepository,
    val mapper: ModelMapper,
) {

    fun findFolders(): List<Folder> {
        return folderRepository.findAll().toList()
    }

    fun findFolder(id: Long): Folder {
        return folderRepository.findById(id).orElse(null)
    }

    fun createFolder(dto: FolderDto): Folder {
        val entity = mapper.map(dto, Folder::class.java)
        return folderRepository.save(entity)
    }

    fun deleteFolder(id: Long) {
        folderRepository.deleteById(id)
    }
}