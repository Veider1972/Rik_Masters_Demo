package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.door

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.veider.domain.Door
import ru.veider.rikmasterstest.R

@Composable
fun DoorSurfaceWithoutImage(door: Door) {

	Box(modifier = Modifier.fillMaxWidth(1f)) {
		Row(
			modifier = Modifier
				.height(72.dp)
				.fillMaxWidth(1f),
			verticalAlignment = Alignment.CenterVertically
		) {
			DrawDoorName(rowScope = this, door = door)
			DrawLock(door = door)
		}
		if (door.favorites){
			Image(painter = painterResource(id = R.drawable.star),
				contentDescription = null,
				modifier = Modifier.align(Alignment.TopEnd).padding(top = 0.dp, end=0.dp)
			)
		}
	}
}