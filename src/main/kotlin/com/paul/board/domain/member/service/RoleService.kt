package com.paul.board.domain.member.service

import com.paul.board.domain.member.entity.Role
import com.paul.board.domain.member.repository.RoleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RoleService(val roleRepository: RoleRepository) {
    fun saveRole(name: String) = roleRepository.save(Role(name))
}