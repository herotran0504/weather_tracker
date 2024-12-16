package com.htsoft.weather.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.htsoft.weather.R
import com.htsoft.weather.android.ui.theme.AppTheme
import com.htsoft.weather.android.ui.theme.caption
import com.htsoft.weather.android.ui.theme.subheadingLarge

@Composable
fun WeatherEmptyScreen(
    title: String = stringResource(R.string.weather_no_city_selected),
    subtitle: String = stringResource(R.string.weather_select_city_hint)
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = subheadingLarge(),
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = subtitle,
            style = caption(),
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherEmptyScreenPreview() {
    AppTheme {
        WeatherEmptyScreen()
    }
}
