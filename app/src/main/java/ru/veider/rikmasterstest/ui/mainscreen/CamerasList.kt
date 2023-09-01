package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.veider.core.datatype.ScreenState
import ru.veider.domain.Camera
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels.MainViewModel
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.camera.CameraCard
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.camera.RoomCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CamerasList() {
	val viewModel: MainViewModel = koinViewModel()
	val cameras by viewModel.camerasList.collectAsState()
	val rooms by viewModel.roomsList.collectAsState()
	val refreshing by viewModel.isCamerasRefreshing.collectAsState()
	val pullRefreshState = rememberPullRefreshState(refreshing = refreshing, onRefresh = { viewModel.getRemoteCameras() })

	Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
		LazyColumn(modifier = Modifier.fillMaxWidth(1f)) {
			when (cameras) {
				is ScreenState.Success -> {
					(cameras as ScreenState.Success<List<Camera>>).data.filter { it.room.isNullOrBlank() }.forEach { camera ->
						item {
							CameraCard(camera)
						}
					}
					rooms.forEach { room ->
						val camerasList = (cameras as ScreenState.Success<List<Camera>>).data.filter { it.room == room.name }
						if (camerasList.isNotEmpty()) {
							item {
								RoomCard(room = room)
							}

							camerasList.forEach { camera ->
								item {
									CameraCard(camera)
								}
							}
						}
					}
				}

				is ScreenState.Error -> {
					item {
						Text(text = (cameras as ScreenState.Error<List<Camera>>).error.message.toString())
					}
				}

				is ScreenState.Loading -> {}
			}
		}
		PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
	}
}










