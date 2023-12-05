package com.example.flickersearchapp


import androidx.paging.PagingSource
import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.domain.repository.SearchPhotoPagingSource
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.fakeEntities.FakePhotosFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchPhotosPagingSourceTest {

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)
    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val fakePhotosFactory = FakePhotosFactory()
    private val fakePhotos = listOf(
        fakePhotosFactory.createFirstPhoto(),
        fakePhotosFactory.createPhoto(),
        fakePhotosFactory.createLastPhoto()
    )
    @Test
    fun `when users are given then users paging source returns error load result`() =
        testScope.runTest {
            // given
            val repository = SearchPhotosRepository(apiInterface)
            val usersPagingSource = SearchPhotoPagingSource(
                query = "Hello",
                searchPhotosRepository = repository
            )

            val params = PagingSource
                .LoadParams
                .Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Error<Int, Photo>(
                    throwable = RuntimeException("no data for you")
                )::class.java

            // when
            val actual = usersPagingSource.load(params = params)::class.java

            // then
            assertEquals(expected, actual)
        }

    @Test
    fun `when users are given for next pages then users paging source returns success append load result`() =
        testScope.runTest {
            // given
            val repository = SearchPhotosRepository(apiInterface)
            val usersPagingSource = SearchPhotoPagingSource(
                query = "Hello",
                searchPhotosRepository = repository
            )

            val params = PagingSource
                .LoadParams
                .Append(
                    key = 20,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Page(
                    data = fakePhotos,
                    prevKey = null,
                    nextKey = 40
                )

            // when
            val actual = usersPagingSource.load(params = params)

            // then
            assertEquals(expected, actual)
        }
}