package com.paul.board.domain.comment.repository

import com.paul.board.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndMemberId(id: Long, memberId: Long): Comment
}