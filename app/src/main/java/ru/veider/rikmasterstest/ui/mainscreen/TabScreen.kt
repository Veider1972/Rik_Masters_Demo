package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ui.theme.BackgroundColor
import ru.veider.rikmasterstest.ui.theme.tabItemFontStyle

@Composable
fun TabScreen() {
	val tabIndex = remember { mutableIntStateOf(0) }

	val tabs = listOf(stringResource(id = R.string.cameras), stringResource(id = R.string.doors))

	Column(modifier = Modifier.fillMaxWidth()) {
		TabRow(
			selectedTabIndex = tabIndex.intValue,
			containerColor= BackgroundColor,
			modifier = Modifier.shadow(
				elevation = 2.dp,
				spotColor = Color(0x1A000000),
				ambientColor = Color(0x1A000000),
				clip = false
			),
			divider = {
				Divider(
					thickness=1.dp
				)
			}
		) {
			tabs.forEachIndexed { index, title ->
				Tab(text = {
					Text(
						text = title,
						style = tabItemFontStyle
					)
				},
					selected = tabIndex.intValue == index,
					onClick = { tabIndex.intValue = index },
					modifier = Modifier.height(44.dp)
				)
			}
		}
		when (tabIndex.intValue) {
			0 -> CamerasList()
			1 -> DoorsList()
		}
	}
}