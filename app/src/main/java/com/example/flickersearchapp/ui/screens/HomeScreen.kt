package com.example.flickersearchapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flickersearchapp.ui.component.FlickerItem
import com.example.flickersearchapp.ui.component.SearchTextFieldComposable
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val list = listOf("Hello 1", "Hello 2", "Hello 3", "Hello 4")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        SearchTextFieldComposable(
            searchText = viewModel.searchText,
            onTextChange = { viewModel.updatedSearchText(it) }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items = list) { item ->
                FlickerItem(image = "", text = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FlickerSearchAppTheme {
        HomeScreen()
    }
}