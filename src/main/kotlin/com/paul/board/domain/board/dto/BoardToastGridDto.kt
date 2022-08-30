package com.paul.board.domain.board.dto

import com.paul.board.domain.board.entity.Board
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class BoardToastGridDto(
    val data: HashMap<String, Any>
) {
    val result: Boolean = true

    companion object {
        fun of(paging: Page<Board>, currentPage: Int): ResponseEntity<Any> {
            val content = paging.content
            val pagination = HashMap<String, Any>()
            pagination["page"] = currentPage
            pagination["totalPage"] = paging.totalElements

            val result = HashMap<String, Any>()
            result["contents"] = content
            result["pagination"] = pagination

            return ResponseEntity(BoardToastGridDto(data = result), HttpStatus.OK)
        }
    }
}


