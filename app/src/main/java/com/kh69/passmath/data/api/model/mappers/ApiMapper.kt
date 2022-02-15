package com.kh69.passmath.data.api.model.mappers


interface ApiMapper<E, D> {

    fun mapToDomain(apiEntity: E): D
}