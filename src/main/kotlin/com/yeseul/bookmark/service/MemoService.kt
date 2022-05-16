package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.CreateMemoDto
import com.yeseul.bookmark.controller.dto.response.MemoDto
import com.yeseul.bookmark.domain.Memo
import com.yeseul.bookmark.repository.MemoRepository
import com.yeseul.bookmark.utils.DataWithTotal
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MemoService(
    val memoRepository: MemoRepository,
    val mapper: ModelMapper
) {

    fun findMemos(pageable: Pageable): DataWithTotal<List<MemoDto>> {
        val entities = memoRepository.findAll(pageable)
        val totalCount = entities.totalElements.toInt()
        val data = entities.toList().map { mapper.map(it, MemoDto::class.java) }
        return DataWithTotal(data, totalCount)
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