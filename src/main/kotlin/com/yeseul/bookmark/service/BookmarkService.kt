package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.CreateBookmarkDto
import com.yeseul.bookmark.controller.dto.response.BookmarkDto
import com.yeseul.bookmark.domain.Bookmark
import com.yeseul.bookmark.domain.Folder
import com.yeseul.bookmark.repository.BookmarkRepository
import com.yeseul.bookmark.repository.FolderRepository
import com.yeseul.bookmark.repository.MemberRepository
import com.yeseul.bookmark.utils.DataWithTotal
import org.modelmapper.ModelMapper
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    val bookmarkRepository: BookmarkRepository,
    val memberRepository: MemberRepository,
    val folderRepository: FolderRepository,
    val mapper: ModelMapper
) {

    fun findBookmarks(folderId: Long, pageable: Pageable): DataWithTotal<List<BookmarkDto>> {
        val folder = folderRepository.findById(folderId).orElseThrow { throw NotFoundException() }
        val entities = bookmarkRepository.findAllByFolder(folder, pageable)
        val totalCount = entities.totalElements.toInt()
        val data = entities.toList().map { mapper.map(it, BookmarkDto::class.java) }
        return DataWithTotal(data, totalCount)
    }

    fun findBookmark(id: Long): BookmarkDto {
        val entity = bookmarkRepository.findById(id).orElse(null)
        return mapper.map(entity, BookmarkDto::class.java)
    }

    fun createBookmark(username: String, folderId: Long, dto: CreateBookmarkDto) {
        val member = memberRepository.findByUsername(username)
        val entity = Bookmark(
            url = dto.url,
            folder = Folder(id = folderId, member = member)
        )
        bookmarkRepository.save(entity)
    }

    fun deleteBookmark(id: Long) {
        bookmarkRepository.deleteById(id)
    }
}