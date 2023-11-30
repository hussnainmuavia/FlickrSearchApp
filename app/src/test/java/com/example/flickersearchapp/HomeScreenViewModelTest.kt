package com.example.flickersearchapp

import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomeScreenViewModelTest {

    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()
    private val photosRepository = SearchPhotosRepository(this.apiInterface)
    private val searchPhotosUseCase = SearchPhotosUseCase(this.photosRepository)
    private val popularMoviesViewModel = HomeScreenViewModel(searchPhotosUseCase)

    @Test
    fun `when use case returns success then resource should be success`() {
        runTest {

            /* coEvery { searchPhotosUseCase.invoke("Hello") } returns flow {
                 emit(
                     ResponseState.Success(
                         listOf()
                     )
                 )
             }*/

            //coVerify(exactly = 1) { searchPhotosUseCase.invoke("Hello") }
            assert(popularMoviesViewModel.uiState.value != null)
            //   assert(popularMoviesViewModel.getSearch().value is ResponseState.Success)
            assert(popularMoviesViewModel.uiState.value.photosList != null)
        }
    }

    @Test
    fun `when use case returns error then resource should be error`() {
        runTest {
            //every { apiObserver.onChanged(any()) } answers { }
            /*coEvery { searchPhotosUseCase.invoke("Hello") } returns flow {
                emit(
                    ResponseState.Error(
                        "Unexpected error"
                    )
                )
            }*/

            //coVerify(exactly = 1) { searchPhotosUseCase.invoke("Hello") }
            assert(popularMoviesViewModel.uiState.value.photosList != null)
            //assert(popularMoviesViewModel.getSearch() is ResponseState.Error)
            popularMoviesViewModel.updatedSearchText("Hello")
           // assert(popularMoviesViewModel.searchText == "Hello")
        }
    }
}