package com.paul.board.domain.board.entity

import com.paul.board.domain.common.BaseEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "board")
@DynamicUpdate
@DynamicInsert
class Board(
    @Column(length = 20)
    var title: String,

    @Lob
    var contents: String,

    @ColumnDefault("0")
    var likes: Int = 0,

    @Column(name = "member_id")
    val memberId: Long,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    val id: Long? = 0

    fun addLikes(likes: Int) = ++this.likes

    fun updateBoard(title: String, contents: String) {
        this.title = title
        this.contents = contents
    }
}