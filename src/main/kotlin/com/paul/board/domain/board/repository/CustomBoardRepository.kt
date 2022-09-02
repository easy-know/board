package com.paul.board.domain.board.repository

import com.paul.board.domain.board.dto.BoardDto
import com.paul.board.domain.board.dto.BoardSearchCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomBoardRepository {
    fun findBoardById(boardId: Long): BoardDto
    fun findAllBoardBySearchConditionPaging(pageable: Pageable, searchCondition: BoardSearchCondition): Page<BoardDto>
}