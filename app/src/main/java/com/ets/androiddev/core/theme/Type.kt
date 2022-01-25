package com.ets.androiddev.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ets.androiddev.R

private val RalewayFontFamily = FontFamily(
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.raleway_light, FontWeight.Light),
    Font(R.font.raleway_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.raleway_bold, FontWeight.Bold),
    Font(R.font.raleway_bold_italic, FontWeight.Bold, FontStyle.Italic),
)

val typography = Typography(
    defaultFontFamily = RalewayFontFamily,
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 1.15.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 1.15.sp
    )
)
