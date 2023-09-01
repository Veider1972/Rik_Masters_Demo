package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.door

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.veider.domain.Door
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.CardElement
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels.MainViewModel
import ru.veider.rikmasterstest.ui.mainscreen.common.DialogRename

@Composable
fun DoorCard(door: Door) {
	val viewModel: MainViewModel = koinViewModel()

	var showRenameDialog by rememberSaveable { mutableStateOf(false) }
	if (showRenameDialog)
		DialogRename(onDismiss = { showRenameDialog = false },
			currentName = door.localName ?: door.name,
			onAccept = { name ->
				viewModel.updateDoor(door.copy(localName = name))
				showRenameDialog = false
			},
			onCancel = { showRenameDialog = false })

	val isRevealed by viewModel.revealedDoorCard.collectAsState()
	CardElement(
		height = if (door.snapshot.isNullOrEmpty()) 72.dp else 279.dp,
		isRevealed = isRevealed?.objID.let { door.objID == it },
		onFavorite = { viewModel.updateDoor(door.copy(favorites = door.favorites.not())) },
		onEdit = { showRenameDialog = true },
		onExpand = { viewModel.revealDoor(door) },
		onCollapse = { viewModel.revealDoor(null) }) {
		if (door.snapshot.isNullOrEmpty())
			DoorSurfaceWithoutImage(door = door)
		else
			DoorSurfaceWithImage(door = door)
	}
}