package ru.veider.rikmasterstest.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import ru.veider.rikmasterstest.R

val PrimaryColor @Composable get()= colorResource(id = R.color.primary)
val SecondaryColor @Composable get()= colorResource(id = R.color.secondary)
val OnSurfaceColor @Composable get()= colorResource(id = R.color.on_surface)
val TitleColor @Composable get()= colorResource(id = R.color.title_color)
val SubTitleColor @Composable get()= colorResource(id = R.color.subtitle_color)

val SurfaceColor @Composable get()= colorResource(id = R.color.white)
val BackgroundColor @Composable get()= colorResource(id = R.color.background)
val SpotColor @Composable get()= colorResource(id = R.color.spot_color)
val AmbientColor @Composable get()= colorResource(id = R.color.ambient_color)