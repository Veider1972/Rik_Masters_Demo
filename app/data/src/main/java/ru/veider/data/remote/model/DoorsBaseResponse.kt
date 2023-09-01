package ru.veider.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DoorsBaseResponse(
	@SerializedName("success") val success : Boolean,
	@SerializedName("data") val data : List<DoorListResponse>
)


