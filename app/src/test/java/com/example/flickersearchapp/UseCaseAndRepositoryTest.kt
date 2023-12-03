package com.example.flickersearchapp

import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.utils.ResponseState
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * The local unit test, which will test the scenario for API call if it got success or error.
 * Provided the data being fetched against the search query
 * Also, performing test for use case and repository calls.
 */
class UseCaseAndRepositoryTest {

    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()
    private val photosRepository = SearchPhotosRepository(this.apiInterface)
    private val photosUseCase = SearchPhotosUseCase(this.photosRepository)

    @Test
    fun `search data is fetched`() = runBlocking {
        val searchResult =
            PhotoSearchModule.getPhotoSearchApi().getSearchResults(
                apiKey = "5a2cc90782760b3a6b3eca570dfaf5c3",
                text = "",
                page = 1,
                format = "json",
                noJsonCallback = 1
            )
        val list = searchResult.photos?.photo
        assert(list?.size!! > 0)

        /* This is an example model for validating test from realtime API Call.
          Photo(
            farm=66,
            id=53364943048,
            owner=125857360@N02,
            secret=c095b1ee16,
            server=65535,
            title=Sanrio Hello Kitty Candy Sticks 10 a
        )*/
        assertEquals(list[0]?.title, "Sanrio Hello Kitty Candy Sticks 10 a")
    }
/*
    @Test
    fun `search photos repository and useCase`(): Unit = runBlocking {
        val apiInterface = PhotoSearchModule.getPhotoSearchApi()
        val repository = SearchPhotosRepository(apiInterface)
        val searchResult = SearchPhotosUseCase(repository)
        assert(searchResult.invoke("Hello", viewModelScope).count() > 0)
    }

    @Test
    fun `when use case returns success then resource should be success`() {
        runBlocking {
            val searchUseCase = photosUseCase.invoke("hello", viewModelScope)
            val eventCount = searchUseCase.count()
            assert(eventCount >= 2)

            var resource = searchUseCase.first()
            assert(resource is ResponseState.Loading)

            resource = searchUseCase.last()
            assert(resource is ResponseState.Success)
            assert(resource.data != null)

            println(eventCount)
        }
    }

    @Test
    fun `when use case returns error then resource returned should be error`() {
        runTest {
            val searchUseCase = photosUseCase.invoke("hello", viewModelScope)
            val eventCount = searchUseCase.count()
            assert(eventCount == 2)

            var resource = searchUseCase.first()
            assert(resource is ResponseState.Loading)

            resource = searchUseCase.last()
            assert(resource is ResponseState.Error)
            println(resource)

            *//**
             * Uncomment it if you want to see the success of this test, as it would get success
             * when continuity of the this test case return the error

            resource = searchUseCase.last()
            assert(resource is ResponseState.Success)
            assert(resource.data != null)
             **//*
        }
    }*/
}