package com.paul.board.api.board

import com.paul.board.domain.board.dto.BoardDto
import com.paul.board.domain.board.dto.BoardSearchCondition
import com.paul.board.domain.board.service.BoardService
import com.paul.board.domain.common.PageRequest
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api/board")
class BoardApi(
    val boardService: BoardService
) {
    @PostMapping
    fun saveBoard(
        @RequestBody @Validated boardDto: BoardDto,
        principal: Principal
    ) =
        ResponseEntity(boardService.saveBoard(principal.name.toLong(), boardDto), CREATED)

    @PutMapping("{boardId}")
    fun updateBoard(
        boardDto: BoardDto,
        @PathVariable boardId: Long
    ) = ResponseEntity(boardService.updateBoard(1L, boardId, boardDto), OK)

    @GetMapping
    fun findAllBoard(
        @Validated pageRequest: PageRequest,
        searchCondition: BoardSearchCondition
    ) = ResponseEntity(boardService.findAllBoard(pageRequest.of(), searchCondition), OK)

    @DeleteMapping("{boardId}")
    fun deleteBoard(@PathVariable boardId: Long) = ResponseEntity(boardService.deleteBoard(boardId), OK)

    @GetMapping("{boardId}")
    fun findBoard(@PathVariable boardId: Long) = ResponseEntity(boardService.findById(boardId), OK)
}