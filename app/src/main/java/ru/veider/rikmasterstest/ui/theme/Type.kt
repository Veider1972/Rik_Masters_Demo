package ru.veider.rikmasterstest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.veider.rikmasterstest.R

// Set of Material typography styles to start with
val Typography = Typography(
	bodyLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	)
)

val titleFontStyle
	@Composable get() = TextStyle(
		fontSize = 21.sp,
		fontFamily = FontFamily(Font(R.font.circe_w400)),
		fontWeight = FontWeight(400),
		color = TitleColor,
		textAlign = TextAlign.Center
	)

val tabItemFontStyle
	@Composable get() = TextStyle(
		fontSize = 17.sp,
		lineHeight = 16.sp,
		fontFamily = FontFamily(Font(R.font.circe_w400)),
		fontWeight = FontWeight(400),
		color = TitleColor,
		textAlign = TextAlign.Center
	)

val roomsTitleFontStyle
	@Composable get() = TextStyle(
		fontSize = 21.sp,
		lineHeight = 30.95.sp,
		fontFamily = FontFamily(Font(R.font.circe_w300)),
		fontWeight = FontWeight(300),
		color = TitleColor
	)

val cameraTitleFontStyle
	@Composable get() = TextStyle(
		fontSize = 17.sp,
		lineHeight = 25.06.sp,
		fontFamily = FontFamily(Font(R.font.circe_w400)),
		fontWeight = FontWeight(400),
		color = TitleColor
	)

val roomTitleFontStyle
	@Composable get() = TextStyle(
		fontSize = 17.sp,
		lineHeight = 25.06.sp,
		fontFamily = FontFamily(Font(R.font.circe_w400)),
		fontWeight = FontWeight(400),
		color = TitleColor
	)

val roomOnlineFontStyle
	@Composable get() = TextStyle(
		fontSize = 14.sp,
		lineHeight = 20.64.sp,
		fontFamily = FontFamily(Font(R.font.circe_w300)),
		fontWeight = FontWeight(300),
		color = SubTitleColor
	)

val dialogTitle
	get() = TextStyle(
		fontFamily = FontFamily(Font(R.font.circe_w400)),
		fontSize = 17.sp
	)

val dialogText
	get() = TextStyle(
	fontFamily = FontFamily(Font(R.font.circe_w400)),
		fontSize = 15.sp)