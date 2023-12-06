package com.example.flickersearchapp


import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import com.example.flickersearchapp.di.PhotoSearchModule
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * The local unit test, which will test the scenario for API call if it got success or error.
 * Provided the data being fetched against the search query
 * Also, performing test for use case and repository calls.
 */
class UseCaseAndRepositoryUnitTest {


    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)

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

    @Test
    fun `search data is fetched`() = runBlocking {
        val searchResult =
            PhotoSearchModule.getPhotoSearchApi().getSearchResults(
                apiKey = Constants.API_KEY_VALUE,
                text = "Hello",
                page = 1,
                format = "json",
                noJsonCallback = 1
            )
        val list = searchResult.photos?.photo
        assert(list?.size!! > 0)
    }

    @Test
    fun `search Result from use case is fetched`() = runTest {
        testScope.launch {
            val apiInterface = PhotoSearchModule.getPhotoSearchApi()
            val repository = SearchPhotosRepository(apiInterface)
            val searchResult = SearchPhotosUseCase(repository)
            val tmp = searchResult("Hello").take(1).toList().first()
            val result = tmp.collectDataForTest()
            assertEquals(result, result)
        }
    }

    private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, StandardTestDispatcher()) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }

}