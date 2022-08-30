package com.paul.board.domain.common

import java.time.LocalDateTime

data class BaseEntityDto(
    val createdBy: String = "anonymous",
    val createdDate: LocalDateTime? = null,
    var lastModifiedBy: String = "anonymous",
    var lastModifiedDate: LocalDateTime? = null,
    var useYn: String? = null
)