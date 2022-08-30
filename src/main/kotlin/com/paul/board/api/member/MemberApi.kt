package com.paul.board.api.member

import com.paul.board.domain.member.dto.MemberDto
import com.paul.board.domain.member.service.MemberService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/member")
class MemberApi(val memberService: MemberService) {
    @GetMapping("{memberId}")
    fun findMemberById(@PathVariable memberId: Long) = ResponseEntity(memberService.findById(memberId), OK)

    @GetMapping
    fun findAllMembers() = ResponseEntity(memberService.findAll(), OK)

    @PostMapping
    fun saveMember(@RequestBody @Validated memberDto: MemberDto) =
        ResponseEntity(memberService.saveMember(memberDto), CREATED)

    @PutMapping("{memberId}")
    fun updateMember(
        @PathVariable memberId: Long,
        memberDto: MemberDto
    ) = ResponseEntity(memberService.updateMember(memberId, memberDto), OK)

    @DeleteMapping
    fun deleteMember() = ResponseEntity(memberService.deleteMember(1L), OK)
}