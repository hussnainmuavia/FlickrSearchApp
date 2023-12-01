package com.example.flickersearchapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flickersearchapp.domain.models.PhotoMap
import com.example.flickersearchapp.ui.component.LoadingDialog
import com.example.flickersearchapp.ui.component.PhotosList
import com.example.flickersearchapp.ui.component.SearchFieldComponent
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.isLoading) {
        LoadingDialog()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        SearchFieldComponent(
            searchText = viewModel.searchText,
            onTextChange = { viewModel.updatedSearchText(it) },
            onSearchTextSubmit = {
                viewModel.updatedPageState(newValue = 1)
                viewModel.getSearch(uiState.page)
            }
        )
        PhotosList(
            list = uiState.photosList as List<PhotoMap>,
            onPageUpdate = {
                viewModel.getSearch(page = it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FlickerSearchAppTheme {
        HomeScreen()
    }
}