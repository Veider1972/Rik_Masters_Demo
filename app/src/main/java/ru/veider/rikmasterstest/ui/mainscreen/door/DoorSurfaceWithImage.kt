package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.door

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.veider.domain.Door
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.CameraChannel
import ru.veider.rikmasterstest.ui.theme.roomOnlineFontStyle

@Composable
fun DoorSurfaceWithImage(door: Door) {

	Column(modifier = Modifier.fillMaxWidth(1f)) {
		door.snapshot?.run {
			CameraChannel(url = this, isFavorite = door.favorites, isRecord = false)
		}
		Row(
			modifier = Modifier
				.height(72.dp)
				.fillMaxWidth(1f),
			verticalAlignment = Alignment.CenterVertically
		) {
			Column(
				modifier = Modifier
					.width(0.dp)
					.weight(1f)
			) {
				Row {
					DrawDoorName(rowScope = this, door = door)
				}
				Text(
					text = stringResource(id = R.string.online),
					style = roomOnlineFontStyle,
					modifier = Modifier.padding(start = 16.dp)
				)

			}
			DrawLock(door = door)
		}
	}
}