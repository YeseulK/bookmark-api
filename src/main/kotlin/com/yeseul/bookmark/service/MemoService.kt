package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.CreateMemoDto
import com.yeseul.bookmark.controller.dto.response.MemoDto
import com.yeseul.bookmark.domain.Memo
import com.yeseul.bookmark.repository.MemoRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class MemoService(
    val memoRepository: MemoRepository,
    val mapper: ModelMapper
) {

    fun findMemos(): List<MemoDto> {
        val entities = memoRepository.findAll().toList()
        return entities.map { mapper.map(it, MemoDto::class.java) }
    }

    fun findMemo(id: Long): MemoDto {
        val entity = memoRepository.findById(id).orElse(null)
        return mapper.map(entity, MemoDto::class.java)
    }

    fun createMemo(dto: CreateMemoDto) {
        val entity = mapper.map(dto, Memo::class.java)
        memoRepository.save(entity)
    }

    fun deleteMemo(id: Long) {
        memoRepository.deleteById(id)
    }
}