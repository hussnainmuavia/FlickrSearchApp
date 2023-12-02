package com.example.flickersearchapp.domain.models

data class PagedResponse(
    val data: List<Photo>?,
    val total: Int,
    val page: Int,
)
