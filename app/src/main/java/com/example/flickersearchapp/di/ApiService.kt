package com.example.flickersearchapp.di

import com.example.flickersearchapp.domain.models.SearchResult
import com.example.flickersearchapp.utils.Constants.KEY_API_KEY
import com.example.flickersearchapp.utils.Constants.KEY_FORMAT
import com.example.flickersearchapp.utils.Constants.KEY_NO_JSON_CALLBACK
import com.example.flickersearchapp.utils.Constants.KEY_QUERY
import com.example.flickersearchapp.utils.Constants.SEARCH_PHOTO_PATH
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(SEARCH_PHOTO_PATH)
    suspend fun getSearchResults(
        @Query(KEY_API_KEY) apiKey: String = "5a2cc90782760b3a6b3eca570dfaf5c3",
        @Query(KEY_QUERY) text: String,
        @Query(KEY_FORMAT) format: String = "json",
        @Query(KEY_NO_JSON_CALLBACK) noJsonCallback: Int = 1
    ): SearchResult
}