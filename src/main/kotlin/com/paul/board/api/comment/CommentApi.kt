package com.paul.board.api.comment

import com.paul.board.domain.comment.dto.CommentDto
import com.paul.board.domain.comment.service.CommentService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api/comment")
class CommentApi(
    val commentService: CommentService
) {
    @PostMapping("{boardId}")
    fun saveComment(
        @PathVariable boardId: Long,
        commentDto: CommentDto
    ) = ResponseEntity(commentService.saveComment(1L, commentDto), CREATED)

    @PutMapping("{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        commentDto: CommentDto
    ) = ResponseEntity(commentService.updateComment(commentId, commentDto), OK)

    @GetMapping("{commentId}")
    fun findComment(@PathVariable commentId: Long) = ResponseEntity(commentService.findById(commentId), OK)

    @DeleteMapping("{commentId}")
    fun deleteComment(@PathVariable commentId: String, principal: Principal) =
        ResponseEntity(commentService.deleteComment(principal.name.toLong(), commentId.toLong()), OK)
}