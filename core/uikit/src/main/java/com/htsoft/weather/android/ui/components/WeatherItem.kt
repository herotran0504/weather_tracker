package com.htsoft.weather.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.htsoft.weather.android.ui.theme.bodyTextSmall
import com.htsoft.weather.android.ui.theme.captionMuted

@Composable
fun WeatherItem(
    title: String,
    subtitle: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = bodyTextSmall(),
        )
        Text(
            style = captionMuted(),
            text = subtitle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherItemPreview() {
    WeatherItem("UV", "4")
}