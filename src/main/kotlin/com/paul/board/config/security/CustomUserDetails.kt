package com.paul.board.config.security

import com.paul.board.domain.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val member: Member
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        AuthorityUtils.createAuthorityList(member.roles.parallelStream().map { it.name }
            .toString())

    override fun getPassword(): String = member.password

    override fun getUsername(): String = member.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = (member.useYn.equals("1"))
}