package com.example.flickersearchapp.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.domain.repository.SearchPhotoPagingSource
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(private val repository: SearchPhotosRepository) {
    companion object {
        private const val INITIAL_PAGE_KEY = 1
        private const val PAGE_SIZE = 20
    }

    operator fun invoke(query: String): Flow<PagingData<Photo>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            initialKey = INITIAL_PAGE_KEY,
            pagingSourceFactory = {
                SearchPhotoPagingSource(query, repository)
            }).flow
    }
}