package com.example.flickersearchapp.ui.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.flickersearchapp.R
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.ui.component.LoadingDialog
import com.example.flickersearchapp.ui.component.PhotosList
import com.example.flickersearchapp.ui.component.SearchBarComponent
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {

    val lazyPagingItems: LazyPagingItems<Photo> = viewModel.searchData.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        SearchBarComponent(
            searchText = viewModel.searchText,
            label = LocalContext.current.getString(R.string.label_search),
            placeholder = LocalContext.current.getString(R.string.label_search_photos),
            onTextChange = { viewModel.updatedSearchText(it) },
            onSearchTextSubmit = {
                viewModel.setSearch(viewModel.searchText)
            },
            trailingIconClick = {
                viewModel.updatedSearchText(it)
                viewModel.setSearch(it)
            },
            onSearchAction = {
                viewModel.setSearch(viewModel.searchText)
            }
        )

        /**
         * When user will press the search button and the API call hit,
         * it will display the loading progress bar if the success hit otherwise display error message
         * This will be displayed upon two conditions for now as
         * when the [loadState] would be [append] or [refresh].
         */
        if ((lazyPagingItems.loadState.refresh == LoadState.Loading) ||
            (lazyPagingItems.loadState.append == LoadState.Loading)) {
            LoadingDialog()
        }
        if ((lazyPagingItems.loadState.refresh is LoadState.Error) ||
            (lazyPagingItems.loadState.append is LoadState.Error)) {
            // SomeCatchableException
            val error = (lazyPagingItems.loadState.refresh as LoadState.Error).error
            Toast.makeText(
                LocalContext.current,
                error.localizedMessage ?: "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }

        /**
         * A list component to display the search items.
         */
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