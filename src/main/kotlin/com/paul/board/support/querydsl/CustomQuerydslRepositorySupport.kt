package com.paul.board.support.querydsl

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
abstract class CustomQuerydslRepositorySupport
/**
 * Creates a new [QuerydslRepositorySupport] instance for the given domain type.}}"}}}
 * @param domainClass must not be null.
 */
    (domainClass: Class<*>?) : QuerydslRepositorySupport(domainClass!!) {
    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}