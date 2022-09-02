package com.paul.board.domain.member.entity

import com.paul.board.domain.common.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*


@Entity
@Table(name = "member")
@DynamicInsert
@DynamicUpdate
class Member(
    @Column(unique = true)
    val email: String,
    val password: String,

    @Column(length = 5)
    var name: String,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    val id: Long? = 0

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "member_role",
        joinColumns = [JoinColumn(name = "member_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: MutableSet<Role> = mutableSetOf()

    fun updateMember(name: String) = run { this.name = name }

    fun addRole(role: Role) = run { this.roles.add(role) }
}