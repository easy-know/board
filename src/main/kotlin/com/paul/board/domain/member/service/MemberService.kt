package com.paul.board.domain.member.service

import com.paul.board.domain.member.dto.MemberDto
import com.paul.board.domain.member.entity.Member
import com.paul.board.domain.member.repository.MemberRepository
import com.paul.board.domain.member.repository.RoleRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    val memberRepository: MemberRepository,
    val roleRepository: RoleRepository,
    val passwordEncoder: PasswordEncoder
) {
    fun saveMember(memberDto: MemberDto) {
        val member = Member(
            email = memberDto.email,
            password = passwordEncoder.encode(memberDto.password),
            name = memberDto.name
        )
        val role = roleRepository.findByName("ROLE_MEMBER") ?: throw IllegalArgumentException("존재하지 않는 권한입니다.")
        member.addRole(role)
        memberRepository.save(member)
    }

    fun updateMember(memberId: Long, memberDto: MemberDto) =
        memberRepository.save(memberRepository.findById(memberId).get()).id

    fun deleteMember(memberId: Long) = memberRepository.delete(memberRepository.findById(memberId).get())

    @Transactional(readOnly = true)
    fun findById(memberId: Long) = MemberDto(memberRepository.findById(memberId).get())

    @Transactional(readOnly = true)
    fun findAll() = memberRepository.findAll().map { member -> MemberDto(member) }
}