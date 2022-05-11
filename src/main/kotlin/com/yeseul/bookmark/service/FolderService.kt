package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.CreateFolderDto
import com.yeseul.bookmark.controller.dto.response.FolderDto
import com.yeseul.bookmark.domain.Folder
import com.yeseul.bookmark.repository.FolderRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class FolderService(
    val folderRepository: FolderRepository,
    val mapper: ModelMapper,
) {

    fun findFolders(): List<FolderDto> {
        val entities = folderRepository.findAll().toList()
        return entities.map { mapper.map(it, FolderDto::class.java) }
    }

    fun findFolder(id: Long): FolderDto {
        val entity = folderRepository.findById(id).orElse(null)
        return mapper.map(entity, FolderDto::class.java)
    }

    fun createFolder(dto: CreateFolderDto) {
        val entity = mapper.map(dto, Folder::class.java)
        folderRepository.save(entity)
    }

    fun deleteFolder(id: Long) {
        folderRepository.deleteById(id)
    }
}