package com.paul.board.domain.common

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 10)
    val createdBy: String? = "anonymous"

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    val createdDate: LocalDateTime? = LocalDateTime.now()

    @Column(name = "last_modified_by", length = 10)
    @LastModifiedBy
    var lastModifiedBy: String? = "anonymous"

    @Column(name = "last_modified_date")
    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = LocalDateTime.now()

    @Column(name = "use_yn", length = 10)
    var useYn: String? = "1"
}