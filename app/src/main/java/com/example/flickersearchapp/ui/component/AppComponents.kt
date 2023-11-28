package com.example.flickersearchapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme

@Composable
fun SearchTextFieldComposable(
    modifier: Modifier = Modifier,
    hintText: String = "Search country",
    searchText: String = "",
    onTextChange: (newText: String) -> Unit = {},
    onSearchTextSubmit: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(color = Color.Gray, shape = RoundedCornerShape(8.dp)),
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
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
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
fun FlickerItem(modifier: Modifier = Modifier, image: String = "", text: String = "") {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent, shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .padding(top = 16.dp)
                .background(color = Color.Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = modifier
                    .size(60.dp)
                    .fillMaxWidth(),
                model = "https://picsum.photos/200",
                contentDescription = "Image"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = text,
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

