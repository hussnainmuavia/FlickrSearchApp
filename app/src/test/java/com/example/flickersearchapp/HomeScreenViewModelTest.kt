package com.example.flickersearchapp

import androidx.paging.compose.collectAsLazyPagingItems
import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.utils.ResponseState
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomeScreenViewModelTest {

    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()
    private val photosRepository = SearchPhotosRepository(this.apiInterface)
    private val searchPhotosUseCase = SearchPhotosUseCase(this.photosRepository)
    private val homeScreenViewModel = HomeScreenViewModel(searchPhotosUseCase)

    @Test
    fun `when search text set then resource should be success`() {
        runTest {
            homeScreenViewModel.updatedSearchText("Hello")

            CoroutineScope(Dispatchers.IO).launch {
                homeScreenViewModel.setSearch(homeScreenViewModel.searchText)
            }
            assert(homeScreenViewModel.searchText == "Hello")
            //assert(homeScreenViewModel.searchData.collectAsLazyPagingItems().itemCount > 0 )
        }
    }

    @Test
    fun `when search list returns then resource should be success`() {
        runTest {
           // assert(homeScreenViewModel.uiState.value != null)
           // assert(homeScreenViewModel.uiState.value.photosList != null)
        }
    }
}