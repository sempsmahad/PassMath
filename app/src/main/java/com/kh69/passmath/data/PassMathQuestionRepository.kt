package com.kh69.passmath.data

import com.kh69.passmath.data.api.PassMathApi
import okhttp3.Cache
import javax.inject.Inject

class PassMathQuestionRepository @Inject constructor(
    private val api: PassMathApi,
    private val cache: Cache,
    private val apiAnimalMapper: ApiAnimalMapper,
    private val apiPaginationMapper: ApiPaginationMapper,
    dispatchersProvider: DispatchersProvider
): AnimalRepository {}