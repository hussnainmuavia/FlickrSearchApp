package com.example.flickersearchapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val searchUseCase: SearchPhotosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoSearchState())
    val uiState: StateFlow<PhotoSearchState> = _uiState.asStateFlow()

    /**
     * mutable State [searchText] of string type that would being updated when user will type
     */
    var searchText by mutableStateOf("")
        private set

    fun updatedSearchText(newValue: String) {
        searchText = newValue
    }

    fun setSearch(query: String) {
        _search.value = query
    }

    private val _search = MutableStateFlow("")
    private val search = _search.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchData = search.flatMapLatest { query ->
        searchUseCase(query = query, viewModelScope)
    }

    /* fun getSearch() = viewModelScope.launch(Dispatchers.IO) {
         searchUseCase(query = searchText, viewModelScope).collect { responseState ->
             when (responseState) {
                 is ResponseState.Loading<*> -> {
                     _uiState.update { PhotoSearchState(isLoading = true) }
                 }

                 is ResponseState.Success<*> -> {
                     _uiState.update {
                         PhotoSearchState(
                             isLoading = false,
                             photosList = responseState
                         )
                     }
                 }

                 is ResponseState.Error<*> -> {
                     _uiState.update {
                         PhotoSearchState(
                             isLoading = false,
                             message = responseState.message ?: "An unexpected error occurred"
                         )
                     }
                 }
             }
         }
     }*/

    data class PhotoSearchState(
        val isLoading: Boolean = false,
        val photosList: Flow<PagingData<Photo>>? = null,
        val message: String = ""
    )
}