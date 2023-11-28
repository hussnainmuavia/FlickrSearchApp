package com.example.flickersearchapp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {

    var searchText by mutableStateOf("")
        private set

    fun updatedSearchText(newValue : String) {
        searchText = newValue
        Log.d("searchText", searchText)
    }
}