package com.paul.board.domain.member.dto

import com.paul.board.domain.member.entity.Member
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class MemberDto(
    @NotEmpty var name: String,
    @Email var email: String,
    @NotEmpty var password: String,
) {
    var id: Long? = null

    constructor(member: Member) : this(
        name = member.name,
        email = member.email,
        password = "unknown"
    ) {
        this.id = member.id
    }
}