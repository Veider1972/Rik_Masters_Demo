package ru.veider.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CameraResponse (
	@SerializedName("name") val name : String,
	@SerializedName("snapshot") val snapshot : String,
	@SerializedName("room") val room : String? = null,
	@SerializedName("id") val id : Int,
	@SerializedName("favorites") val favorites : Boolean,
	@SerializedName("rec") val rec : Boolean
)
