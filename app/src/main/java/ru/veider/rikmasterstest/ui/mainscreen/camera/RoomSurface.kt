package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.camera

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.veider.domain.Room
import ru.veider.rikmasterstest.ui.theme.roomsTitleFontStyle

@Composable
fun RoomSurface(room: Room) {
	Box(modifier = Modifier.fillMaxWidth(1f)) {
		Text(
			text = room.localName ?: room.name,
			style = roomsTitleFontStyle,
			modifier = Modifier.padding(start = 21.dp, top = 16.dp)
		)
	}
}