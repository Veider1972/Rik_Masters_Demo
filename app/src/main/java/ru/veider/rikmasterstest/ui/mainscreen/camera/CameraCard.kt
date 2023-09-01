package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.veider.domain.Camera
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.CardElement
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels.MainViewModel
import ru.veider.rikmasterstest.ui.mainscreen.common.DialogRename

@Composable
fun CameraCard(camera: Camera) {

	val viewModel: MainViewModel = koinViewModel()

	var showRenameDialog by rememberSaveable { mutableStateOf(false) }
	if (showRenameDialog)
		DialogRename(onDismiss = { showRenameDialog = false },
			currentName = camera.localName ?: camera.name,
			onAccept = { name ->
				viewModel.updateCamera(camera.copy(localName = name))
				showRenameDialog = false
			},
			onCancel = { showRenameDialog = false })

	val isRevealed by viewModel.revealedCameraCard.collectAsState()
	CardElement(
		height = 279.dp,
		isRevealed = isRevealed?.objID.let { camera.objID == it },
		onFavorite = { viewModel.updateCamera(camera.copy(favorites = camera.favorites.not())) },
		onEdit = { showRenameDialog = true },
		onExpand = {
			viewModel.revealCamera(camera)
		},
		onCollapse = {
			viewModel.revealCamera(null)
		}) {
		CameraSurface(camera = camera)
	}
}