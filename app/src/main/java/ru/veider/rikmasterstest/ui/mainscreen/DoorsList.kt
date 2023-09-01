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
import ru.veider.domain.Door
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.door.DoorCard
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorsList() {
	val viewModel: MainViewModel = koinViewModel()
	val doors by viewModel.doorsList.collectAsState()
	val refreshing  by viewModel.isDoorsRefreshing.collectAsState()
	val pullRefreshState = rememberPullRefreshState(refreshing = refreshing , onRefresh = { viewModel.getRemoteDoors() })
	Box(modifier=Modifier.pullRefresh(pullRefreshState)){
		LazyColumn(modifier = Modifier.fillMaxWidth(1f)) {
			when (doors) {
				is ScreenState.Success -> {
					(doors as ScreenState.Success<List<Door>>).data.forEach { door ->
						item {
							DoorCard(door)
						}
					}
				}

				is ScreenState.Error -> {
					item {
						Text(text = (doors as ScreenState.Error<List<Door>>).error.message.toString())
					}
				}
				is ScreenState.Loading -> {}
			}

		}
		PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
	}

}









