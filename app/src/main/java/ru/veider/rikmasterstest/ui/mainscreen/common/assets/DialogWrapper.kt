package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.assets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.veider.rikmasterstest.R

@Composable
fun DialogWrapper(onDismiss: () -> Unit, body: @Composable () -> Unit) {
	Dialog(
		onDismissRequest = onDismiss, properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
	) {
		Card(
			shape = RoundedCornerShape(dimensionResource(id = R.dimen.global_padding)),
			modifier = Modifier.padding(dimensionResource(id = R.dimen.global_padding)),
			elevation = dimensionResource(id = R.dimen.global_padding)
		) {
			Column() {
				body()
			}
		}
	}
}