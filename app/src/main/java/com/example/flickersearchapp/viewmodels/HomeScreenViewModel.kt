package com.example.flickersearchapp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickersearchapp.models.Photo
import com.example.flickersearchapp.models.PhotoMap
import com.example.flickersearchapp.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoSearchState())
    val uiState: StateFlow<PhotoSearchState> = _uiState.asStateFlow()

    var searchText by mutableStateOf("")
        private set

    fun updatedSearchText(newValue : String) {
        searchText = newValue
        getSearchResult()
    }

    private fun getSearchResult(){
        viewModelScope.launch {
            _uiState.update { PhotoSearchState(isLoading = true)  }
            fetchImages().let {list ->
                _uiState.update { PhotoSearchState(isLoading = false, photosList = list!!)  }
            }
        }
    }

    private suspend fun fetchImages(): List<PhotoMap>? {
        if (searchText.isBlank()) {
            return emptyList()
        }

        val searchResponse = ApiClient.client.getSearchResults(text = searchText)
        return searchResponse.photos?.photo?.map { photo ->
            PhotoMap(
                id = photo?.id.toString(),
                url = "https://farm${photo?.farm}.staticflickr.com/${photo?.server}/${photo?.id}_${photo?.secret}.jpg",
                title = photo?.title.toString()
            )
        }
    }

    data class PhotoSearchState(val isLoading : Boolean = false, val photosList : List<PhotoMap> = listOf())
}