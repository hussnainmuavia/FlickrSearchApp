package com.example.flickersearchapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme

@Composable
fun PhotoItem(modifier: Modifier = Modifier, photo: Photo? = null) {
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
                model = loadImage(photo),
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

fun loadImage(photo: Photo? = null): String {
    photo?.let {
        return "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
    }
    return ""
}

@Preview(showBackground = true)
@Composable
fun FlickerItemPreview() {
    FlickerSearchAppTheme {
        PhotoItem()
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