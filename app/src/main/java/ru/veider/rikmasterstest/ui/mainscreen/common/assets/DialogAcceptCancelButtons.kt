package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.assets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ui.theme.SecondaryColor
import ru.veider.rikmasterstest.ui.theme.dialogText

@Composable
fun DialogAcceptCancelButtons(accept: () -> Unit, cancel: () -> Unit) {
	Row {
		Button(
			onClick = accept,
			modifier = Modifier
				.padding(
					start = dimensionResource(id = R.dimen.single_padding),
					bottom = dimensionResource(id = R.dimen.single_padding),
					end = dimensionResource(id = R.dimen.half_padding)
				)
				.weight(0.5f),
			colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryColor)
		) {
			Text(text = stringResource(id = R.string.accept), style = dialogText)
		}
		Button(
			onClick = cancel,
			modifier = Modifier
				.padding(
					start = dimensionResource(id = R.dimen.half_padding),
					end = dimensionResource(id = R.dimen.single_padding),
					bottom = dimensionResource(id = R.dimen.single_padding)
				)
				.weight(0.5f),
			colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryColor)
		) {
			Text(text = stringResource(id = R.string.cancel), style = dialogText)
		}
	}
}