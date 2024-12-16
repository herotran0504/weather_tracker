package com.htsoft.weather.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.htsoft.weather.uikit.R

val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val AppTypography = Typography(
    displayLarge = Typography().displayLarge.copy(fontFamily = PoppinsFontFamily),
    displayMedium = Typography().displayMedium.copy(fontFamily = PoppinsFontFamily),
    displaySmall = Typography().displaySmall.copy(fontFamily = PoppinsFontFamily),
    headlineLarge = Typography().headlineLarge.copy(fontFamily = PoppinsFontFamily),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = PoppinsFontFamily),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = PoppinsFontFamily),
    bodyLarge = Typography().bodyLarge.copy(fontFamily = PoppinsFontFamily),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = PoppinsFontFamily),
    bodySmall = Typography().bodySmall.copy(fontFamily = PoppinsFontFamily),
    labelLarge = Typography().labelLarge.copy(fontFamily = PoppinsFontFamily),
    labelMedium = Typography().labelMedium.copy(fontFamily = PoppinsFontFamily),
    labelSmall = Typography().labelSmall.copy(fontFamily = PoppinsFontFamily)
)

@Composable
fun headingLarge() = MaterialTheme.typography.displayLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 70.sp,
    color = DarkGray50
)

@Composable
fun headingMedium() = MaterialTheme.typography.displayLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 60.sp,
    color = DarkGray50
)

@Composable
fun subheadingLarge() = MaterialTheme.typography.headlineLarge.copy(
    fontWeight = FontWeight.SemiBold,
    fontSize = 30.sp,
    color = DarkGray50
)

@Composable
fun subheadingSmall() = MaterialTheme.typography.titleLarge.copy(
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    color = DarkGray50
)

@Composable
fun caption() = MaterialTheme.typography.titleLarge.copy(
    fontWeight = FontWeight.SemiBold,
    fontSize = 15.sp,
    color = DarkGray50
)

@Composable
fun searchMedium() = MaterialTheme.typography.titleLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
    color = MediumGray50
)

@Composable
fun captionMuted() = MaterialTheme.typography.titleLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
    color = NeutralGray50
)

@Composable
fun bodyTextSmall() = MaterialTheme.typography.bodySmall.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    color = MediumGray50
)
