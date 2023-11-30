package com.example.flickersearchapp.ui.component

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.flickersearchapp.domain.models.PhotoMap
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme


@Composable
fun SearchTextFieldComposable(
    modifier: Modifier = Modifier,
    hintText: String = "Search",
    searchText: String = "",
    onTextChange: (newText: String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .background(color = Gray, shape = MaterialTheme.shapes.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_search_category_default),
            contentDescription = "search icon",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(20.dp)
        )
        Box {
            BasicTextField(
                value = searchText,
                onValueChange = { onTextChange(it) },
                modifier = Modifier.background(Gray)
            )
            if (searchText == "") {
                Text(
                    modifier = Modifier,
                    text = hintText,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchComposablePreview() {
    FlickerSearchAppTheme {
        SearchTextFieldComposable()
    }
}


@Composable
fun FlickerItem(modifier: Modifier = Modifier, photo: PhotoMap? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Transparent, shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .padding(top = 16.dp)
                .background(color = Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = modifier
                    .size(60.dp)
                    .fillMaxWidth(),
                model = if (!photo?.url.isNullOrEmpty()) photo?.url else "",
                contentDescription = "Image",
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = if (!photo?.title.isNullOrEmpty()) photo?.title.toString() else "",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlickerItemPreview() {
    FlickerSearchAppTheme {
        FlickerItem()
    }
}

@Composable
fun PhotosList(modifier: Modifier = Modifier, list: List<PhotoMap> = listOf()) {

    val listState = rememberLazyListState()
    val itemHeight = with(LocalDensity.current) { 80.dp.toPx() } // Your item height
    val scrollPos = listState.firstVisibleItemIndex * itemHeight + listState.firstVisibleItemScrollOffset

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        itemsIndexed(items = list) { index, photo ->
            FlickerItem(photo = photo)
            if (listState.isScrolledToTheEnd()) {
                Toast.makeText(LocalContext.current, "${index}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotosListPreview() {
    FlickerSearchAppTheme {
        PhotosList()
    }
}

@Composable
fun SearchFieldComponent(
    modifier: Modifier = Modifier,
    hintText: String = "Search",
    searchText: String = "",
    onTextChange: (newText: String) -> Unit = {},
    onSearchTextSubmit: () -> Unit = {}
) {
    Surface(
        border = BorderStroke(1.dp, Gray), modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchTextFieldComposable(modifier = modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
                .fillMaxWidth(),
                searchText = searchText,
                hintText = hintText,
                onTextChange = { onTextChange(it) })
            Button(modifier = modifier.padding(end = 8.dp), onClick = { onSearchTextSubmit() }) {
                Text(text = "Search")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SurveyAnswerPreview() {
    FlickerSearchAppTheme {
        SearchFieldComponent()
    }
}

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        CircularProgressIndicator()
    }
}

fun LazyListState.isScrolledToTheEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount
