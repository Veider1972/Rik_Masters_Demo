package ru.veider.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CameraListResponse (
	@SerializedName("room") val room : List<String>,
	@SerializedName("cameras") val cameras : List<CameraResponse>
)
