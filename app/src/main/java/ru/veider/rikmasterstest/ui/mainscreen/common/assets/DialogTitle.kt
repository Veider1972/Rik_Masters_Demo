package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.assets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ui.theme.dialogTitle

@Composable
fun DialogTitle(text: Int) {
	DialogTitle(stringResource(id = text))
}

@Composable
fun DialogTitle(text: String) {
	Text(
		text = text.uppercase(), style = dialogTitle, maxLines = 1,
		modifier = Modifier
			.padding(top = dimensionResource(id = R.dimen.single_padding))
			.padding(horizontal = dimensionResource(id = R.dimen.single_padding))
			.fillMaxWidth(),
		textAlign = TextAlign.Center
	)
}