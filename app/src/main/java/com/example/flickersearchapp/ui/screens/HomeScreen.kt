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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.ui.component.LoadingDialog
import com.example.flickersearchapp.ui.component.PhotosList
import com.example.flickersearchapp.ui.component.SearchBarComponent
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {

    val lazyPagingItems: LazyPagingItems<Photo> = viewModel.searchData.collectAsLazyPagingItems()
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
        SearchBarComponent(
            searchText = viewModel.searchText,
            label = "Search",
            placeholder = "Search photos",
            onTextChange = { viewModel.updatedSearchText(it) },
            onSearchTextSubmit = {
                viewModel.setSearch(viewModel.searchText)
            },
            trailingIconClick = {
                viewModel.updatedSearchText(it)
                viewModel.setSearch(it)
            },
        )

        if (lazyPagingItems.loadState.append == LoadState.Loading ||
            lazyPagingItems.loadState.refresh == LoadState.Loading) {
            LoadingDialog()
        }
        PhotosList(
            lazyPagingItems = lazyPagingItems
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