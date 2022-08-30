package com.paul.board.domain.board.dto

data class BoardSearchCondition(
    val title: String? = null,
    val contents: String? = null,
    val author: String? = null
)