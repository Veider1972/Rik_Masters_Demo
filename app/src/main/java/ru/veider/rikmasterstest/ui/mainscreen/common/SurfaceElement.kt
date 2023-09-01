package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import ru.veider.rikmasterstest.R


const val SURFACE_OFFSET = -150f
val SURFACE_TOP_PADDING = 0.dp

@Composable
fun SurfaceElement(
	isRevealed: Boolean,
	onEdit: () -> Unit,
	onExpand: () -> Unit,
	onCollapse: () -> Unit,
	content: @Composable () -> Unit
) {
	Box(
		Modifier
			.fillMaxWidth()
			.padding(SURFACE_TOP_PADDING)
	) {
		ActionsEdit(
			modifier = Modifier
				.align(Alignment.CenterEnd)
				.padding(end = dimensionResource(id = R.dimen.global_padding)),
			actionIconSize = 36.dp,
			onEdit = { onEdit() }
		)
		DraggableSurface(
			isRevealed = isRevealed,
			modifier = Modifier
				.padding(
					start = dimensionResource(id = R.dimen.global_padding),
					end = dimensionResource(id = R.dimen.global_padding)
				),
			cardOffset = SURFACE_OFFSET,
			onExpand = { onExpand() },
			onCollapse = { onCollapse() },
			content = { content() }
		)
	}
}