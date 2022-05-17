package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.CreateFolderDto
import com.yeseul.bookmark.controller.dto.response.FolderAndBookmarksDto
import com.yeseul.bookmark.controller.dto.response.FolderDto
import com.yeseul.bookmark.domain.Folder
import com.yeseul.bookmark.repository.FolderRepository
import com.yeseul.bookmark.repository.MemberRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class FolderService(
    val folderRepository: FolderRepository,
    val memberRepository: MemberRepository,
    val mapper: ModelMapper,
) {

    fun findFolders(userId: Long): List<FolderDto> {
        val member = memberRepository.findById(userId)
        val entities = member.get().folders
        return entities.toList().map { mapper.map(it, FolderDto::class.java) }
    }

    fun findFolder(id: Long): FolderAndBookmarksDto {
        val entity = folderRepository.findById(id).orElse(null)
        return mapper.map(entity, FolderAndBookmarksDto::class.java)
    }

    fun createFolder(userId: Long, dto: CreateFolderDto): FolderDto {
        val member = memberRepository.findById(userId)
        val entity = Folder(
            name = dto.name,
            member = member.get()
        )
        val created = folderRepository.save(entity)
        return mapper.map(created, FolderDto::class.java)
    }

    fun deleteFolder(id: Long) {
        folderRepository.deleteById(id)
    }
}