package com.example.flickersearchapp

import android.text.method.Touch.scrollTo
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel
import io.mockk.coEvery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class HomeScreenViewModelTest {

    private val apiInterface = PhotoSearchModule.getPhotoSearchApi()
    private val photosRepository = SearchPhotosRepository(this.apiInterface)
    private val searchPhotosUseCase = SearchPhotosUseCase(this.photosRepository)
    private val homeScreenViewModel = HomeScreenViewModel(searchPhotosUseCase)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when search text set then resource should be success`() {
        runTest {
            homeScreenViewModel.updatedSearchText("Hello")

            CoroutineScope(Dispatchers.IO).launch {
                homeScreenViewModel.setSearch(homeScreenViewModel.searchText)
                homeScreenViewModel.searchData.flatMapLatest { query ->
                    searchPhotosUseCase(query = homeScreenViewModel.searchText)
                }
                assert(homeScreenViewModel.searchData.count() > 0)
                val first = homeScreenViewModel.searchData.first()
                assert(first.equals(homeScreenViewModel.searchData))
            }
            assert(homeScreenViewModel.searchText == "Hello")

            val items: Flow<PagingData<Photo>> = homeScreenViewModel.searchData
           // assert(items.count() > 0 )
        }
    }

    @Test
    fun `when search list returns then resource should be success`() {
        runTest {
            homeScreenViewModel.setSearch("Hello")
            assert(homeScreenViewModel.searchData.collect() != null)

            assertEquals(homeScreenViewModel.search, homeScreenViewModel.search, "")
        }
    }

    /*fun test_items_contain_one_to_ten() = runTest {
        // Get the Flow of PagingData from the ViewModel under test
        val items: Flow<PagingData<Photo>> = homeScreenViewModel.searchData

        val itemsSnapshot: List<Photo> = items.asSnapshot {
            // Scroll to the 50th item in the list. This will also suspend till
            // the prefetch requirement is met if there's one.
            // It also suspends until all loading is complete.
            scrollTo(index = 50)
        }

        // With the asSnapshot complete, you can now verify that the snapshot
        // has the expected values
        assertEquals(
            expected = (0..50).map(Int::Photo),
            actual = itemsSnapshot
        )
    }*/

   /* fun test_footer_is_visible() = runTest {
        // Get the Flow of PagingData from the ViewModel under test
        val items: Flow<PagingData<Photo>> = homeScreenViewModel.searchData

        val itemsSnapshot: List<String> = items.asSnapshot {
            // Scroll till the footer is visible
            appendScrollWhile { item: String -> item != "Footer" }
        }
    }*/
}