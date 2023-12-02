package com.example.flickersearchapp.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme
import kotlinx.coroutines.flow.flowOf


@Composable
fun PhotosList(
    modifier: Modifier = Modifier, lazyPagingItems: LazyPagingItems<Photo>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        items(count = lazyPagingItems.itemCount) { index ->
            val photo = lazyPagingItems[index]
            PhotoItem(photo = photo)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotosListPreview() {
    FlickerSearchAppTheme {
        PhotosList(lazyPagingItems = flowOf(PagingData.from(listOf<Photo>())).collectAsLazyPagingItems())
    }
}