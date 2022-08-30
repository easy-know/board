package com.paul.board.domain.comment.dto

import com.paul.board.domain.board.entity.Board
import com.paul.board.domain.comment.entity.Comment

class CommentDto(
    var content: String,
    val boardId: Long,
    val memberId: Long
) {
    constructor(comment: Comment) : this(
        content = comment.content,
        boardId = comment.board.id!!,
        memberId = comment.memberId,
    )

    companion object {
        fun convertEntity(memberId: Long, board: Board, commentDto: CommentDto) = Comment(
            content = commentDto.content,
            memberId = memberId,
            board = board
        )
    }
}