package com.htsoft.weather.android.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.htsoft.weather.android.domain.Weather
import com.htsoft.weather.android.ui.theme.LightGray50
import com.htsoft.weather.android.ui.theme.headingMedium
import com.htsoft.weather.android.ui.theme.subheadingSmall

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun WeatherSearchResult(
    weather: Weather, onClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(32.dp))
        Card(
            modifier = Modifier
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = LightGray50),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                with(sharedTransitionScope) {
                    Column {
                        Text(
                            text = weather.location.name,
                            style = subheadingSmall(),
                            modifier = Modifier.sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "name-${weather.location.name}"),
                                animatedVisibilityScope = animatedContentScope
                            )
                        )
                        Text(
                            text = "${weather.current.tempCelsius}°",
                            style = headingMedium(),
                            modifier = Modifier.sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "temp-${weather.location.name}"),
                                animatedVisibilityScope = animatedContentScope
                            )
                        )
                    }
                    AsyncImage(
                        model = weather.current.condition.icon,
                        contentDescription = weather.current.condition.text,
                        Modifier
                            .size(75.dp)
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "weather-image"),
                                animatedVisibilityScope = animatedContentScope
                            )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun SearchResultCardPreview() {
    SharedTransitionLayout {
        AnimatedContent(targetState = true, label = "basic_transition") {
            if (it) {
                WeatherSearchResult(
                    weather = weather,
                    {},
                    this@SharedTransitionLayout,
                    animatedContentScope = this@AnimatedContent,
                )
            }
        }
    }
}
