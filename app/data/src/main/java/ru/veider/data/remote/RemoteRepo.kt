package ru.veider.data.remote

import ru.veider.core.datatype.Transport
import ru.veider.data.remote.model.CamerasBaseResponse
import ru.veider.data.remote.model.DoorsBaseResponse

interface RemoteRepo {
	suspend fun getDoors(): Transport<DoorsBaseResponse>
	suspend fun getCameras(): Transport<CamerasBaseResponse>
	fun closeRepo()
}