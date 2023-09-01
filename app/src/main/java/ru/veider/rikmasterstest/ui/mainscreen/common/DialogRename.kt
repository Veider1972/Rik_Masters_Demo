package ru.veider.rikmasterstest.ui.mainscreen.common

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import ru.veider.rikmasterstest.R
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.assets.DialogAcceptCancelButtons
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.assets.DialogTitle
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common.assets.DialogWrapper
import ru.veider.rikmasterstest.ui.theme.OnSurfaceColor
import ru.veider.rikmasterstest.ui.theme.PrimaryColor
import ru.veider.rikmasterstest.ui.theme.dialogText

@Composable
fun DialogRename(
	currentName: String, onDismiss: () -> Unit, onAccept: (String) -> Unit, onCancel: () -> Unit
) {

	val context = LocalContext.current

	val newName = remember { mutableStateOf(currentName) }

	DialogWrapper(onDismiss = onDismiss) {
		DialogTitle(text = R.string.enter_new_name)
		TextField(
			value = newName.value,
			onValueChange = { value -> newName.value = value },
			textStyle = dialogText,
			modifier = Modifier
				.padding(horizontal = dimensionResource(id = R.dimen.single_padding))
				.border(1.dp, PrimaryColor, RoundedCornerShape(8.dp)),
			singleLine = true,
			shape = RoundedCornerShape(8.dp),

			colors = TextFieldDefaults.textFieldColors(
				backgroundColor = Color.Transparent,
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent,
				disabledIndicatorColor = Color.Transparent,
				textColor = OnSurfaceColor
			)
		)
		DialogAcceptCancelButtons(accept = {
			if (newName.value.isNotEmpty()) onAccept(newName.value)
			else Toast.makeText(context, context.getString(R.string.not_empty), Toast.LENGTH_LONG).show()
		}, cancel = { onCancel() })
	}

}