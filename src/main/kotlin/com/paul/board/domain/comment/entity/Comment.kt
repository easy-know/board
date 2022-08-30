package com.paul.board.domain.comment.entity

import com.paul.board.domain.board.entity.Board
import com.paul.board.domain.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "comment")
class Comment(
    var content: String,

    @Column(name = "member_id")
    val memberId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    val board: Board
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    val id: Long? = 0

    fun updateContent(content: String) {
        this.content = content
    }
}