package com.example.flickersearchapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flickersearchapp.R
import com.example.flickersearchapp.ui.theme.FlickerSearchAppTheme


@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    label: String = LocalContext.current.getString(R.string.label_search), // default values
    placeholder: String = LocalContext.current.getString(R.string.label_search), // default values
    searchText: String = "", // query to search
    onTextChange: (text: String) -> Unit = {}, //it will call whenever new input would be added
    onSearchTextSubmit: () -> Unit = {}, // a call when pressed the button to submit search for API
    trailingIconClick: (text: String) -> Unit = {}, // it will clear the text from search field
    onSearchAction: () -> Unit = {}, // the action click from Keyboard Search button
) {
    Surface {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchFieldComponent(
                modifier = modifier
                    .weight(1f)
                    .background(Color.Transparent),
                query = searchText,
                onTextChange = {
                    onTextChange(it)
                },
                trailingIconClick = {
                    trailingIconClick("")
                },
                label = label,
                placeholder = placeholder,
                onSearchAction = {
                    onSearchAction()
                }
            )

            Button(
                modifier = modifier.padding(top = 8.dp, end = 8.dp),
                onClick = { onSearchTextSubmit() }) {
                Text(text = LocalContext.current.getString(R.string.label_search))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SurveyAnswerPreview() {
    FlickerSearchAppTheme {
        SearchBarComponent()
    }
}
