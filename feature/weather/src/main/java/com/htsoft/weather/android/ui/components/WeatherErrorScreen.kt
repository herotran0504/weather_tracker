package com.htsoft.weather.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.htsoft.weather.android.ui.theme.subheadingSmall
import com.htsoft.weather.exception.NoNetworkException
import com.htsoft.weather.exception.WeatherApiKeyMissingException

@Composable
fun WeatherErrorScreen(
    icon: ImageVector,
    errorMessage: String,
    contentDescription: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = errorMessage,
            style = subheadingSmall(),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun NoNetworkExceptionPreview() {
    WeatherErrorScreen(Icons.Default.WifiOff, NoNetworkException().message.orEmpty())
}

@Preview(showBackground = true)
@Composable
fun WeatherApiKeyMissingExceptionPreview() {
    WeatherErrorScreen(Icons.Default.Error, WeatherApiKeyMissingException().message.orEmpty())
}