package com.kh69.passmath.data

import com.kh69.passmath.data.api.PassMathApi
import com.kh69.passmath.data.api.model.mappers.ApiPaginationMapper
import com.kh69.passmath.data.api.model.mappers.ApiQuestionMapper
import com.kh69.passmath.utils.DispatchersProvider
import okhttp3.Cache
import javax.inject.Inject

class PassMathQuestionRepository @Inject constructor(
    private val api: PassMathApi,
    private val cache: Cache,
    private val apiQuestionMapper: ApiQuestionMapper,
    private val apiPaginationMapper: ApiPaginationMapper,
    dispatchersProvider: DispatchersProvider
): AnimalRepository {}