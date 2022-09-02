package com.paul.board.domain.board.dto

import com.paul.board.domain.board.entity.Board
import com.paul.board.domain.common.BaseEntityDto
import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime
import javax.validation.constraints.Size

class BoardDto(
    @Size(min = 1, max = 20)
    var title: String,

    @Size(min = 10)
    var contents: String
) : BaseEntityDto() {
    var id: Long? = 0
    var memberId: Long? = null
    var author: String? = "anonymous"
    var likes: Int? = 0

    constructor(board: Board) : this(
        title = board.title,
        contents = board.contents
    )

    @QueryProjection
    constructor(
        id: Long,
        title: String,
        contents: String,
        memberId: Long,
        author: String,
        createdDate: LocalDateTime,
        likes: Int
    ) : this(
        title = title,
        contents = contents
    ) {
        this.id = id
        this.memberId = memberId
        this.author = author
        this.createdDate = createdDate
        this.likes = likes
    }
}