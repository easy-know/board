package com.paul.board.domain.board.dto

import com.paul.board.domain.board.entity.Board

class BoardDto(
    var title: String,
    var contents: String
) {
    constructor(board: Board) : this(board.title, board.contents)
}