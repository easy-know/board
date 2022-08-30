package com.paul.board.domain.board.repository

import com.paul.board.domain.board.entity.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long>, CustomBoardRepository<Board>