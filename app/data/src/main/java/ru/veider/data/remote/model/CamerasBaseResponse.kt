package ru.veider.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CamerasBaseResponse(
	@SerializedName("success") val success : Boolean,
	@SerializedName("data") val data : CameraListResponse
)




