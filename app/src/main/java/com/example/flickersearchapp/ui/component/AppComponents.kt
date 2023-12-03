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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.flickersearchapp.domain.models.Photo
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme

/**
 * [PhotoItem] is a row item that would being used in a list component [PhotosList] as a list
 * item to display the possible search items.
 * [PhotoItem] contains an AsyncImage and a Text to display the results that being passed from the
 * [Photo] object as an argument. Then build a concatenated url from the utility [loadImage]
 * based on the [photo] properties
 */
@Composable
fun PhotoItem(modifier: Modifier = Modifier, photo: Photo? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(color = Transparent, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .padding(top = 16.dp)
                .background(color = Gray, shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(70.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
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

/**
 * [loadImage] is a utility to build url from [Photo] object. it takes [photo] as argument and
 * perform concatenation and return the url to display the image. if [photo] is empty it will return
 * empty string
 */
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

/**
*  A loading progress bar to display the loading behaviour.
*/
@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        CircularProgressIndicator()
    }
}