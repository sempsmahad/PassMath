package com.kh69.passmath.data.api.model.mappers

import com.kh69.passmath.data.api.model.ApiPaginatedQuestions
import com.kh69.passmath.domain.model.pagination.Pagination
import javax.inject.Inject

class ApiPaginationMapper @Inject constructor(): ApiMapper<ApiPaginatedQuestions.ApiPagination?, Pagination> {

    override fun mapToDomain(apiEntity: ApiPaginatedQuestions.ApiPagination?): Pagination {
        return Pagination(
            currentPage = apiEntity?.currentPage ?: 0,
            totalPages = apiEntity?.totalPages ?: 0
        )
    }
}