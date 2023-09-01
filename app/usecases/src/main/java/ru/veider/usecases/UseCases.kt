package ru.veider.usecases

import ru.veider.core.datatype.Transport
import ru.veider.domain.Camera
import ru.veider.domain.Door
import ru.veider.domain.Room

interface UseCases {
	suspend fun getRemoteDoors(): Transport<List<Door>>
	suspend fun getRemoteCameras(): Transport<Pair<List<Room>, List<Camera>>>
	fun closeRepo()

	suspend fun getAllCameras() : Transport<List<Camera>>
	suspend fun getAllDoors() : Transport<List<Door>>
	suspend fun getAllRooms() : Transport<List<Room>>
	suspend fun saveAllCameras(objs: List<Camera>): Transport<Boolean>
	suspend fun saveAllDoors(objs: List<Door>): Transport<Boolean>
	suspend fun saveAllRooms(objs: List<Room>): Transport<Boolean>
	suspend fun updateCamera(camera:Camera): Transport<Boolean>
	suspend fun updateDoor(door:Door): Transport<Boolean>
	suspend fun updateRoom(room:Room): Transport<Boolean>
	suspend fun saveCamera(camera:Camera): Transport<Boolean>
	suspend fun saveDoor(door:Door): Transport<Boolean>
	suspend fun saveRoom(room:Room): Transport<Boolean>

}