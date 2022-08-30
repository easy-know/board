package com.paul.board.domain.board.service

import com.paul.board.domain.board.dto.BoardDto
import com.paul.board.domain.board.dto.BoardSearchCondition
import com.paul.board.domain.board.entity.Board
import com.paul.board.domain.board.repository.BoardRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BoardService(val boardRepository: BoardRepository) {
    fun saveBoard(memberId: Long, boardDto: BoardDto) {
        boardRepository.save(
            Board(
                title = boardDto.title,
                contents = boardDto.contents,
                memberId = memberId
            )
        )
    }

    fun updateBoard(memberId: Long, boardId: Long, boardDto: BoardDto): Long? {
        val boardOptional = boardRepository.findById(boardId)
        val board = boardOptional.get()
        board.updateBoard(boardDto.title, boardDto.contents)
        return board.id
    }

    fun findById(boardId: Long) = BoardDto(boardRepository.findById(boardId).get())

    fun deleteBoard(boardId: Long) = boardRepository.delete(boardRepository.findById(boardId).get())

    fun findAllBoard(pageable: Pageable, searchCondition: BoardSearchCondition) =
        boardRepository.findAllBoard(pageable, searchCondition)
}