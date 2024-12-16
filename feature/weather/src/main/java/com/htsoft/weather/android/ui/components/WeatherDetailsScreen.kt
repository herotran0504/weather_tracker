package com.htsoft.weather.android.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.htsoft.weather.R
import com.htsoft.weather.android.domain.Weather
import com.htsoft.weather.android.ui.annotation.LightPreview
import com.htsoft.weather.android.ui.theme.LightGray50
import com.htsoft.weather.android.ui.theme.headingLarge
import com.htsoft.weather.android.ui.theme.subheadingLarge
import com.htsoft.weather.uikit.R.drawable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun WeatherDetailsScreen(
    weather: Weather,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(72.dp))
            AsyncImage(
                model = weather.current.condition.icon,
                contentDescription = weather.current.condition.text,
                modifier = Modifier
                    .height(124.dp)
                    .align(Alignment.CenterHorizontally)
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "weather-image"),
                        animatedVisibilityScope = animatedContentScope
                    )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = weather.location.name,
                    style = subheadingLarge(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "name-${weather.location.name}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(drawable.ic_location),
                    contentDescription = "",
                    modifier = Modifier.size(21.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${weather.current.tempCelsius}°",
                style = headingLarge(),
                modifier = Modifier.sharedElement(
                    sharedTransitionScope.rememberSharedContentState(key = "temp-${weather.location.name}"),
                    animatedVisibilityScope = animatedContentScope
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = LightGray50,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WeatherItem(
                            title = stringResource(R.string.weather_humidity),
                            subtitle = "${weather.current.humidity}%"
                        )
                        WeatherItem(
                            title = stringResource(R.string.weather_uv),
                            subtitle = "${weather.current.uv}"
                        )
                        WeatherItem(
                            title = stringResource(R.string.weather_feels_like),
                            subtitle = "${weather.current.feelsLikeCelsius}°"
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@LightPreview
@Composable
fun WeatherDetailsScreenPreview() {
    SharedTransitionLayout {
        AnimatedContent(targetState = true, label = "basic_transition") {
            if (it) {
                WeatherDetailsScreen(
                    weather = weather,
                    this@SharedTransitionLayout,
                    animatedContentScope = this@AnimatedContent,
                )
            }
        }
    }
}
