package com.example.flickersearchapp.domain.repository

import com.example.flickersearchapp.domain.models.SearchResult
import com.example.flickersearchapp.network.ApiService
import javax.inject.Inject

class SearchPhotosRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getSearchPhotos(query: String): SearchResult {
        return apiService.getSearchResults(text = query)
    }
}