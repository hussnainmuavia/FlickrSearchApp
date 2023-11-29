package com.example.flickersearchapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickersearchapp.domain.models.PhotoMap
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val searchUseCase: SearchPhotosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoSearchState())
    val uiState: StateFlow<PhotoSearchState> = _uiState.asStateFlow()

    var searchText by mutableStateOf("")
        private set

    fun updatedSearchText(newValue: String) {
        searchText = newValue
    }

    fun getSearch() = viewModelScope.launch(Dispatchers.IO) {
        searchUseCase.invoke(query = searchText).collect { responseState ->
            when (responseState) {
                is ResponseState.Loading -> {
                    _uiState.update { PhotoSearchState(isLoading = true) }
                }

                is ResponseState.Success -> {
                    _uiState.update {
                        PhotoSearchState(
                            isLoading = false,
                            photosList = responseState.data ?: emptyList()
                        )
                    }
                }

                is ResponseState.Error -> {
                    _uiState.update {
                        PhotoSearchState(
                            isLoading = false,
                            message = responseState.message ?: "An unexpected error occured"
                        )
                    }
                }
            }
        }
    }

    data class PhotoSearchState(
        val isLoading: Boolean = false,
        val photosList: List<PhotoMap?>? = emptyList(),
        val message: String = ""
    )
}