package com.example.flickersearchapp

import androidx.paging.PagingData
import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class HomeScreenViewModelTest {

    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()
    private val photosRepository = SearchPhotosRepository(this.apiInterface)
    private val searchPhotosUseCase = SearchPhotosUseCase(this.photosRepository)
    private val homeScreenViewModel = HomeScreenViewModel(searchPhotosUseCase)


    @Test
    fun `when search text state is updated`() {
        runTest {
            homeScreenViewModel.updatedSearchText("Hello")
            assert(homeScreenViewModel.searchText == "Hello")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when search text set then resource should be success`() {
        runTest {
            CoroutineScope(Dispatchers.IO).launch {
                homeScreenViewModel.setSearch(homeScreenViewModel.searchText)
                homeScreenViewModel.searchData.flatMapLatest { query ->
                    searchPhotosUseCase(query = homeScreenViewModel.searchText)
                }
                assert(homeScreenViewModel.searchData.count() > 0)
            }
        }
    }
}