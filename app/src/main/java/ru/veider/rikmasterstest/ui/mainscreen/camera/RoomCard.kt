package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.koin.androidx.compose.koinViewModel
import ru.veider.domain.Room
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.SurfaceElement
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels.MainViewModel
import ru.veider.rikmasterstest.ui.mainscreen.common.DialogRename

@Composable
fun RoomCard(room: Room) {
	val viewModel: MainViewModel = koinViewModel()

	var showRenameDialog by rememberSaveable { mutableStateOf(false) }
	if (showRenameDialog)
		DialogRename(onDismiss = { showRenameDialog = false },
			currentName = room.localName ?: room.name,
			onAccept = { name ->
				viewModel.updateRoom(room.copy(localName = name))
				showRenameDialog = false
			},
			onCancel = { showRenameDialog = false })

	val isRevealed by viewModel.revealedRoomCard.collectAsState()
	SurfaceElement(
		isRevealed = isRevealed?.objID.let { room.objID == it },
		onEdit = { showRenameDialog = true},
		onExpand = { viewModel.revealRoom(room) },
		onCollapse = { viewModel.revealRoom(null) }) {
		RoomSurface(room = room)
	}
}