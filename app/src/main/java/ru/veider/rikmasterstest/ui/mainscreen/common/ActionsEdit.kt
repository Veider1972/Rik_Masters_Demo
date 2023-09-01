package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.veider.rikmasterstest.R

@Composable
fun ActionsEdit(
	modifier: Modifier,
	actionIconSize: Dp,
	onEdit: () -> Unit
) {
	Row(modifier = modifier) {
		IconButton(
			modifier = Modifier
				.padding(end=9.dp)
				.size(actionIconSize),
			onClick = onEdit,
			content = {
				Image(
					painter = painterResource(id = R.drawable.edit),
					contentDescription = null,
				)
			}
		)
	}
}