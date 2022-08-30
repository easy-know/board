package com.paul.board.domain.board.repository

import com.paul.board.domain.board.dto.BoardSearchCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomBoardRepository<T> {
    fun findAllBoard(pageable: Pageable, searchCondition: BoardSearchCondition): Page<T>
}