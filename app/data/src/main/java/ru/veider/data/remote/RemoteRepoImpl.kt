package ru.veider.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.veider.core.datatype.Transport
import ru.veider.data.remote.model.CamerasBaseResponse
import ru.veider.data.remote.model.DoorsBaseResponse

private const val BASE_PATH = "http://cars.cprogroup.ru/api/rubetek/"
private const val DOORS_PATH = "${BASE_PATH}doors/"
private const val CAMERAS_PATH = "${BASE_PATH}cameras/"

class RemoteRepoImpl(
	private val client: HttpClient
) : RemoteRepo {

	override suspend fun getDoors(): Transport<DoorsBaseResponse> {
		return try {
			Transport.Success(client.get(DOORS_PATH).body())
		} catch (t: Throwable) {
			Transport.Error(t)
		}
	}

	override suspend fun getCameras(): Transport<CamerasBaseResponse> =
		try {
			Transport.Success(client.get(CAMERAS_PATH).body())
		} catch (t: Throwable) {
			Transport.Error(t)
		}

	override fun closeRepo() {
		client.close()
	}
}