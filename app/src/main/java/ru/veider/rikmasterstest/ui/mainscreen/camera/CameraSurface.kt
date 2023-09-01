package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.camera

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.veider.domain.Camera
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.CameraChannel
import ru.veider.rikmasterstest.ui.theme.cameraTitleFontStyle

@Composable
fun CameraSurface(camera: Camera) {
	Column(modifier = Modifier.fillMaxWidth(1f)) {
		camera.snapshot?.run{
			CameraChannel(url = this, isFavorite = camera.favorites, isRecord = camera.rec)
		}
		Row(
			modifier = Modifier
				.height(72.dp)
				.fillMaxWidth(1f),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = camera.localName ?: camera.name,
				style = cameraTitleFontStyle,
				modifier = Modifier.padding(start = 16.dp)
			)
		}
	}
}