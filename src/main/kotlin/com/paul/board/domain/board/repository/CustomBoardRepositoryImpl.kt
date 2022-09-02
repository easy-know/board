package com.paul.board.domain.board.repository

import com.paul.board.domain.board.dto.BoardDto
import com.paul.board.domain.board.dto.BoardSearchCondition
import com.paul.board.domain.board.dto.QBoardDto
import com.paul.board.domain.board.entity.Board
import com.paul.board.domain.board.entity.QBoard.board
import com.paul.board.domain.member.entity.QMember.member
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.apache.logging.log4j.util.Strings
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class CustomBoardRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : CustomBoardRepository, QuerydslRepositorySupport(Board::class.java) {
    override fun findBoardById(boardId: Long): BoardDto {
        val query = jpaQueryFactory
            .select(
                QBoardDto(
                    board.id,
                    board.title,
                    board.contents,
                    board.memberId,
                    member.name,
                    board.createdDate,
                    board.likes
                )
            )
            .from(board)
            .join(member).on(board.memberId.eq(member.id))
            .where(eqBoardId(boardId))

        return query.fetchOne()!!
    }

    override fun findAllBoardBySearchConditionPaging(
        pageable: Pageable,
        searchCondition: BoardSearchCondition
    ): Page<BoardDto> {
        val query = querydsl?.applyPagination(
            pageable, jpaQueryFactory
                .select(
                    QBoardDto(
                        board.id,
                        board.title,
                        board.contents,
                        board.memberId,
                        member.name,
                        board.createdDate,
                        board.likes
                    )
                )
                .from(board)
                .join(member).on(board.memberId.eq(member.id))
                .where(
                    eqTitle(searchCondition.title),
                    eqContents(searchCondition.contents)
                )
        )

        val boardList = query!!.fetch()
        val totalCount = query.fetchCount()

        return PageImpl(boardList, pageable, totalCount)
    }

    private fun eqBoardId(boardId: Long): BooleanExpression? {
        return board.id.eq(boardId)
    }

    private fun eqTitle(title: String?): BooleanExpression? {
        return if (Strings.isEmpty(title)) null else board.title.eq(title)
    }

    private fun eqContents(contents: String?): BooleanExpression? {
        return if (Strings.isEmpty(contents)) null else board.contents.eq(contents)
    }
}