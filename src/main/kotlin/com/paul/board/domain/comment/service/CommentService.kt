package com.paul.board.domain.comment.service

import com.paul.board.domain.board.repository.BoardRepository
import com.paul.board.domain.comment.dto.CommentDto
import com.paul.board.domain.comment.repository.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService(
    val commentRepository: CommentRepository,
    val boardRepository: BoardRepository
) {
    fun saveComment(memberId: Long, commentDto: CommentDto): Long? {
        val board = boardRepository.findById(commentDto.boardId).get()
        return commentRepository.save(CommentDto.convertEntity(memberId, board, commentDto)).id
    }

    fun updateComment(commentId: Long, commentDto: CommentDto) {
        val comment = commentRepository.findById(commentId).get()
        comment.updateContent(commentDto.content)
    }

    fun findById(commentId: Long) = CommentDto(commentRepository.findById(commentId).get())
    fun deleteComment(memberId: Long, commentId: Long) =
        commentRepository.delete(commentRepository.findByIdAndMemberId(commentId, memberId))
}