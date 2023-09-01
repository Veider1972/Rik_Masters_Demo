package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.door

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.veider.domain.Door
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels.MainViewModel

@Composable
fun DrawLock(door: Door) {

	val viewModel: MainViewModel = koinViewModel()

	Image(
		painter = if (door.locked) painterResource(id = R.drawable.lockon)
		else painterResource(id = R.drawable.lockoff),
		contentDescription = null,
		modifier = Modifier.padding(end = 24.7.dp)
			.clickable { viewModel.updateDoor(door.copy(locked = door.locked.not())) }
	)
}