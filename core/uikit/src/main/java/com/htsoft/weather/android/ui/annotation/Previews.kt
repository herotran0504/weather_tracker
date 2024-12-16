package com.htsoft.weather.android.ui.annotation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class LightPreview

@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class DarkPreview
