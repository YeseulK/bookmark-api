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

    fun findFolders(username: String): List<FolderDto> {
        val member = memberRepository.findByUsername(username)
        val entities = member.folders
        return entities.toList().map { mapper.map(it, FolderDto::class.java) }
    }

    fun findFolder(id: Long): FolderAndBookmarksDto {
        val entity = folderRepository.findById(id).orElse(null)
        return mapper.map(entity, FolderAndBookmarksDto::class.java)
    }

    fun createFolder(username: String, dto: CreateFolderDto) {
        val member = memberRepository.findByUsername(username)
        val entity = Folder(
            name = dto.name,
            member = member
        )
        folderRepository.save(entity)
    }

    fun deleteFolder(id: Long) {
        folderRepository.deleteById(id)
    }
}