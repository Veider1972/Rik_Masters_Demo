package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ui.theme.titleFontStyle

@Composable
fun MainScreen() {
	Column {
		Text(
			text = stringResource(id = R.string.title),
			modifier = Modifier
				.padding(top = 16.dp, bottom=20.dp)
				.fillMaxWidth(1f),
			style = titleFontStyle
		)
		TabScreen()
	}
}


