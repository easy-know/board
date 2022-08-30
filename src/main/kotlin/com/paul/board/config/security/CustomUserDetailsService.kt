package com.paul.board.config.security

import com.paul.board.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return CustomUserDetails(
            member = memberRepository.findByEmail(username) ?: throw UsernameNotFoundException("아이디나 비밀번호가 일치하지 않습니다.")
        )
    }
}