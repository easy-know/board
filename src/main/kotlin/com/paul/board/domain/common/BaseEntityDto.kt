package com.paul.board.domain.common

import java.time.LocalDateTime

abstract class BaseEntityDto {
    val createdBy: String? = null
    var createdDate: LocalDateTime? = null
    var lastModifiedBy: String? = null
    var lastModifiedDate: LocalDateTime? = null
    var useYn: String? = null
}