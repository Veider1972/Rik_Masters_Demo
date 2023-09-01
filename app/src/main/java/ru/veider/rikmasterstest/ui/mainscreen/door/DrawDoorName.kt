package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.door

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.veider.domain.Door
import ru.veider.rikmasterstest.ui.theme.roomTitleFontStyle

@Composable
fun DrawDoorName(rowScope: RowScope, door: Door) {
	rowScope.run {
		Text(
			text = door.localName ?: door.name,
			style = roomTitleFontStyle,
			modifier = Modifier
				.padding(start = 16.dp)
				.width(0.dp)
				.weight(1f)
		)
	}
}