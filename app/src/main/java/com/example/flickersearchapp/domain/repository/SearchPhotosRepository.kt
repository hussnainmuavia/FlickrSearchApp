package com.example.flickersearchapp.domain.repository

import com.example.flickersearchapp.di.ApiService
import com.example.flickersearchapp.domain.models.PagedResponse
import javax.inject.Inject

class SearchPhotosRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getSearchPhotos(query: String, page: Int): PagedResponse {
        val networkResult = apiService.getSearchResults(text = query, page = page)
        return PagedResponse(
            data = networkResult.photos?.photo,
            total = networkResult.photos?.total ?: 0,
            page = page
        )
    }
}