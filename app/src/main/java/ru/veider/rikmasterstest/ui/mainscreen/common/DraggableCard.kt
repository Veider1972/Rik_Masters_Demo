package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.*
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ui.theme.AmbientColor
import ru.veider.rikmasterstest.ui.theme.SpotColor
import ru.veider.rikmasterstest.ui.theme.SurfaceColor
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableCard(
	modifier: Modifier,
	isRevealed: Boolean,
	cardOffset: Float,
	onExpand: () -> Unit,
	onCollapse: () -> Unit,
	content: @Composable () -> Unit
) {
	val transitionState = remember {
		MutableTransitionState(isRevealed).apply {
			targetState = !isRevealed
		}
	}
	val transition = updateTransition(transitionState, "cardTransition")

	val offsetTransition by transition.animateFloat(
		label = "cardOffsetTransition",
		transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
		targetValueByState = { if (isRevealed) cardOffset else 0f },

		)

	Card(
		modifier = modifier
			.offset { IntOffset(offsetTransition.roundToInt(), 0) }
			.pointerInput(Unit) {
				detectHorizontalDragGestures { _, dragAmount ->
					if (dragAmount < -MIN_DRAG_AMOUNT)
						onExpand()
					else
						onCollapse()
				}
			}
			.shadow(elevation = 3.dp, spotColor = SpotColor, ambientColor = AmbientColor, clip = false),
		backgroundColor = SurfaceColor,
		shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)),
		elevation = dimensionResource(id = R.dimen.card_elevation),
		content = { content() }
	)
}