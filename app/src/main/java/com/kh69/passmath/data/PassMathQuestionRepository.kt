package com.kh69.passmath.data

import okhttp3.Cache

class PassMathQuestionRepository @Inject constructor(
    private val api: PassMathApi,
    private val cache: Cache,
    private val apiAnimalMapper: ApiAnimalMapper,
    private val apiPaginationMapper: ApiPaginationMapper,
    dispatchersProvider: DispatchersProvider
): AnimalRepository {