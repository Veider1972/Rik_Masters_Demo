package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.*
import ru.veider.rikmasterstest.ui.theme.BackgroundColor
import kotlin.math.roundToInt


@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableSurface(
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

	Surface(
		modifier = modifier
			.offset { IntOffset(offsetTransition.roundToInt(), 0) }
			.pointerInput(Unit) {
				detectHorizontalDragGestures { _, dragAmount ->
					when {
						dragAmount < -MIN_DRAG_AMOUNT -> onExpand()
						dragAmount >= MIN_DRAG_AMOUNT -> onCollapse()
					}
				}
			},
		color = BackgroundColor,
		elevation = 0.dp,
		content = { content() }
	)
}