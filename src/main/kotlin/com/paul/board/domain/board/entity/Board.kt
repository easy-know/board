package com.paul.board.domain.board.entity

import com.paul.board.domain.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "board")
class Board(
    var title: String,
    var contents: String,
    var likes: Int? = 0,
    @Column(name = "member_id")
    val memberId: Long
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    val id: Long? = 0

    fun addLikes(likes: Int) {
        this.likes = this.likes!! + 1
    }

    fun updateBoard(title: String, contents: String) {
        this.title = title
        this.contents = contents
    }
}