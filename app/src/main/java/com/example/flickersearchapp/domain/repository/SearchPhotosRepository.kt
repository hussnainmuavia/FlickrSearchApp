package com.example.flickersearchapp.domain.repository

import com.example.flickersearchapp.di.ApiService
import com.example.flickersearchapp.domain.models.SearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class SearchPhotosRepository @Inject constructor(
    private val apiService: ApiService,
) {

    // Mutex to make writes to cached values thread-safe.
    private val latestSearchResultMutex = Mutex()

    /**
     * It's important that each repository defines a single source of truth.
     * The source of truth always contains data that is consistent, correct, and up-to-date.
     * In fact, the data exposed from the repository should always be the data coming directly from the source of truth.
     */
    var mSearchResult: SearchResult? = null
        set(value) {
            field = value
            /**  As conveniently, when we will set the data this setter will run and set the value.
             *  If we want to implement Room persistence and store the data into the db
             *  then we would run a add operations here for DB to persist the data into
             *  the DB.
             *  e.g. DBDao.addSearchResult()
             * */
            if (field != null) {
                //DB Operation to add
            }
        }
        /*get() {
            return if (mSearchResult != null) {
            *//**
             *  When we will get the data of object it will check if the  object of
             *  [mSearchResult] is not empty it will return the object in memory.
             *  So here we can save additional calls in case of local db access or network
             *  operations for getting the same data.
             *  this get() will called and return the field.
             *//*
                field
            } else {
                 / **  When we will get the data of object it will check if the  object of
                 *  [mSearchResult] is empty it will return the object from local DB
                 *  e.g. DBDao.addSearchResult()
                 *
                 *  }
            }*/

    suspend fun getSearchPhotos(query: String, page: Int): SearchResult {
        val networkResult = apiService.getSearchResults(text = query, page = page)
        // Mutex to make writes to cached values thread-safe.
        // Thread-safe write to search results.
        latestSearchResultMutex.withLock {
            this.mSearchResult = networkResult
        }

        //mSearchResult = apiService.getSearchResults(text = query, page = page)
        return latestSearchResultMutex.withLock { mSearchResult as SearchResult }
    }
}