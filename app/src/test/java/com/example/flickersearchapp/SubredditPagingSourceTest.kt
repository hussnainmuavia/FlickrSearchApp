package com.example.flickersearchapp

import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult.Page
import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.repository.SearchPhotoPagingSource
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.fakeEntities.FakePhotosFactory
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.junit.JUnitAsserter.assertEquals

class SubredditPagingSourceTest {

    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()
    private val photosRepository = SearchPhotosRepository(this.apiInterface)

    private val fakePhotosFactory = FakePhotosFactory()
    private val fakePhotos = listOf(
        fakePhotosFactory.createPhoto(),
        fakePhotosFactory.createPhoto(),
        fakePhotosFactory.createPhoto()
    )

    @Test
    fun itemKeyedPagingSource() = runTest {
        val pagingSource = SearchPhotoPagingSource("Hello", photosRepository)
        assertEquals(
            expected = Page(
                data = listOf(fakePhotos[0], fakePhotos[1]),
                prevKey = fakePhotos[0].title,
                nextKey = fakePhotos[0].title
            ),
            actual = pagingSource.load(
                Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
            message = ""
        )
    }

    @Test
    fun pageKeyedPagingSource() = runTest {
        val pagingSource = SearchPhotoPagingSource("Hello", photosRepository)
        assertEquals(
            expected = Page(
                data = listOf(fakePhotos[0], fakePhotos[1]),
                prevKey = null,
                nextKey = fakePhotos[1].title
            ),
            actual = pagingSource.load(
                Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
            message = ""
        )
    }
}