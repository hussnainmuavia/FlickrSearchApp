package com.example.flickersearchapp

import androidx.paging.PagingSource
import com.example.flickersearchapp.di.ApiService
import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.domain.models.SearchResult
import com.example.flickersearchapp.domain.repository.SearchPhotoPagingSource
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.fakeEntities.FakePhotosFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchPhotosPagingSourceTest2 {

    @Mock
    lateinit var api: ApiService
    lateinit var pagingSource: SearchPhotoPagingSource
    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()
    private val photosRepository = SearchPhotosRepository(this.apiInterface)


    private val fakePhotosFactory = FakePhotosFactory()
    private val fakePhotos = listOf(
        fakePhotosFactory.createFirstPhoto(),
        fakePhotosFactory.createPhoto(),
        fakePhotosFactory.createLastPhoto()
    )

    data class ReviewsResponse(
        val reviews : List<Photo> = ArrayList(),
        val totalCount : Int = 0,
        val pagination : PaginationResponse,
    )

    data class PaginationResponse(val limit : Int = 1, val offset : Int = 0)

    companion object {
        val reviewsResponse = ReviewsResponse(
            reviews = listOf(
                 Photo(farm=66, id="53166585097", isfamily=0, isfriend=0, ispublic=1, owner="190663875@N04", secret="74aae73154", server="65535", title="Hello Kitty with Teddy Bear")
            ),
            totalCount = 10,
            pagination = PaginationResponse(limit = 1, offset = 0)
        )
        val nextReviewsResponse = ReviewsResponse(
            reviews = listOf(
                Photo(farm=66, id="53166585097", isfamily=0, isfriend=0, ispublic=1, owner="190663875@N04", secret="74aae73154", server="65535", title="Hello Kitty with Teddy Bear")
            ),
            totalCount = 10,
            pagination = PaginationResponse(limit = 1, offset = 1)
        )
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        pagingSource = SearchPhotoPagingSource(query = "Hello", searchPhotosRepository = photosRepository)
    }

    @Test
    fun `reviews paging source load - failure - http error`() = runTest {
        val error = RuntimeException("404", Throwable())
        given(api.getSearchResults(text = "Hello", page = 1)).willThrow(error)
        val expectedResult = PagingSource.LoadResult.Error<Int, Photo>(error)
        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `reviews paging source load - failure - received null`() = runTest {
            given(api.getSearchResults(text = "Hello", page = 1)).willReturn(null)
            val expectedResult = PagingSource.LoadResult.Error<Int, Photo>(NullPointerException())
            assertEquals(
                expectedResult.toString(), pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = 0,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                ).toString()
            )
        }

    @Test
    fun `reviews paging source refresh - success`() = runTest {
        given(api.getSearchResults(text = "Hello", page = 1)).willReturn(SearchResult())
        val expectedResult = PagingSource.LoadResult.Page(
            data = fakePhotos,
            prevKey = null,
            nextKey = 1
        )
        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}