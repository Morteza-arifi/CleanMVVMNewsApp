package com.thirtysixone.morti.cleanmvvmnewsapp.data.remote.dto

import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Source

data class SourceDto(
    val id: String,
    val name: String
) {
    fun toSource() : Source = Source(
        id = id,
        name = name
    )
}