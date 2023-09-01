package ru.veider.rikmasterstest.ru.veider.rikmasterstest.ui.mainscreen.common

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ru.veider.rikmasterstest.R

@Composable
fun CameraChannel(url:String, isFavorite:Boolean, isRecord:Boolean){

	val context = LocalContext.current

	Box(modifier = Modifier
		.fillMaxWidth(1f)
		.height(207.dp)){
		GlideImage(
			modifier = Modifier
				.fillMaxSize(1f),
			imageModel = { url },
			imageOptions = ImageOptions(
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center
			)
		)
		if (isFavorite){
			Image(painter = painterResource(id = R.drawable.star),
				contentDescription = null,
				modifier = Modifier
					.align(Alignment.TopEnd)
					.padding(top = 0.dp, end = 0.dp)
			)
		}
		if (isRecord){
			Image(painter = painterResource(id = R.drawable.rec),
				contentDescription = null,
				modifier = Modifier
					.align(Alignment.TopStart)
					.padding(top = 14.dp, start = 12.dp)
			)
		}
		Image(painter = painterResource(id = R.drawable.play_button),
			contentDescription = null,
			modifier = Modifier
				.align(Alignment.Center)
				.clickable { Toast.makeText(context, context.getText(R.string.video_not_realized), Toast.LENGTH_LONG).show() }
		)


	}
}