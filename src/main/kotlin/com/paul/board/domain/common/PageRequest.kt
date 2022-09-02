package com.paul.board.domain.common

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class PageRequest(
    @Min(value = 1, message = "페이지는 1보다 작을 수 없습니다.")
    var page: Int,

    @NotNull(message = "페이지 사이즈는 필수 입력 값입니다.")
    @Min(value = 15, message = "페이지 사이즈는 15 이상이어야 합니다.")
    @Max(value = 100, message = "페이지 사이즈는 100 이하이어야 합니다.")
    val size: Int,

    val sort: MutableList<@Pattern(
        regexp = "\\w*\\.+(desc|DESC|asc|ASC)\\Z",
        message = "정렬하고자 하는 필드 명과 정렬방향을 정확히 입력하세요."
    ) String> = mutableListOf()
) {

    private fun getOrders(sortList: List<String>): List<Sort.Order> {
        val orders: MutableList<Sort.Order> = mutableListOf()
        sortList.forEach { sort ->
            orders.add(
                Sort.Order(
                    Sort.Direction.valueOf(sort.split(".")[1].uppercase(Locale.ROOT)),
                    sort.split(".")[0]
                )
            )
        }

        return orders
    }

    fun of(): PageRequest {
        if (sort.isEmpty()) {
            return PageRequest.of(page - 1, size)
        }

        return PageRequest.of(page - 1, size, Sort.by(this.getOrders(sort)))
    }
}