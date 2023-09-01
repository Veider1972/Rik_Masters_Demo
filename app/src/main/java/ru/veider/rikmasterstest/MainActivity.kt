package ru.veider.rikmasterstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.MainScreen
import ru.veider.rikmasterstest.ui.theme.BackgroundColor
import ru.veider.rikmasterstest.ui.theme.RikMastersTestTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			RikMastersTestTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color= BackgroundColor) {
					MainScreen()
				}
			}
		}
	}
}