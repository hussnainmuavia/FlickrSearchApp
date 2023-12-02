package com.example.flickersearchapp.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.flickersearchapp.domain.models.PagedResponse
import com.example.flickersearchapp.domain.models.Photo
import retrofit2.HttpException
import java.io.IOException


/**
 * A [PagingSource] that uses the "nextKey" field as the key for next pages.
 * @see [SearchPhotoPagingSource]
 */
class SearchPhotoPagingSource(
    private val query: String,
    private val searchPhotosRepository: SearchPhotosRepository
) : PagingSource<Int, Photo>() {

    companion object {
        private const val INITIAL_LOAD_SIZE = 0
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        try {
            val currentPageNumber = params.key ?: INITIAL_LOAD_SIZE

            val response: PagedResponse = searchPhotosRepository.getSearchPhotos(
                page = currentPageNumber,
                query = query
            )

            val nextKey = when {
                (params.loadSize * (currentPageNumber + 1)) < response.total -> currentPageNumber + 1
                else -> null
            }

            return LoadResult.Page(
                prevKey = null,
                nextKey = nextKey,
                data = response.data ?: emptyList()
            )
        } catch (ex: HttpException) {
            // HttpException for any HTTP status codes.
            return LoadResult.Error(ex)
        } catch (ex: IOException) {
            // IOException for network failures.
            return LoadResult.Error(ex)
        }
    }
}