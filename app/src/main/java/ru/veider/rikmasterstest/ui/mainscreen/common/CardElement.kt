package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.veider.rikmasterstest.R


const val CARD_OFFSET = -250f
val CARD_TOP_PADDING = 11.dp

@Composable
fun CardElement(
	height: Dp,
	isRevealed: Boolean,
	onFavorite: () -> Unit,
	onEdit: () -> Unit,
	onExpand: () -> Unit,
	onCollapse: () -> Unit,
	content: @Composable () -> Unit
) {
	Box(
		Modifier
			.fillMaxWidth()
			.padding(CARD_TOP_PADDING)
			.height(height)
	) {
		ActionsEditFavorite(
			modifier = Modifier
				.align(Alignment.CenterEnd)
				.padding(end = dimensionResource(id = R.dimen.global_padding)),
			actionIconSize = 36.dp,
			onFavorite = { onFavorite() },
			onEdit = { onEdit() }
		)
		DraggableCard(
			isRevealed = isRevealed,
			modifier = Modifier
				.padding(
					start = dimensionResource(id = R.dimen.global_padding),
					end = dimensionResource(id = R.dimen.global_padding)
				)
				.height(279.dp),
			cardOffset = CARD_OFFSET,
			onExpand = { onExpand() },
			onCollapse = { onCollapse() },
			content = { content() }
		)
	}
}