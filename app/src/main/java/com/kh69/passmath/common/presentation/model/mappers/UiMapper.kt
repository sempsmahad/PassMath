package com.kh69.passmath.common.presentation.model.mappers

interface UiMapper<E, V> {

    fun mapToView(input: E): V
}