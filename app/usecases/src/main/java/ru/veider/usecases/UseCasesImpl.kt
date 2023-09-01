package ru.veider.usecases

import ru.veider.core.datatype.Transport
import ru.veider.data.Repo
import ru.veider.data.local.model.CameraRealm
import ru.veider.data.local.model.DoorRealm
import ru.veider.data.local.model.RoomRealm
import ru.veider.domain.Camera
import ru.veider.domain.Door
import ru.veider.domain.Room
import ru.veider.usecases.assets.toCamera
import ru.veider.usecases.assets.toCameraReam
import ru.veider.usecases.assets.toCamerasList
import ru.veider.usecases.assets.toDoor
import ru.veider.usecases.assets.toDoorRealm
import ru.veider.usecases.assets.toDoorsList
import ru.veider.usecases.assets.toRoom
import ru.veider.usecases.assets.toRoomRealm
import ru.veider.usecases.assets.toRoomsList

class UseCasesImpl(
	private val repo: Repo
) : UseCases {
	override suspend fun getRemoteDoors(): Transport<List<Door>> =
		when (val result = repo.getDoors()) {
			is Transport.Success -> Transport.Success(result.data.toDoorsList())
			is Transport.Error -> Transport.Error(result.error)
		}

	override suspend fun getRemoteCameras(): Transport<Pair<List<Room>, List<Camera>>> =
		when (val result = repo.getCameras()) {
			is Transport.Success -> Transport.Success(Pair(result.data.toRoomsList(), result.data.toCamerasList()))
			is Transport.Error -> Transport.Error(result.error)
		}

	override fun closeRepo() {
		repo.closeRepo()
	}

	override suspend fun getAllCameras(): Transport<List<Camera>> =
		try {
			when (val result = repo.getAll(CameraRealm::class)) {
				is Transport.Success -> Transport.Success(result.data.map { it.toCamera() })
				is Transport.Error -> Transport.Error(result.error)
			}
		} catch (t: Throwable) {
			Transport.Error(t)
		}

	override suspend fun getAllDoors(): Transport<List<Door>> =
		try {
			when (val result = repo.getAll(DoorRealm::class)) {
				is Transport.Success -> Transport.Success(result.data.map { it.toDoor() })
				is Transport.Error -> Transport.Error(result.error)
			}
		} catch (t: Throwable) {
			Transport.Error(t)
		}

	override suspend fun getAllRooms(): Transport<List<Room>> =
		try {
			when (val result = repo.getAll(RoomRealm::class)) {
				is Transport.Success -> Transport.Success(result.data.map { it.toRoom() })
				is Transport.Error -> Transport.Error(result.error)
			}
		} catch (t: Throwable) {
			Transport.Error(t)
		}

	override suspend fun saveAllCameras(objs: List<Camera>): Transport<Boolean> =
		repo.saveAll(objs.map {
			it.toCameraReam() })

	override suspend fun saveAllDoors(objs: List<Door>): Transport<Boolean> =
		repo.saveAll(objs.map { it.toDoorRealm() })

	override suspend fun saveAllRooms(objs: List<Room>): Transport<Boolean> =
		repo.saveAll(objs.map { it.toRoomRealm() })

	override suspend fun updateCamera(camera: Camera): Transport<Boolean> = repo.update(camera.toCameraReam())
	override suspend fun updateDoor(door: Door): Transport<Boolean> = repo.update(door.toDoorRealm())
	override suspend fun updateRoom(room: Room): Transport<Boolean> = repo.update(room.toRoomRealm())

	override suspend fun saveCamera(camera: Camera): Transport<Boolean> = repo.save(camera.toCameraReam())
	override suspend fun saveDoor(door: Door): Transport<Boolean> = repo.save(door.toDoorRealm())
	override suspend fun saveRoom(room: Room): Transport<Boolean> = repo.save(room.toRoomRealm())

}