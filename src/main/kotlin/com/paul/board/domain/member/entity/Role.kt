package com.paul.board.domain.member.entity

import com.paul.board.domain.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "role")
class Role(
    @Column(name = "name")
    var name: String,
) : BaseEntity() {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0
}
