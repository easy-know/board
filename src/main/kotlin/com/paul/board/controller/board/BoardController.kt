package com.paul.board.controller.board

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class BoardController {
    @GetMapping
    fun viewBoardList(): String = "contents/board-list"

    @GetMapping("{boardId}")
    fun viewBoardDetail(@PathVariable boardId: String): String = "contents/board-detail"

    @GetMapping("post")
    fun viewIndex(): String = "contents/board-post"
}