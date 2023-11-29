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
import com.example.flickersearchapp.ui.component.PhotosList
import com.example.flickersearchapp.ui.component.SearchFieldComponent
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel

@Composable
//fun HomeScreen(viewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        SearchFieldComponent(
            searchText = viewModel.searchText,
            onTextChange = { viewModel.updatedSearchText(it) },
            onSearchTextSubmit = { viewModel.getSearch() }
        )

        PhotosList(list = uiState.photosList as List<PhotoMap>)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FlickerSearchAppTheme {
        HomeScreen()
    }
}